package ioi.integration.formProcessorFilters;

import java.util.List;
import java.util.Map;

import org.alfresco.repo.forms.Form;
import org.alfresco.repo.forms.FormData;
import org.alfresco.repo.forms.processor.AbstractFilter;
import org.alfresco.service.cmr.repository.NodeRef;

public class TaskFormFilter extends AbstractFilter<Object, NodeRef> {

	@Override
	public void beforeGenerate(Object item, List<String> fields, List<String> forcedFields, Form form,
			Map<String, Object> context) {
		System.out.println("beforeGenerate!");
	}

	@Override
	public void afterGenerate(Object item, List<String> fields, List<String> forcedFields, Form form,
			Map<String, Object> context) {
		System.out.println("afterGenerate!");		
	}

	@Override
	public void beforePersist(Object item, FormData data) {
		System.out.println("beforePersist!");		
	}

	@Override
	public void afterPersist(Object item, FormData data, NodeRef persistedObject) {
		System.out.println("afterPersist!");		
	}
}