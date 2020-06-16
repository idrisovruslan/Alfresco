// Declare namespace
if (typeof Smart == undefined || !Smart) { var Smart = {}; }
if (!Smart.custom) { Smart.custom = {}; }
(function()
{
  // Define constructor
  Smart.custom.dataLists = function CustomDataLists_constructor(htmlId)
  {
    Smart.custom.dataLists.superclass.constructor.call(this, htmlId);
    return this;
  };

  // Extend default DocumentList
  YAHOO.extend(Smart.custom.dataLists, Alfresco.component.DataLists,
  {
	  

	  
	  onReady: function CustomDL_onReady()
	  {
	  // Call super class method
	  Smart.custom.dataLists.superclass.onReady.call(this);
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