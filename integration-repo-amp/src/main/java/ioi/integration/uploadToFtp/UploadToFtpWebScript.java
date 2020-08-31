package ioi.integration.uploadToFtp;

import ioi.integration.ftp.Ftp;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.*;
import org.alfresco.service.namespace.QName;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;

public class UploadToFtpWebScript extends DeclarativeWebScript {

	private ServiceRegistry serviceRegistry;

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	private Ftp ftp;

	public void setFtp(Ftp ftp) {
		this.ftp = ftp;
	}

	private String directoryName;
	private String fileName;
	private final Set<QName> models = new HashSet<QName>();
	
	private NodeRef mainNodeRef;
	private DocumentBuilder builder;
	private Map<QName,Serializable> mainNodeProperties;

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		Map<String, Object> model = new HashMap<String, Object>();

		try {
			loadWebScriptRequestParam(req);
			mainNodeProperties = getSerializableDatalist(mainNodeRef);
			initializationVariable();
			
			//testMethod();
			
			initializationDocumentBuilder();
			Document xmlDocument = writeDatalistToXML(mainNodeRef);
			ftp.ftpConnect();
			ftp.makeDirectoryInFtp(directoryName);
			ftp.uploadXmlToFileInFtp(xmlDocument, fileName);
			ftp.ftpDisconnect();
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg", e.getMessage());
			return model;
		}

		model.put("msg", "Загрузка на FTP выполнена");
		return model;
	}
	/**
	 * TEST!!!!
	 */
	public void testMethod() {
		System.out.println(serviceRegistry.getNodeService().getType(mainNodeRef));
		System.out.println(serviceRegistry.getNodeService().getType(mainNodeRef));
		System.out.println("+++++++++++");
		List<ChildAssociationRef> childAssociationRefs = serviceRegistry.getNodeService().getParentAssocs(mainNodeRef);
		for(ChildAssociationRef childAssociationRef : childAssociationRefs) {
			System.out.println(serviceRegistry.getNodeService().getType(childAssociationRef.getParentRef()));
			System.out.println(serviceRegistry.getNodeService().getType(childAssociationRef.getChildRef()));
			System.out.println("============");
		}
	}
	/**
	 * Считывание значений из запроса
	 * @throws Exception 
	 */
	public void loadWebScriptRequestParam(WebScriptRequest req) throws Exception {
		String result;
		try {
			mainNodeRef = new NodeRef(req.getParameter("nodeRef"));
		} catch (MalformedNodeRefException e) {
			e.printStackTrace();
			result = "MalformedNodeRefException, method: loadWebScriptRequestParam()";
			throw new Exception(result);
		}
	}
	/**
	 * Получение значений Даталиста
	 * @throws Exception 
	 */
	public Map<QName,Serializable> getSerializableDatalist(NodeRef nodeRef) throws Exception {
		try {
			return serviceRegistry.getNodeService().getProperties(nodeRef);
		} catch (InvalidNodeRefException e) {
			e.printStackTrace();
			String result = "InvalidNodeRefException, method: makeSerializableDatalist()";
			throw new Exception(result);
		}
	}
	/**
	 * Инициализация переменных
	 * @throws Exception 
	 */
	public void initializationVariable () {
		directoryName = mainNodeRef.toString().replace("workspace://SpacesStore/", "");
		fileName = directoryName + "_property.xml";
		models.add(QName.createQName("{http://www.ioi.com/model/AZTKP/1.0}AZTKPModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/priceInfo/1.0}priceInfoModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/procurement/1.0}procurementProcedureModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/priceInfo/1.0}priceInfoModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/supplier/1.0}SupplierModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/lotdoc/1.0}LotDocumentModel"));
		models.add(QName.createQName("{http://www.ioi.com/model/directory/1.0}DirectoryModel"));
	}
	/**
	 * Инициализация документ-билдера для XML
	 * @throws Exception 
	 */
	public void initializationDocumentBuilder() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			String result = "ParserConfigurationException, method: initializationDocumentBuilder()";
			throw new Exception(result);
		}
	}
	/**
	 * Запись настроек в XML файл
	 * @throws Exception 
	 */
	public Document writeDatalistToXML(NodeRef nodeRef) throws Exception {
		Document document = builder.newDocument();
		Element rootElement = document.createElement("nodeRef:" + directoryName);

		fillDocWithNodeProperties(document, mainNodeProperties, rootElement);
		
		Element associationElement = document.createElement("associations");
		rootElement.appendChild(associationElement);

		for (QName qNameModel : models) {

			Collection<QName> qNameAssociations = serviceRegistry.getDictionaryService().getAssociations(qNameModel);

			if (!qNameAssociations.isEmpty()) {

				Map<QName, Serializable> associationNodeProperties;

				for (QName qNameAssociationOfType : qNameAssociations) {
					List<AssociationRef> associationRefsList = serviceRegistry.getNodeService().getTargetAssocs(mainNodeRef, qNameAssociationOfType);
					if (!associationRefsList.isEmpty()) {
						for (AssociationRef associationRef : associationRefsList) {
							NodeRef nodeRefAssociation = associationRef.getTargetRef();
							try {
								associationNodeProperties = getSerializableDatalist(nodeRefAssociation);
							} catch (Exception e) {
								e.printStackTrace();
								String result = "Exception, method: writeDatalistToXML()";
								throw new Exception(result);
							}
							Element associationChieldElement = document.createElement("nodeRef:" + nodeRefAssociation.toString().replace("workspace://SpacesStore/", ""));
							associationElement.appendChild(associationChieldElement);
							fillDocWithNodeProperties(document, associationNodeProperties, associationChieldElement);
						}
					}
				}
			}
		}
		document.appendChild(rootElement);
		return document;
	}
	/**
	 * Заполнение документа свойствами нода
	 */
	public void fillDocWithNodeProperties(Document document,Map<QName, Serializable> nodeProperties, Element mainElement) {
		for (QName key : nodeProperties.keySet()) {
			Element propertyElement = document.createElement(key.getLocalName());
			if (nodeProperties.get(key) != null) {
				propertyElement.appendChild(document.createTextNode(nodeProperties.get(key).toString()));
			} else {
				propertyElement.appendChild(document.createTextNode("null"));
			}
			mainElement.appendChild(propertyElement);
		}
	}
}