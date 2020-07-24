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
   
            <div id="${formId}-fields" class="form-fields">

               <#list form.structure as item>
                  <#if item.kind == "set">
                     <@formLib.renderSet set=item />
                  <#else>
                     <@formLib.renderField field=form.fields[item.id] />
                  </#if>
               </#list>

            </div>
            


            

            <div class="bdft">
               <input id="${formId}-submit" type="submit" value="${msg("form.button.submit.label")}" />
               &nbsp;<input id="${formId}-cancel" type="button" value="${msg("form.button.cancel.label")}" />
            </div>
      
         </form>

      </div>
   </div>
</div>


<script type="text/javascript">
   var nodeRef = "${form.arguments["itemId"]}",
      formMode = '${form.mode}',
      varId = '${id}',
      varFormId = '${formId}';
   
   var nodeRef2 = "${form.fields.assoc_lot_positionQualifierLink.value}";
   
   function loadScript(url, callback) {
       // adding the script tag to the head as suggested before
      var head = document.getElementsByTagName('head')[0],
	 script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = url;
      // then bind the event to the callback function 
      // there are several events for cross browser compatibility
      script.onreadystatechange = callback;
      script.onload = callback;
      // fire the loading
      head.appendChild(script);
   }
   
   function tabbedMain() {
      initAcc(nodeRef2);
   }
   
   loadScript("/share/res/js/form/template/dataitem/dataitem.js", tabbedMain);
   
</script>