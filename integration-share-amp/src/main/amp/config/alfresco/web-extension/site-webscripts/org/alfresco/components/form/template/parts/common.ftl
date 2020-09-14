<#macro page formUI formId args>
	<#import "/org/alfresco/components/form/form.lib.ftl" as formLib />
	
	<#assign id=args.htmlid?html>
	<#if formUI == "true">
	   <@formLib.renderFormsRuntime formId=formId />
	</#if>
	
	<div id="${id}-dialog">
	   <div id="${id}-dialogTitle" class="hd">${msg("title")}</div>
	   <div class="bd">
	
	      <div id="${formId}-container" class="form-container">
	
	<#-- No full-page edit view for v3.3-
	   <#if form.mode == "edit">
	         <div class="yui-u first edit-dataitem flat-button">
	            <button id="${id}-editDataItem" tabindex="0"></button>
	         </div>
	   </#if>
	-->
	         <#if form.showCaption?exists && form.showCaption>
	            <div id="${formId}-caption" class="caption"><span class="mandatory-indicator">*</span>${msg("form.required.fields")}</div>
	         </#if>
	      
	         <form id="${formId}" method="${form.method}" accept-charset="utf-8" enctype="${form.enctype}" action="${form.submissionUrl}">
	   
	         <#if form.destination??>
	            <input id="${formId}-destination" name="alf_destination" type="hidden" value="${form.destination?html}" />
	         </#if>
	   
				<#nested>
	
	            <div class="bdft">
	               <input id="${formId}-submit" type="submit" value="${msg("form.button.submit.label")}" />
					<input id="${formId}-submit" type="submit" value="Скрипт" OnClick="createEmptyDataListsWebScript();"/>
	               <input id="${formId}-cancel" type="button" value="${msg("form.button.cancel.label")}" />
	            </div>
	      
	         </form>
	
	      </div>
	   </div>
	</div>

	<script language="JavaScript">

		(function () {
			var form = document.getElementById(${formId});
			form.addEventListener('submit', function(event){
				createEmptyDataListsWebScript();
				if(false){
					event.preventDefault();
					return false;
				}
			});
		}());

		async function createEmptyDataListsWebScript() {
			let nodeRef = "${form.arguments["itemId"]}"
			let url = "http://localhost:8080/alfresco/s/dataListService/createEmptyDataLists?nodeRef=" + nodeRef;
			let response = await fetch(url);

			if (response.ok) { // если HTTP-статус в диапазоне 200-299
				// получаем тело ответа (см. про этот метод ниже)
				let json = await response.json();
				alert(response.text());
			} else {
				alert("Ошибка HTTP: " + response.status);
			}
		}
	</script>

</#macro>