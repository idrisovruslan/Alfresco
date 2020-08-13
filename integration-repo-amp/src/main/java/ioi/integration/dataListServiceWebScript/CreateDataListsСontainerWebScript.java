package ioi.integration.dataListServiceWebScript;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.alfresco.model.ContentModel;
import org.alfresco.model.DataListModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class CreateDataListsСontainerWebScript extends DeclarativeWebScript {

	private ServiceRegistry serviceRegistry;

	DescriptionСontainers descСontainers = new DescriptionСontainers();

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		Map<String, Map<String, String>> descriptionСontainers = descСontainers.getDescriptionСontainers();
		Map<String, Object> model = new HashMap<String, Object>();

		for (Entry<String, Map<String, String>> entry : descriptionСontainers.entrySet()) {
			String key = entry.getKey();
			Map<String, String> description = entry.getValue();
			QName projectListItemType = QName.createQName(description.get("NAMESPACE_URI"), description.get("DATA_LIST_NAME"));

			if (!serviceRegistry.getSiteService().hasContainer(description.get("SITE_SHORT_NAME"),
					description.get("DATA_LIST_SITE_CONTAINER"))) {
				serviceRegistry.getSiteService().createContainer(description.get("SITE_SHORT_NAME"),
						description.get("DATA_LIST_SITE_CONTAINER"), ContentModel.TYPE_CONTAINER, null);
			}
			NodeRef dataListContainerNodeRef = serviceRegistry.getSiteService()
					.getContainer(description.get("SITE_SHORT_NAME"), description.get("DATA_LIST_SITE_CONTAINER"));
			NodeRef contaner = serviceRegistry.getNodeService().getChildByName(dataListContainerNodeRef,
					ContentModel.ASSOC_CONTAINS, description.get("DATA_LIST_NAME"));
			
			if (contaner == null) {
				Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
				// Create the data list container
				properties.put(ContentModel.PROP_NAME, description.get("DATA_LIST_NAME"));
				properties.put(ContentModel.PROP_TITLE, description.get("CONTENT_MODEL_PROP_TITLE"));
				properties.put(ContentModel.PROP_DESCRIPTION, description.get("CONTENT_MODEL_PROP_DESCRIPTION"));
				properties.put(DataListModel.PROP_DATALIST_ITEM_TYPE,
						description.get("PREFIX") + ":" + projectListItemType.getLocalName());

				NodeRef datalistNodeRef = serviceRegistry.getNodeService()
						.createNode(dataListContainerNodeRef, ContentModel.ASSOC_CONTAINS,
								QName.createQName(description.get("CONTANER_QNAME")), DataListModel.TYPE_DATALIST, properties)
						.getChildRef();

				if (datalistNodeRef != null) {
					continue;
				} else {
					break;
				}
			}
		}
		model.put("msg", "Отработал");
		return model;
	}
}