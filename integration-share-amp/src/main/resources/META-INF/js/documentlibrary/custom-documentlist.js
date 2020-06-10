// Declare namespace
if (typeof Tutorials == undefined || !Tutorials) { var Tutorials = {}; }
if (!Tutorials.custom) { Tutorials.custom = {}; }
(function()
{
  // Define constructor
  Tutorials.custom.DocumentList = function CustomDocumentList_constructor(htmlId)
  {
    Tutorials.custom.DocumentList.superclass.constructor.call(this, htmlId);
    return this;
  };

  // Extend default DocumentList
  YAHOO.extend(Tutorials.custom.DocumentList, Alfresco.DocumentList,
  {
	  

	  
	  onReady: function CustomDL_onReady()
	  {
	  // Call super class method
	  Tutorials.custom.DocumentList.superclass.onReady.call(this);
	   var elms=Dom.getElementsByClassName("left");
	    var spn=document.createElement('span');  
	
	    var spn1=document.createElement('span');  
	
	    var btn = document.createElement('button');        
	
	    var t = document.createTextNode("Reports");       
	    btn.appendChild(t);  
	    btn.onclick= this.onButtonClick;
	    spn.setAttribute('class','yui-button yui-push-button');
	     spn1.setAttribute('class','first-child');
	    spn.appendChild(spn1);
	    spn1.appendChild(btn);
	    elms[0].appendChild(spn);
	
	},



  onButtonClick: function CustomDL_onButtonClick(e, p_obj)

  {    

 //This is where control comes on click of button. You could create dynamic dialog or other logic based on your requirement.

  }
	
  });
})();