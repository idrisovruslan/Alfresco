package ioi.integration.dataListServiceWebScript;

import java.util.HashMap;
import java.util.Map;


public class DescriptionContainers {

    Map<String,Map<String,String>> descriptionContainers = new HashMap<String,Map<String,String>>();

	//TYPE OF CONTANIER
    private final String DATA_LIST_SITE_CONTAINER = "dataLists";
    //NAME OF SITES
    private final String SITE_SHORT_NAME = "smart";
    private final String SITE_SHORT_NAME_DIRECTORIES = "directories";
    
    
//NAMESPACES
    //PRICEINF
    private final String NAMESPACE_URI_PRICEINF = "http://www.ioi.com/model/priceInfo/1.0";
    private final String PREFIX_PRICEINF = "priceinf";
    //AZTKP
    private final String NAMESPACE_URI_AZTKP = "http://www.ioi.com/model/AZTKP/1.0";
    private final String PREFIX_AZTKP = "aztkp";
    //DIRECTORY
    private final String NAMESPACE_URI_DIRECTORY = "http://www.ioi.com/model/directory/1.0";
    private final String PREFIX_DIRECTORY = "dir";
    //LOTDOC
    private final String NAMESPACE_URI_LOTDOC = "http://www.ioi.com/model/lotdoc/1.0";
    private final String PREFIX_LOTDOC = "lot";
    //PROCUREMENT
    private final String NAMESPACE_URI_PROCUREMENT = "http://www.ioi.com/model/procurement/1.0";
    private final String PREFIX_PROCUREMENT = "proc";
    //SUPPLIER
    private final String NAMESPACE_URI_SUPPLIER = "http://www.ioi.com/model/supplier/1.0";
    private final String PREFIX_SUPPLIER = "supp";
    
//data list name, titel, description 
  //PRICEINF
    //ICI_SEARCH_RESULTS
    private final String DATA_LIST_NAME_ICI_SEARCH_RESULTS = "iciSearchResults";
    private final String CONTENT_MODEL_PROP_TITLE_ICI_SEARCH_RESULTS = "Результаты поиска ИЦИ";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_ICI_SEARCH_RESULTS = "";
    private final String CONTANER_QNAME_ICI_SEARCH_RESULTS = "cm:" + DATA_LIST_NAME_ICI_SEARCH_RESULTS;
    
    //CALCULATION_AND_JUSTIFICATION_NMC
    private final String DATA_LIST_NAME_CALCULATION_AND_JUSTIFICATION_NMC = "calculationAndJustificationNmc";
    private final String CONTENT_MODEL_PROP_TITLE_CALCULATION_AND_JUSTIFICATION_NMC = "Расчет-обоснование НМЦ";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_CALCULATION_AND_JUSTIFICATION_NMC = "";
    private final String CONTANER_QNAME_CALCULATION_AND_JUSTIFICATION_NMC = "cm:" + DATA_LIST_NAME_CALCULATION_AND_JUSTIFICATION_NMC;
    
  //AZTKP
    //ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY
    private final String DATA_LIST_NAME_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY = "attributiveStructureOfTheAztkpFacility";
    private final String CONTENT_MODEL_PROP_TITLE_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY = "Атрибутивный состав объекта АЗТКП";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY = "";
    private final String CONTANER_QNAME_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY = "cm:" + DATA_LIST_NAME_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY;

  //DIRECTORY
    //MANUFACTURER
    private final String DATA_LIST_NAME_MANUFACTURER = "manufacturer";
    private final String CONTENT_MODEL_PROP_TITLE_MANUFACTURER = "Изготовитель";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_MANUFACTURER = "";
    private final String CONTANER_QNAME_MANUFACTURER = "cm:" + DATA_LIST_NAME_MANUFACTURER;
    
    //SAFETY_CLASS
    private final String DATA_LIST_NAME_SAFETY_CLASS = "safetyClass";
    private final String CONTENT_MODEL_PROP_TITLE_SAFETY_CLASS = "Класс безопасности";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_SAFETY_CLASS = "";
    private final String CONTANER_QNAME_SAFETY_CLASS = "cm:" + DATA_LIST_NAME_SAFETY_CLASS;
    
