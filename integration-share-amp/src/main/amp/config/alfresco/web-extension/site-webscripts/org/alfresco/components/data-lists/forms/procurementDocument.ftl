<#import "parts/common.ftl" as common>
<#import "parts/macro.lib.ftl" as macroLib>
 
<@common.page formUI formId args>  


	<div id="${formId}-fields" class="form-fields">
	   <#list form.structure as item>
	      <#if item.kind == "set">
	      	 <div class="yui-u">
	       		<#--<@formLib.renderSet set=item /> -->
	        	<@macroLib.renderSetWithColumns set=item />
	         </div>
	      <#else>
	         <@formLib.renderField field=form.fields[item.id] />
				
				<#-- <div class="yui-g">
					<div class="yui-u first">
						<#if (item_index % 2) == 0>
						<@formLib.renderField field=form.fields[item.id] />
						</#if>
					</div>
					<div class="yui-u">
						<#if (item_index % 2) != 0>
						<@formLib.renderField field=form.fields[item.id] />
						</#if>
					</div>	
				</div> -->
         	   	
	      </#if>
	   </#list>
	</div>
	
</@common.page>

<#--
<@formLib.renderField field=form.fields["proc:procurementName"] />
-->

<#-- 
   <#list set.children as item>
      <#if item.kind == "set">
         <@renderSetWithColumns set=item />
      <#else>
         <#if (item_index % 2) == 0>
         <div class="yui-g"><div class="yui-u first">
         <#else>
         <div class="yui-u">
         </#if>
         <@formLib.renderField field=form.fields[item.id] />
         </div>
         <#if ((item_index % 2) != 0) || !item_has_next></div></#if>
      </#if>
   </#list>
-->