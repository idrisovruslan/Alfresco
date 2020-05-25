<#include "common/picker.inc.ftl" />
<#import "/org/alfresco/components/form/form.lib.ftl" as formLib />

<#assign controlId = fieldHtmlId + "-cntrl">

<script type="text/javascript">//<![CDATA[
(function()
{
   <@renderPickerJS field "picker" />
   picker.setOptions(
   {
   <#if field.control.params.showTargetLink??>
      showLinkToTarget: ${field.control.params.showTargetLink},
      <#if page?? && page.url.templateArgs.site??>
         targetLinkTemplate: "${url.context}/page/site/${page.url.templateArgs.site!""}/document-details?nodeRef={nodeRef}",
      <#else>
         targetLinkTemplate: "${url.context}/page/document-details?nodeRef={nodeRef}",
      </#if>
   </#if>
   <#if field.control.params.allowNavigationToContentChildren??>
      allowNavigationToContentChildren: ${field.control.params.allowNavigationToContentChildren},
   </#if>
      itemType: "${field.endpointType}",
      multipleSelectMode: ${field.endpointMany?string},
      parentNodeRef: "alfresco://company/home",
   <#if field.control.params.rootNode??>
      rootNode: "${field.control.params.rootNode}",
   </#if>
      itemFamily: "node",
      displayMode: "${field.control.params.displayMode!"items"}"
   });
})();
//]]></script>

<div class="form-field">

   <#if form.mode == "view">
      <div id="${controlId}" class="viewmode-field">
      
         <#if (field.endpointMandatory!false || field.mandatory!false) && field.value == "">
            <span class="incomplete-warning"><img src="${url.context}/res/components/form/images/warning-16.png" title="${msg("form.field.incomplete")}" /><span>
         </#if>
         <span class="viewmode-label">${field.label?html}:</span>
         <span id="${controlId}-currentValueDisplay" class="viewmode-value current-values"></span>
      </div>
   <#else>
      <label for="${controlId}">${field.label?html}:<#if field.endpointMandatory!false || field.mandatory!false><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      
      <div id="${controlId}" class="object-finder">
         
         
         
         
         
         <div><h1>test1</h1></div>
         <div id="${controlId}-currentValueDisplay" class="current-values"></div>
         
   
         


<div>     
	<#if field.id??>               
		${field.id} + "field.id"<br><br>
	</#if>
	<#if field.type??>               
		${field.type} + "field.type"<br><br>
	</#if>
	<#if field.value??>               
		${field.value} + "field.value"<br><br>
	</#if>
	<#if field.name??>               
		${field.name} + "field.name"<br><br>
	</#if>
	
	

	<#if field.params??>               
		${field.params} + "field.params"<br><br>
	</#if>	
	<#if field.options??>               
		${field.options} + "field.options"<br><br>
	</#if>	
	<#if field.fields??>               
		${field.fields} + "field.fields"<br><br>
	</#if>
	
	



<#if form.structure??>  	
   <#list form.structure as item>
		${item} + ${item_index} + ${item.kind}<br><br>
   </#list>	
</#if>	

</div>        
         
         

         
         <#if field.disabled == false>
         
            <input type="hidden" id="${fieldHtmlId}" name="-" value="${field.value?html}" />
            
            <input type="hidden" id="${controlId}-added" name="${field.name}_added" />
            
            <input type="hidden" id="${controlId}-removed" name="${field.name}_removed" />
            
            <div id="${controlId}-itemGroupActions" class="show-picker"></div>
         
            <@renderPickerHTML controlId />
            
         </#if>
      </div>
   </#if>
</div>

