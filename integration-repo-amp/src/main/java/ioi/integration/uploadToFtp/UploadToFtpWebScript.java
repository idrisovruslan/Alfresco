package ioi.integration.uploadToFtp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.alfresco.service.ServiceRegistry;
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

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			this.ftpConn("192.168.3.93", "ftpuser", "pass");
		} catch (Exception ex) {
			Logger.getLogger(UploadToFtpWebScript.class.getName()).log(Level.SEVERE, null, ex);
		}
		model.put("msg", "ok");
		return model;
	}

	public void ftpConn(String hostAddress, String log, String password)
			throws FileNotFoundException, TransformerFactoryConfigurationError, TransformerException {
		FTPClient fClient = new FTPClient();
		try {
			fClient.connect(hostAddress);
			fClient.enterLocalPassiveMode();
			fClient.login(log, password);

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			ParamLangXML();
			OutputStream outputStream = fClient.storeFileStream("proxy.xml");
			transformer.transform(new DOMSource(WriteParamXML()), new StreamResult(outputStream));
			outputStream.close();
			fClient.logout();
			fClient.disconnect();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public void ParamLangXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	DocumentBuilder builder;

	/**
	 * Запись настроек в XML файл
	 */
	public Document WriteParamXML() {

		Document doc = builder.newDocument();
		Element RootElement = doc.createElement("proxy");

		Element NameElementTitle = doc.createElement("use");
		NameElementTitle.appendChild(doc.createTextNode("true"));
		RootElement.appendChild(NameElementTitle);

		Element NameElementCompile = doc.createElement("host");
		NameElementCompile.appendChild(doc.createTextNode("127.0.0.1"));
		RootElement.appendChild(NameElementCompile);

		Element NameElementRuns = doc.createElement("port");
		NameElementRuns.appendChild(doc.createTextNode("8080"));
		RootElement.appendChild(NameElementRuns);

		doc.appendChild(RootElement);
		return doc;
	}
}
