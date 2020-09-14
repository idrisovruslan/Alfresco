package ioi.integration.dataListServiceWebScript;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.GregorianCalendar;
import ioi.integration.ftp.Ftp;
import org.alfresco.model.ContentModel;
import org.alfresco.model.DataListModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//Создает результаты поиска ИЦИ из ФТП. !!!!!не доработан!!!!!:
//      имена папок и файлов заданы жестко, метод readXmlFromFtp()
//      жестко задано имя "tzDate"
//      скрипт отвечает и за создание контейнера и за создание даталиста(за создание должен отвеать CreateDataListContainerWebScript

public class CreateDataListWebScript extends DeclarativeWebScript {

    private ServiceRegistry serviceRegistry;

    private DescriptionContainers descContainers;

    private Ftp ftp;

    public void setDescContainers(DescriptionContainers descContainers) { this.descContainers = descContainers; }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    public void setFtp(Ftp ftp) { this.ftp = ftp; }

    private final String WORKING_DIRECTORY_NAME = "2df200ae-188e-4777-a3e7-2d6c092bfae6";
    private final String FILE_NAME = "2df200ae-188e-4777-a3e7-2d6c092bfae6_property.xml";

    Map<String, Map<String, String>> descriptionContainers;
    Map<String, String> iciSearchResultsDescription;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();

        descriptionContainers = descContainers.getDescriptionContainers();
        iciSearchResultsDescription = descriptionContainers.get("ICI_SEARCH_RESULTS");

        Map<String, String> dataListProperties = readXmlFromFtp();
        createDataList(dataListProperties);

        model.put("msg", "ok");
        return model;
    }

    public Map<String, String> readXmlFromFtp() {
        Map<String, String> dataListProperties = null;
        try {
            ftp.ftpConnect();
            dataListProperties = ftp.readXmlFromFtp(WORKING_DIRECTORY_NAME,
                    FILE_NAME);
            ftp.ftpDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataListProperties;
    }


    public void createDataList(Map<String, String> dataListProperties) {
        if (!serviceRegistry.getSiteService().hasContainer(iciSearchResultsDescription.get("SITE_SHORT_NAME"), iciSearchResultsDescription.get("DATA_LIST_SITE_CONTAINER"))) {
            serviceRegistry.getSiteService().createContainer(iciSearchResultsDescription.get("SITE_SHORT_NAME"), iciSearchResultsDescription.get("DATA_LIST_SITE_CONTAINER"),
                    ContentModel.TYPE_CONTAINER, null);
        }

        final QName PRICEINF_PROJECT_LIST_ITEM_TYPE = QName.createQName(
                iciSearchResultsDescription.get("NAMESPACE_URI"),
                iciSearchResultsDescription.get("DATA_LIST_NAME"));

        NodeRef dataListContainerNodeRef = serviceRegistry.getSiteService().getContainer(iciSearchResultsDescription.get("SITE_SHORT_NAME"),
                iciSearchResultsDescription.get("DATA_LIST_SITE_CONTAINER"));

        System.out.println(iciSearchResultsDescription.get("DATA_LIST_NAME"));

        // Check that the data list name is not already used
        NodeRef contaner = serviceRegistry.getNodeService().getChildByName(dataListContainerNodeRef,
                ContentModel.ASSOC_CONTAINS, iciSearchResultsDescription.get("DATA_LIST_NAME"));


        Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
        if (contaner == null) {
            //создание контейнера даталистов
            properties.put(ContentModel.PROP_NAME, iciSearchResultsDescription.get("DATA_LIST_NAME"));
            properties.put(ContentModel.PROP_TITLE, iciSearchResultsDescription.get("DATA_LIST_NAME_ICI_SEARCH_RESULTS"));
            properties.put(ContentModel.PROP_DESCRIPTION, iciSearchResultsDescription.get("CONTENT_MODEL_PROP_DESCRIPTION_ICI_SEARCH_RESULTS"));
            properties.put(DataListModel.PROP_DATALIST_ITEM_TYPE,
                    iciSearchResultsDescription.get("PREFIX_PRICEINF") + PRICEINF_PROJECT_LIST_ITEM_TYPE.getLocalName());

            NodeRef datalistNodeRef = serviceRegistry.getNodeService()
                    .createNode(dataListContainerNodeRef, ContentModel.ASSOC_CONTAINS,
                            QName.createQName("cm:priceinf_ici_" + iciSearchResultsDescription.get("DATA_LIST_NAME")), DataListModel.TYPE_DATALIST, properties)
                    .getChildRef();

        } else {
            //заполнение и создание даталиста
            for (String key : dataListProperties.keySet()) {
                if (key.equals("tzDate")) {
                    GregorianCalendar startDate = new GregorianCalendar();
                    try {
                        simpleDateFormat.isLenient();
                        startDate.setTime(simpleDateFormat.parse(dataListProperties.get(key)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    properties.put(QName.createQName(iciSearchResultsDescription.get("NAMESPACE_URI"), key), dataListProperties.get(key));
                }
            }

            NodeRef projectANodeRef = serviceRegistry
                    .getNodeService().createNode(contaner, ContentModel.ASSOC_CONTAINS,
                            QName.createQName("cm:priceinf_ici_" + dataListProperties.get("name")), PRICEINF_PROJECT_LIST_ITEM_TYPE, properties)
                    .getChildRef();

        }
    }
}