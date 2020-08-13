package ioi.integration.dataListServiceWebScript;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.model.DataListModel;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class CreateDataListWebScript extends DeclarativeWebScript {
	
    private final static String NAMESPACE_URI = "http://www.ioi.com/model/priceInfo/1.0";
    private final String DATA_LIST_SITE_CONTAINER = "dataLists";
    private final QName PRICEINF_PROJECT_LIST_ITEM_TYPE = QName.createQName(NAMESPACE_URI, "iciSearchResults");

    private ServiceRegistry serviceRegistry;

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    protected Map<String, Object> executeImpl(
            WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();

        // Name of the out-of-the-box Sample site (Web Site Design Project)
        String siteShortName = "smart";

        // Name of the data list we are about to create
        String dataListName = "iciSearchResults";
        // Get or create the site data list container (assumes that the site exists)
        
        if (!serviceRegistry.getSiteService().hasContainer(siteShortName, DATA_LIST_SITE_CONTAINER)) {
        	serviceRegistry.getSiteService().createContainer(siteShortName, DATA_LIST_SITE_CONTAINER,
                    ContentModel.TYPE_CONTAINER, null);
        }
        
        NodeRef dataListContainerNodeRef = serviceRegistry.getSiteService().getContainer(
                siteShortName, DATA_LIST_SITE_CONTAINER);

        
        // Check that the data list name is not already used
        NodeRef contaner = serviceRegistry.getNodeService().getChildByName(
                dataListContainerNodeRef, ContentModel.ASSOC_CONTAINS, dataListName);
        Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
        if (contaner == null) {
            // Create the data list contaner
            properties.put(ContentModel.PROP_NAME, dataListName);
            properties.put(ContentModel.PROP_TITLE, "Результаты поиска ИЦИ");
            properties.put(ContentModel.PROP_DESCRIPTION, "A list of projects that has names starting with A");
            properties.put(DataListModel.PROP_DATALIST_ITEM_TYPE, "priceinf:" + PRICEINF_PROJECT_LIST_ITEM_TYPE.getLocalName());
            
            NodeRef datalistNodeRef = serviceRegistry.getNodeService().createNode(
                    dataListContainerNodeRef,
                    ContentModel.ASSOC_CONTAINS,
                    QName.createQName("cm:projectListA"),
                    DataListModel.TYPE_DATALIST,
                    properties).getChildRef();

                if (datalistNodeRef != null) {
                	model.put("msg", "Создали контейнер:  " + datalistNodeRef);
    			}
                else {
                	model.put("msg", "Не создали контейнер");
                }

        } else {
        	// Create the data list
            properties.put(QName.createQName(NAMESPACE_URI, "iciName"), "iciName");
            properties.put(QName.createQName(NAMESPACE_URI, "iciKind"), "iciKind");
            properties.put(QName.createQName(NAMESPACE_URI, "resource"), "resource");
            properties.put(QName.createQName(NAMESPACE_URI, "quantity"), "quantity");
            properties.put(QName.createQName(NAMESPACE_URI, "priceUnitWithoutVAT"), 1);
            properties.put(QName.createQName(NAMESPACE_URI, "sumWithoutVAT"), 1);
            properties.put(QName.createQName(NAMESPACE_URI, "costOfDeliveryWithoutVAT"), 1);
            properties.put(QName.createQName(NAMESPACE_URI, "sumWithDeliveryWithoutVAT"), 1);
            properties.put(QName.createQName(NAMESPACE_URI, "priceDeliveryWithAdjustmentsWithoutVAT"), 1);
            properties.put(QName.createQName(NAMESPACE_URI, "sumShippingWithAdjustmentsWithoutVAT"), 1);            
            properties.put(QName.createQName(NAMESPACE_URI, "iciCurrency"), "RUB");
            properties.put(QName.createQName(NAMESPACE_URI, "iciVatRate"), "0");
            
            NodeRef projectANodeRef = serviceRegistry.getNodeService().createNode(
            		contaner, ContentModel.ASSOC_CONTAINS,
                    QName.createQName("cm:projectA1"),
                    PRICEINF_PROJECT_LIST_ITEM_TYPE,
                    properties).getChildRef();
            
            if (projectANodeRef != null) {
            	model.put("msg", "Создали лист:  " + projectANodeRef + "в" + contaner);
			}
            else {
            	model.put("msg", "Не создали лист");
            }
        }
        return model;
    }
}