package ioi.integration.dataListServiceWebScript;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CreateEmptyDataListsWebScript extends DeclarativeWebScript {

    private ServiceRegistry serviceRegistry;

    private DescriptionContainers descContainers;

    public void setDescContainers(DescriptionContainers descContainers) { this.descContainers = descContainers; }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    Map<String, Map<String, String>> descriptionContainers;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();
        descriptionContainers = descContainers.getDescriptionContainers();
        Map<String, String> dataListProperties = new HashMap<String, String>();
        NodeRef procurementDocumentNodeRef = new NodeRef(req.getParameter("nodeRef"));

        NodeRef lotDocumentNodeRef = createDataList(dataListProperties, "LOT_DOCUMENT");
        NodeRef lotPositionNodeRef = createDataList(dataListProperties, "LOT_POSITION");
        NodeRef calculationAndJustificationNmcNodeRef = createDataList(dataListProperties, "CALCULATION_AND_JUSTIFICATION_NMC");
        NodeRef iciSearchResultsNodeRef = createDataList(dataListProperties, "ICI_SEARCH_RESULTS");
        NodeRef attributiveStructureOfTheAztkpFacilityNodeRef = createDataList(dataListProperties, "ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY");
        createAssociation("LOT_DOCUMENT", "PROCUREMENT_DOCUMENT", lotDocumentNodeRef, procurementDocumentNodeRef, "lotLink");
        createAssociation("LOT_POSITION", "LOT_DOCUMENT", lotPositionNodeRef, lotDocumentNodeRef, "lotPositionLink");
        createAssociation("LOT_DOCUMENT", "CALCULATION_AND_JUSTIFICATION_NMC", lotDocumentNodeRef, calculationAndJustificationNmcNodeRef, "lotLink");
        createAssociation("ICI_SEARCH_RESULTS", "CALCULATION_AND_JUSTIFICATION_NMC", iciSearchResultsNodeRef, calculationAndJustificationNmcNodeRef, "iciSearchResultsLink");
        createAssociation("LOT_DOCUMENT", "ICI_SEARCH_RESULTS", lotDocumentNodeRef, iciSearchResultsNodeRef, "lotIciLink");
        createAssociation("LOT_DOCUMENT", "ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY", lotDocumentNodeRef, attributiveStructureOfTheAztkpFacilityNodeRef, "lotDocumentLink");
        model.put("msg", "ok");
        return model;
    }

    public NodeRef createDataList(Map<String, String> dataListProperties, String nameContainer) {
        Map<String, String> descriptionContainer = descriptionContainers.get(nameContainer);
        if (!serviceRegistry.getSiteService().hasContainer(descriptionContainer.get("SITE_SHORT_NAME"), descriptionContainer.get("DATA_LIST_SITE_CONTAINER"))) {
            serviceRegistry.getSiteService().createContainer(descriptionContainer.get("SITE_SHORT_NAME"), descriptionContainer.get("DATA_LIST_SITE_CONTAINER"),
                    ContentModel.TYPE_CONTAINER, null);
        }

        final QName PROJECT_LIST_ITEM_TYPE = QName.createQName(
                descriptionContainer.get("NAMESPACE_URI"),
                descriptionContainer.get("DATA_LIST_NAME"));

        NodeRef dataListContainerNodeRef = serviceRegistry.getSiteService().getContainer(descriptionContainer.get("SITE_SHORT_NAME"),
                descriptionContainer.get("DATA_LIST_SITE_CONTAINER"));

        // Check that the data list name is not already used
        NodeRef contaner = serviceRegistry.getNodeService().getChildByName(dataListContainerNodeRef,
                ContentModel.ASSOC_CONTAINS, descriptionContainer.get("DATA_LIST_NAME"));

        Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
        if (contaner != null) {
            //заполнение и создание даталиста
            for (String key : dataListProperties.keySet()) {
                properties.put(QName.createQName(descriptionContainer.get("NAMESPACE_URI"), key), dataListProperties.get(key));
            }

            NodeRef childNodeRef = serviceRegistry
                    .getNodeService().createNode(contaner, ContentModel.ASSOC_CONTAINS,
                            QName.createQName("cm:" + descriptionContainer.get("PREFIX") + descriptionContainer.get("DATA_LIST_NAME")), PROJECT_LIST_ITEM_TYPE, properties)
                    .getChildRef();

            return childNodeRef;
        }
        return null;
    }
    public void createAssociation(String nameChieldContainer, String nameMainContainer,NodeRef childNodeRef, NodeRef mainNodeRef, String assocLocalName) {
        Map<String, String> descriptionContainer = descriptionContainers.get(nameChieldContainer);
        Map<String, String> descriptionMainContainer = descriptionContainers.get(nameMainContainer);

        serviceRegistry.getNodeService().createAssociation(mainNodeRef, childNodeRef,
                QName.createQName(descriptionMainContainer.get("NAMESPACE_URI"), assocLocalName));
    }
}