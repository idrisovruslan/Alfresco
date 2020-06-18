model.jsonModel = {
   services: [
      "alfresco/services/NavigationService",
      "alfresco/services/LogoutService",
      "alfresco/services/DocumentService",
      "alfresco/services/ActionService",
      "alfresco/services/UploadService",
      "alfresco/services/DataListService"
      // Add more services here !!!
   ],
   widgets: [
	   {
		   name: "alfresco/lists/views/layouts/Grid",
		   config: {
		     rootNode: "alfresco://user/home",
		     rawData: true,
		     
		   }
		 }
               // Add more widgets here !!!
            
   ]
};