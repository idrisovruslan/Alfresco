package ioi.integration.uploadToFtp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
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
	
	Map<QName,Serializable> fileProperties;
	String fileName;
	NodeRef nodeRef;
	DocumentBuilder builder;
	
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		
		ParamLangXML();
		
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			nodeRef = new NodeRef(req.getParameter("nodeRef"));
			fileName = ((String) serviceRegistry.getNodeService().getProperty(nodeRef, ContentModel.PROP_NAME)).trim().toLowerCase();
			fileProperties = serviceRegistry.getNodeService().getProperties(nodeRef);
			this.ftpConn("192.168.3.93", "user", "pass", WriteParamXML(nodeRef), fileName);
		} catch (MalformedNodeRefException ex) {
			model.put("msg", "Не верный формат NodeRef: " + req.getParameter("nodeRef"));
			return model;
		} catch (InvalidNodeRefException ex) {
			model.put("msg", "Не удалось получить параметры: " + req.getParameter("nodeRef"));
			return model;
		} catch (TransformerFactoryConfigurationError|TransformerException|IOException ex) {
			model.put("msg", "Не удалось выполнить метод \"ftpConn\"");
			return model;
		}

		model.put("msg", "Загрузка на FTP выполнена");
		return model;
	}
	/**
	 * Создание подключения и загрузка файла
	 */
	public void ftpConn(String hostAddress, String log, String password, Document domSource, String fileName)
			throws TransformerFactoryConfigurationError, TransformerException, SocketException, IOException {
		FTPClient fClient = new FTPClient();
		fClient.connect(hostAddress);
		fClient.enterLocalPassiveMode();
		fClient.login(log, password);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();

		try (OutputStream outputStream = fClient.storeFileStream(fileName + ".xml")) {
			transformer.transform(new DOMSource(domSource), new StreamResult(outputStream));
		}

		fClient.logout();
		fClient.disconnect();
	}
	/**
	 * Инициализация билдера для XML
	 */
	public void ParamLangXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Запись настроек в XML файл
	 */
	public Document WriteParamXML(NodeRef nodeRef) {
		Document doc = builder.newDocument();
		Element rootElement = doc.createElement(fileName);
		
		for (QName key : fileProperties.keySet()) {
			Element propertyElement = doc.createElement(key.getLocalName());
			propertyElement.appendChild(doc.createTextNode(fileProperties.get(key).toString()));
			rootElement.appendChild(propertyElement);
		}

		doc.appendChild(rootElement);
		return doc;
	}
}