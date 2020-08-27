package ioi.integration.uploadToFtp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.MalformedNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UploadToFtpWebScript extends DeclarativeWebScript {

	private ServiceRegistry serviceRegistry;

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	
	String directoryName;
	String fileName;
	
	NodeRef mainNodeRef;
	DocumentBuilder builder;
	FTPClient fClient;
	Map<QName,Serializable> mainNodeProperties;
	
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			loadWebScriptRequestParam(req);
			mainNodeProperties = getSerializableDatalist(mainNodeRef);
			initializationVariable();
			
			testMethod();
			
			initializationDocumentBuilder();
			Document xmlDocument = writeDatalistToXML(mainNodeRef);
			ftpConnect("192.168.3.93", "ftpuser", "S@asd123456");
			makeDirectoryInFtp(directoryName);
			uploadXmlToFileInFtp(xmlDocument);
			ftpDisconnect();
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
		Element rootElement = document.createElement(directoryName);
		
		fillDocWithNodeProperties(document, mainNodeProperties, rootElement);
		
		Element associationElement = document.createElement("associations");
		rootElement.appendChild(associationElement);

		Collection<QName> qNameAssociations = serviceRegistry.getDictionaryService().getAssociations(QName.createQName("{http://www.ioi.com/model/AZTKP/1.0}AZTKPModel"));
		
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
						Element associationChieldElement = document.createElement(nodeRefAssociation.toString().replace("workspace://SpacesStore/", ""));
						associationElement.appendChild(associationChieldElement);
						fillDocWithNodeProperties(document, associationNodeProperties, associationChieldElement);
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
			propertyElement.appendChild(document.createTextNode(nodeProperties.get(key).toString()));
			mainElement.appendChild(propertyElement);
		}
	}
	/**
	 * Создание подключения к FTP
	 * @throws Exception 
	 */
	public void ftpConnect(String hostAddress, String log, String password) throws Exception {
		fClient = new FTPClient();
		try {
			fClient.connect(hostAddress);
			fClient.enterLocalPassiveMode();
			fClient.login(log, password);
		} catch (IOException e) {
			e.printStackTrace();
			String result = "IOException, method: ftpConnect()";
			throw new Exception(result);
		}
	}
	/**
	 * Создание директории на FTP
	 * @throws Exception 
	 */
	public void makeDirectoryInFtp(String directoryName) throws Exception {
		try {
			fClient.makeDirectory(directoryName);
			fClient.changeWorkingDirectory(directoryName);
		} catch (IOException e) {
			e.printStackTrace();
			String result = "IOException, method: makeDirectoryInFtp()";
			throw new Exception(result);
		}
	}
	/**
	 * Загрузка XML файла на FTP
	 * @throws Exception 
	 */
	public void uploadXmlToFileInFtp(Document domSource) throws Exception {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			try (OutputStream outputStream = fClient.storeFileStream(fileName)) {
				transformer.transform(new DOMSource(domSource), new StreamResult(outputStream));
			} catch (TransformerException e) {
				e.printStackTrace();
				String result = "TransformerException, method: uploadXmlToFtp()";
				throw new Exception(result);
			} catch (IOException e) {
				e.printStackTrace();
				String result = "IOException, method: uploadXmlToFtp()";
				throw new Exception(result);
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			String result = "TransformerConfigurationException, method: uploadXmlToFtp()";
			throw new Exception(result);
		}
	}
	/**
	 * Закрытие подключения к FTP
	 * @throws Exception 
	 */
	public void ftpDisconnect() throws Exception {
			try {
				fClient.logout();
				fClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				String result = "IOException, method: ftpDisconnect()";
				throw new Exception(result);
			}
	}
}