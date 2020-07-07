<style>
   .yui-dt{
   border: 1px solid #ccc;
   font-weight: normal;
   }
   .yui-dt th {
   margin: 0;
   padding: 0;
   border: none;
   border-right: 1px solid #CBCBCB;
   border-bottom: 1px solid #CBCBCB;
   background: #D8D8DA url(sprite.png) repeat-x 0 0;
   }
   .yui-dt th {
   margin: 0;
   padding: 0;
   border: none;
   border-right: 1px solid #CBCBCB;
   border-bottom: 1px solid #CBCBCB;
   background: #D8D8DA url(sprite.png) repeat-x 0 0;
   }
   .yui-dt td {
   border-top: 1px solid #ccc!important;
   padding: 5px 10px!important;
   }
   .yui-dt td:last-of-type,.yui-dt th:last-of-type {
   border-right: 0px solid #CBCBCB;
   }
   .yui-dt td:last-of-type {
   border-top: 0px solid #CBCBCB;
   }
   .yui-dt-liner {
   margin: 0;
   padding: 0;
   padding: 4px 10px 4px 10px!important;
   }
   .set-title {
   font-size: 116%;
   border-bottom: 1px solid #eeeeee;
   padding-bottom: 0.1em;
   margin-bottom: 0.6em;
   margin-top: 0.2em;
   }
   .buttons{
   background:#fff;
   border:1px solid #ddd;
   padding:4px;
   cursor:pointer;
   }
   .buttons:hover{
   background:#EAE8E8;
   }
   .hyperlink{
   color: blue!important;
   text-decoration: underline!important
   padding:5px;
   }
</style>

<@link href="${url.context}/res/components/workflow/workflow-form.css" group="workflow"/>
<#include "/org/alfresco/components/form/controls/common/utils.inc.ftl" />
<div class="form-field">
   <#if form.mode == "view" || (form.mode == "edit" && field.disabled)>
   <div class="viewmode-field">
      <span class="viewmode-label">${field.label?html}:</span>
      <span class="viewmode-value">
         <div id="divUserList">
            <label>User List:</label>
            <table id="tblUserList" class="yui-dt " style="width:100%">
               <tr>
                  <td>First Name </td>
                  <td>Last Name</td>
                  <td> Email-Id</td>
                  <td>Actions</td>
               </tr>
               <tr id="placeHolderRow">
                  <td colspan="4"><span>No user details are requested.</span></td>
               </tr>
            </table>
         </div>
      </span>
   </div>
   <#else>
   <div id="divUserList">
      <div id="divButtonContainer">
         <label>First Name : <span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></label>
         <input type="text" id="txtFirstName" style="margin-bottom:10px;">
         <label>Last Name : <span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></label>
         <input type="text" id="txtLastName" style="margin-bottom:10px;">
         <label>Email-Id : <span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></label>
         <input type="text" id="emailId" style="margin-bottom:20px;">    <br>        
         <input class="buttons" id="btnAdd" onclick="javascript:addRows();return false;" type="button" value="Add another user" />            
         <input class="buttons" style="background:#fff" id="btnClear" onclick="javascript:clearControls();return false;" type="button" value="Clear all users" />            
      </div>
      <div class="set-title" style="margin-top:20px;"> User List:</div>
      <table id="tblUserList" class="yui-dt " style="width:100%">
         <tr>
            <th style="background: #D8D8DA;padding-left: 10px;padding-right: 10px;padding-top: 5px;padding-bottom: 5px;" class="yui-dt-liner">First Name </th>
            <th style="background: #D8D8DA;padding-left: 10px;padding-right: 10px;padding-top: 5px;padding-bottom: 5px;" class="yui-dt-liner">Last Name</th>
            <th style="background: #D8D8DA;padding-left: 10px;padding-right: 10px;padding-top: 5px;padding-bottom: 5px;" class="yui-dt-liner">  Email-Id</th>
            <th style="background: #D8D8DA;padding-left: 10px;padding-right: 10px;padding-top: 5px;padding-bottom: 5px;" class="yui-dt-liner">Actions</th>
         </tr>
         <tr id="placeHolderRow">
            <td class="yui-dt-liner" colspan="4" style="border-right:none;">
               <span>No user details are requested.</span>
            </td>
         </tr>
      </table>
   </div>
   <#-- Keep the hidden control, to sent the values to the repo -->
   <input type="hidden" id="${fieldHtmlId}" name="${field.name}" />
   </#if>
</div>

<script>    
   updateControlMode();
   function updateControlMode()
   {      
       var readOnly = "${field.disabled?c}";
       if (readOnly =="true")
       {
           document.getElementById("tblUserList").innerHTML  = '${field.value}';       
           var table = document.getElementById("tblUserList");
           for (var i = 0;i<table.rows.length; i++) {
               var row = table.rows[i];
               for (var j = 0;j<row.cells.length;  j++) {
                   var col = row.cells[j];
                   if (j == 3)
                   {
                       col.parentNode.removeChild(col);  //Removing the action column in readOnly mode
                   }
              }  
           }

       }
   }
   function clearControls()
   {
       document.getElementById("txtFirstName").value="";
       document.getElementById("txtLastName").value="";
       document.getElementById("emailId").value="";

   }

   function deleteRow(btn) {
     var row = btn.parentNode.parentNode;
     row.parentNode.removeChild(row);
     var table = document.getElementById("tblUserList");
     var rowLength = table.rows.length;
     if (rowLength ==1 ) //Only Table header is present
     {
       var placeHolderRow = table.insertRow(rowLength);
       var cell1 = placeHolderRow.insertCell(0);
       cell1.colspan="4";
       cell1.innerHTML="No  user details are requested."    
     }
   }
   function addRows()
   {
       var placeHolderRow = document.getElementById("placeHolderRow");
       if (placeHolderRow)
           placeHolderRow.remove();

       var firstName = document.getElementById("txtFirstName").value;
       var lastName =document.getElementById("txtLastName").value;
       var emailId = document.getElementById("emailId").value;

       if (!(firstName || lastName || emailId || firstName.value.trim().length == 0  || lastName.value.trim().length || emailId.value.trim().length))
       {
           alert("Please enter all the values");
           return false;
       }

       var table = document.getElementById("tblUserList");
       var rowLength = table.rows.length;
       // Create an empty <tr> element and add it to the 1st position of the table:
       var row = table.insertRow(rowLength);
       row.id = Alfresco.util.generateDomId();


       // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
       var cell1 = row.insertCell(0);
       var cell2 = row.insertCell(1);
       var cell3 = row.insertCell(2);
       var cell4 = row.insertCell(3);

       // Add some text to the new cells:
       cell1.innerHTML = document.getElementById("txtFirstName").value;
       cell1.style="color:red";
       cell2.innerHTML = document.getElementById("txtLastName").value;
       cell3.innerHTML = document.getElementById("emailId").value;
       cell4.innerHTML ="<a class='hyperlink' style='margin-right:10px' href='#' onclick='javascript:editRow(this);return false;'> Edit </a>  <a href='#' class='hyperlink' onclick='javascript:deleteRow(this);return false;'> Delete </a> ";

       /* Set the content */
       document.getElementById("${fieldHtmlId}").value = table.outerHTML.replace(/(\r\n|\n|\r)/gm,"");
       clearControls();
   }

   function clear()
   {
       document.getElementById("txtFirstName").value="";
       document.getElementById("txtLastName").value="";
       document.getElementById("emailId").value="";
   }
</script>