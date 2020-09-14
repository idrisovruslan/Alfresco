package ioi.integration.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Ftp {

    FTPClient fClient;

    private String hostAddress;
    private String login;
    private String password;

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Создание подключения к FTP
     *
     * @throws Exception
     */
    public void ftpConnect() throws Exception {
        fClient = new FTPClient();
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(login, password);
        } catch (IOException e) {
            e.printStackTrace();
            String result = "IOException, method: ftpConnect()";
            throw new Exception(result);
        }
    }

    /**
     * Создание директории на FTP
     *
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
     *
     * @throws Exception
     */
    public void uploadXmlToFileInFtp(Document domSource, String fileName) throws Exception {
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
     *
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

    /**
     * Считывание значений XML из FTP
     *
     * @throws Exception
     */
    public Map<String, String> readXmlFromFtp(String workingDirectoryName, String fileName) throws Exception {

        Map<String, String> dataListProperties = new HashMap<String, String>();

        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Выбор нужной директории
            fClient.changeWorkingDirectory(workingDirectoryName);
            // Создается дерево DOM документа из файла

            InputStream is = fClient.retrieveFileStream(fileName);
            Document document = documentBuilder.parse(is);
            is.close();
            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList properties = root.getChildNodes();

            for (int i = 0; i < properties.getLength(); i++) {
                Node propertie = properties.item(i);
                if (propertie.getNodeType() != Node.TEXT_NODE) {
                    dataListProperties.put(propertie.getNodeName(),propertie.getTextContent());
                }
//				if (propertie.getNodeName() != "associations") {
//				} else {
//					NodeList associations = propertie.getChildNodes();
//					for (int j = 0; j < associations.getLength(); j++) {
//						Node association = associations.item(j);
//						NodeList associationProperties = association.getChildNodes();
//						for (int k = 0; k < associationProperties.getLength(); k++) {
//							Node associationPropertie = associationProperties.item(k);
//						}
//					}
//				}

            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return dataListProperties;
    }
}