    //MATERIALS_LIST
    private final String DATA_LIST_NAME_MATERIALS_LIST = "materialsList";
    private final String CONTENT_MODEL_PROP_TITLE_MATERIALS_LIST = "Перечень материалов";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_MATERIALS_LIST = "";
    private final String CONTANER_QNAME_MATERIALS_LIST = "cm:" + DATA_LIST_NAME_MATERIALS_LIST;    
    
    //PAYMENT_CONDITIONS
    private final String DATA_LIST_NAME_PAYMENT_CONDITIONS = "paymentConditions";
    private final String CONTENT_MODEL_PROP_TITLE_PAYMENT_CONDITIONS = "Условия платежа";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_PAYMENT_CONDITIONS = "";
    private final String CONTANER_QNAME_PAYMENT_CONDITIONS = "cm:" + DATA_LIST_NAME_PAYMENT_CONDITIONS;
    
    //PRICE_VALIDITY_IN_TKP
    private final String DATA_LIST_NAME_PRICE_VALIDITY_IN_TKP = "priceValidityInTkp";
    private final String CONTENT_MODEL_PROP_TITLE_PRICE_VALIDITY_IN_TKP = "Срок действия цены в ТКП";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_PRICE_VALIDITY_IN_TKP = "";
    private final String CONTANER_QNAME_PRICE_VALIDITY_IN_TKP = "cm:" + DATA_LIST_NAME_PRICE_VALIDITY_IN_TKP; 
    
  //LOTDOC
    //LOT_DOCUMENT
    private final String DATA_LIST_NAME_LOT_DOCUMENT = "lotDocument";
    private final String CONTENT_MODEL_PROP_TITLE_LOT_DOCUMENT = "Лот";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_LOT_DOCUMENT = "";
    private final String CONTANER_QNAME_LOT_DOCUMENT = "cm:" + DATA_LIST_NAME_LOT_DOCUMENT;
    
    //LOT_POSITION
    private final String DATA_LIST_NAME_LOT_POSITION = "lotPosition";
    private final String CONTENT_MODEL_PROP_TITLE_LOT_POSITION = "Позиция лота";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_LOT_POSITION = "";
    private final String CONTANER_QNAME_LOT_POSITION = "cm:" + DATA_LIST_NAME_LOT_POSITION;
    
    //POSITION
    private final String DATA_LIST_NAME_POSITION = "position";
    private final String CONTENT_MODEL_PROP_TITLE_POSITION = "Позиция";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_POSITION = "";
    private final String CONTANER_QNAME_POSITION = "cm:" + DATA_LIST_NAME_POSITION;
    
    //QUALIFIER_TYPE
    private final String DATA_LIST_NAME_QUALIFIER_TYPE = "qualifierType";
    private final String CONTENT_MODEL_PROP_TITLE_QUALIFIER_TYPE = "Единица измерения";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_QUALIFIER_TYPE = "";
    private final String CONTANER_QNAME_QUALIFIER_TYPE = "cm:" + DATA_LIST_NAME_QUALIFIER_TYPE;
    
    //DELIVERY_GRAF
    private final String DATA_LIST_NAME_DELIVERY_GRAF = "deliveryGraf";
    private final String CONTENT_MODEL_PROP_TITLE_DELIVERY_GRAF = "График поставок";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_DELIVERY_GRAF = "";
    private final String CONTANER_QNAME_DELIVERY_GRAF = "cm:" + DATA_LIST_NAME_DELIVERY_GRAF;
    
    //DELIVERY_SELL_OPT
    private final String DATA_LIST_NAME_DELIVERY_SELL_OPT = "deliverySellOpt";
    private final String CONTENT_MODEL_PROP_TITLE_DELIVERY_SELL_OPT = "Условия поставки";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_DELIVERY_SELL_OPT = "";
    private final String CONTANER_QNAME_DELIVERY_SELL_OPT = "cm:" + DATA_LIST_NAME_DELIVERY_SELL_OPT;
    
    //SALE_GRAF
    private final String DATA_LIST_NAME_SALE_GRAF = "saleGraf";
    private final String CONTENT_MODEL_PROP_TITLE_SALE_GRAF = "График платежей";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_SALE_GRAF = "";
    private final String CONTANER_QNAME_SALE_GRAF = "cm:" + DATA_LIST_NAME_SALE_GRAF;
    
