package ioi.integration.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;

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
     * Закрытие подключения к FTP
     * @throws Exception
     */
    public void readXmlFromFtp() throws Exception {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("BookCatalog.xml");

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    }
}