    //SALE_SELL_OPT
    private final String DATA_LIST_NAME_SALE_SELL_OPT = "saleSellOpt";
    private final String CONTENT_MODEL_PROP_TITLE_SALE_SELL_OPT = "Условия оплаты";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_SALE_SELL_OPT = "";
    private final String CONTANER_QNAME_SALE_SELL_OPT = "cm:" + DATA_LIST_NAME_SALE_SELL_OPT;
    
  //PROCUREMENT
    //PROCUREMENT_DOCUMENT
    private final String DATA_LIST_NAME_PROCUREMENT_DOCUMENT = "procurementDocument";
    private final String CONTENT_MODEL_PROP_TITLE_PROCUREMENT_DOCUMENT = "Проект ЗП";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_PROCUREMENT_DOCUMENT = "";
    private final String CONTANER_QNAME_PROCUREMENT_DOCUMENT = "cm:" + DATA_LIST_NAME_PROCUREMENT_DOCUMENT;
    
    //PROCUREMENT_TYPE
    private final String DATA_LIST_NAME_PROCUREMENT_TYPE = "procurementType";
    private final String CONTENT_MODEL_PROP_TITLE_PROCUREMENT_TYPE = "Тип процедур";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_PROCUREMENT_TYPE = "";
    private final String CONTANER_QNAME_PROCUREMENT_TYPE = "cm:" + DATA_LIST_NAME_PROCUREMENT_TYPE;
    
  //SUPPLIER
    //ACKNOVLEDGED_SUPPLIER
    private final String DATA_LIST_NAME_ACKNOVLEDGED_SUPPLIER = "Acknovledged_Supplier";
    private final String CONTENT_MODEL_PROP_TITLE_ACKNOVLEDGED_SUPPLIER = "Уведомляемые поставщики";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_ACKNOVLEDGED_SUPPLIER = "";
    private final String CONTANER_QNAME_ACKNOVLEDGED_SUPPLIER = "cm:" + DATA_LIST_NAME_ACKNOVLEDGED_SUPPLIER;
    
    //SUPPLIER_BASE
    private final String DATA_LIST_NAME_SUPPLIER_BASE = "Supplier_base";
    private final String CONTENT_MODEL_PROP_TITLE_SUPPLIER_BASE = "Поставщик";
    private final String CONTENT_MODEL_PROP_DESCRIPTION_SUPPLIER_BASE = "";
    private final String CONTANER_QNAME_SUPPLIER_BASE = "cm:" + DATA_LIST_NAME_SUPPLIER_BASE;

	private Map<String,String> getDescriptionСontainer(
			String SITE_SHORT_NAME, 
			String NAMESPACE_URI, 
			String PREFIX, 
			String DATA_LIST_NAME, 
			String CONTENT_MODEL_PROP_TITLE, 
			String CONTENT_MODEL_PROP_DESCRIPTION, 
			String CONTANER_QNAME) {
    	Map<String,String> descriptionСontainer = new HashMap<String,String>();
    	descriptionСontainer.put("DATA_LIST_SITE_CONTAINER", DATA_LIST_SITE_CONTAINER);
    	descriptionСontainer.put("SITE_SHORT_NAME", SITE_SHORT_NAME);
    	descriptionСontainer.put("NAMESPACE_URI", NAMESPACE_URI);
    	descriptionСontainer.put("PREFIX", PREFIX);
    	descriptionСontainer.put("DATA_LIST_NAME", DATA_LIST_NAME);
    	descriptionСontainer.put("CONTENT_MODEL_PROP_TITLE", CONTENT_MODEL_PROP_TITLE);
    	descriptionСontainer.put("CONTENT_MODEL_PROP_DESCRIPTION", CONTENT_MODEL_PROP_DESCRIPTION);
    	descriptionСontainer.put("CONTANER_QNAME", CONTANER_QNAME);
		return descriptionСontainer;
	}
    
	public Map<String,Map<String,String>> getDescriptionContainers() {
        if (descriptionContainers.isEmpty()) {
            descriptionContainers.put("ACKNOVLEDGED_SUPPLIER", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_SUPPLIER,
                    PREFIX_SUPPLIER,
                    DATA_LIST_NAME_ACKNOVLEDGED_SUPPLIER,
                    CONTENT_MODEL_PROP_TITLE_ACKNOVLEDGED_SUPPLIER,
                    CONTENT_MODEL_PROP_DESCRIPTION_ACKNOVLEDGED_SUPPLIER,
                    CONTANER_QNAME_ACKNOVLEDGED_SUPPLIER));
            descriptionContainers.put("SUPPLIER_BASE", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_SUPPLIER,
                    PREFIX_SUPPLIER,
                    DATA_LIST_NAME_SUPPLIER_BASE,
                    CONTENT_MODEL_PROP_TITLE_SUPPLIER_BASE,
                    CONTENT_MODEL_PROP_DESCRIPTION_SUPPLIER_BASE,
                    CONTANER_QNAME_SUPPLIER_BASE));
            descriptionContainers.put("PROCUREMENT_DOCUMENT", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_PROCUREMENT,
                    PREFIX_PROCUREMENT,
                    DATA_LIST_NAME_PROCUREMENT_DOCUMENT,
                    CONTENT_MODEL_PROP_TITLE_PROCUREMENT_DOCUMENT,
                    CONTENT_MODEL_PROP_DESCRIPTION_PROCUREMENT_DOCUMENT,
                    CONTANER_QNAME_PROCUREMENT_DOCUMENT));
            descriptionContainers.put("PROCUREMENT_TYPE", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_PROCUREMENT,
                    PREFIX_PROCUREMENT,
                    DATA_LIST_NAME_PROCUREMENT_TYPE,
                    CONTENT_MODEL_PROP_TITLE_PROCUREMENT_TYPE,
                    CONTENT_MODEL_PROP_DESCRIPTION_PROCUREMENT_TYPE,
                    CONTANER_QNAME_PROCUREMENT_TYPE));
            descriptionContainers.put("LOT_DOCUMENT", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_LOT_DOCUMENT,
                    CONTENT_MODEL_PROP_TITLE_LOT_DOCUMENT,
                    CONTENT_MODEL_PROP_DESCRIPTION_LOT_DOCUMENT,
                    CONTANER_QNAME_LOT_DOCUMENT));
            descriptionContainers.put("LOT_POSITION", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_LOT_POSITION,
                    CONTENT_MODEL_PROP_TITLE_LOT_POSITION,
                    CONTENT_MODEL_PROP_DESCRIPTION_LOT_POSITION,
                    CONTANER_QNAME_LOT_POSITION));
            descriptionContainers.put("POSITION", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_POSITION,
                    CONTENT_MODEL_PROP_TITLE_POSITION,
                    CONTENT_MODEL_PROP_DESCRIPTION_POSITION,
                    CONTANER_QNAME_POSITION));
            descriptionContainers.put("QUALIFIER_TYPE", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_QUALIFIER_TYPE,
                    CONTENT_MODEL_PROP_TITLE_QUALIFIER_TYPE,
                    CONTENT_MODEL_PROP_DESCRIPTION_QUALIFIER_TYPE,
                    CONTANER_QNAME_QUALIFIER_TYPE));
            descriptionContainers.put("DELIVERY_GRAF", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_DELIVERY_GRAF,
                    CONTENT_MODEL_PROP_TITLE_DELIVERY_GRAF,
                    CONTENT_MODEL_PROP_DESCRIPTION_DELIVERY_GRAF,
                    CONTANER_QNAME_DELIVERY_GRAF));
            descriptionContainers.put("DELIVERY_SELL_OPT", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_DELIVERY_SELL_OPT,
                    CONTENT_MODEL_PROP_TITLE_DELIVERY_SELL_OPT,
                    CONTENT_MODEL_PROP_DESCRIPTION_DELIVERY_SELL_OPT,
                    CONTANER_QNAME_DELIVERY_SELL_OPT));
            descriptionContainers.put("SALE_GRAF", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_SALE_GRAF,
                    CONTENT_MODEL_PROP_TITLE_SALE_GRAF,
                    CONTENT_MODEL_PROP_DESCRIPTION_SALE_GRAF,
                    CONTANER_QNAME_SALE_GRAF));
            descriptionContainers.put("SALE_SELL_OPT", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_LOTDOC,
                    PREFIX_LOTDOC,
                    DATA_LIST_NAME_SALE_SELL_OPT,
                    CONTENT_MODEL_PROP_TITLE_SALE_SELL_OPT,
                    CONTENT_MODEL_PROP_DESCRIPTION_SALE_SELL_OPT,
                    CONTANER_QNAME_SALE_SELL_OPT));
            descriptionContainers.put("MANUFACTURER", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_DIRECTORY,
                    PREFIX_DIRECTORY,
                    DATA_LIST_NAME_MANUFACTURER,
                    CONTENT_MODEL_PROP_TITLE_MANUFACTURER,
                    CONTENT_MODEL_PROP_DESCRIPTION_MANUFACTURER,
                    CONTANER_QNAME_MANUFACTURER));
            descriptionContainers.put("SAFETY_CLASS", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_DIRECTORY,
                    PREFIX_DIRECTORY,
                    DATA_LIST_NAME_SAFETY_CLASS,
                    CONTENT_MODEL_PROP_TITLE_SAFETY_CLASS,
                    CONTENT_MODEL_PROP_DESCRIPTION_SAFETY_CLASS,
                    CONTANER_QNAME_SAFETY_CLASS));
            descriptionContainers.put("MATERIALS_LIST", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_DIRECTORY,
                    PREFIX_DIRECTORY,
                    DATA_LIST_NAME_MATERIALS_LIST,
                    CONTENT_MODEL_PROP_TITLE_MATERIALS_LIST,
                    CONTENT_MODEL_PROP_DESCRIPTION_MATERIALS_LIST,
                    CONTANER_QNAME_MATERIALS_LIST));
            descriptionContainers.put("PAYMENT_CONDITIONS", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_DIRECTORY,
                    PREFIX_DIRECTORY,
                    DATA_LIST_NAME_PAYMENT_CONDITIONS,
                    CONTENT_MODEL_PROP_TITLE_PAYMENT_CONDITIONS,
                    CONTENT_MODEL_PROP_DESCRIPTION_PAYMENT_CONDITIONS,
                    CONTANER_QNAME_PAYMENT_CONDITIONS));
            descriptionContainers.put("PRICE_VALIDITY_IN_TKP", this.getDescriptionСontainer(
                    SITE_SHORT_NAME_DIRECTORIES,
                    NAMESPACE_URI_DIRECTORY,
                    PREFIX_DIRECTORY,
                    DATA_LIST_NAME_PRICE_VALIDITY_IN_TKP,
                    CONTENT_MODEL_PROP_TITLE_PRICE_VALIDITY_IN_TKP,
                    CONTENT_MODEL_PROP_DESCRIPTION_PRICE_VALIDITY_IN_TKP,
                    CONTANER_QNAME_PRICE_VALIDITY_IN_TKP));
            descriptionContainers.put("ICI_SEARCH_RESULTS", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_PRICEINF,
                    PREFIX_PRICEINF,
                    DATA_LIST_NAME_ICI_SEARCH_RESULTS,
                    CONTENT_MODEL_PROP_TITLE_ICI_SEARCH_RESULTS,
                    CONTENT_MODEL_PROP_DESCRIPTION_ICI_SEARCH_RESULTS,
                    CONTANER_QNAME_ICI_SEARCH_RESULTS));
            descriptionContainers.put("CALCULATION_AND_JUSTIFICATION_NMC", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_PRICEINF,
                    PREFIX_PRICEINF,
                    DATA_LIST_NAME_CALCULATION_AND_JUSTIFICATION_NMC,
                    CONTENT_MODEL_PROP_TITLE_CALCULATION_AND_JUSTIFICATION_NMC,
                    CONTENT_MODEL_PROP_DESCRIPTION_CALCULATION_AND_JUSTIFICATION_NMC,
                    CONTANER_QNAME_CALCULATION_AND_JUSTIFICATION_NMC));
            descriptionContainers.put("ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY", this.getDescriptionСontainer(
                    SITE_SHORT_NAME,
                    NAMESPACE_URI_AZTKP,
                    PREFIX_AZTKP,
                    DATA_LIST_NAME_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY,
                    CONTENT_MODEL_PROP_TITLE_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY,
                    CONTENT_MODEL_PROP_DESCRIPTION_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY,
                    CONTANER_QNAME_ATTRIBUTIVE_STRUCTURE_OF_THE_AZTKP_FACILITY));
        }
        return descriptionContainers;
	}
}
