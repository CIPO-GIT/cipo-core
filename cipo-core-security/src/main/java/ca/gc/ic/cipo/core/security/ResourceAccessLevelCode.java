package ca.gc.ic.cipo.core.security;

/**
 * Define the resource privilege levels. 
 * [select * from comdb?.comtb002 where fk_cdtb_acronym_id='PRIVILEGELVL']
 * 
 * @author J.Denis
 *
 */
public enum ResourceAccessLevelCode {

	/** Resource access level code for read only public data privilege. */
	READ_ONLY_PUBLIC ("01", "PRIVILEGELVL", "Read only public data", "Lecture de données publique"),
	
	/** Resource access level code for read only all data privilege. */
	READ_ONLY_ALL    ("02", "PRIVILEGELVL", "Read only all data",    "Lecture seulement"),

	/** Resource access level code for update data privilege. */
	UPDATE           ("03", "PRIVILEGELVL", "Update",                "Mise à jour"),

	/** Resource access level code for delete data privilege. */
	DELETE           ("04", "PRIVILEGELVL", "Delete",                "Suppression");
	
    /** Element value (COMTB002.ELEMENT_VALUE). */
    private String elementValue;

    /** Code table acronym (COMTB002.FK_CDTB_ACRONYM_ID). */
    private String codeTableAcronym;
    
    /** English description of the code (COMTB002.NAME_EN). */
    private String englishDescription;
    
    /** French description of the code (COMTB002.NAME_FR). */
    private String frenchDescription;
    
    /**
     * Constructor.
     * 
     * @param elementValue the database code value
     * @param codeTableAcronym the code table acronym
     * @param englishDescription an english description
     * @param frenchDescription a French description
     */
    private ResourceAccessLevelCode(String elementValue, String codeTableAcronym, 
    		String englishDescription, String frenchDescription) {
    	this.elementValue = elementValue;
    	this.codeTableAcronym = codeTableAcronym;
    	this.englishDescription = englishDescription;
    	this.frenchDescription = frenchDescription;
    } // End of the EventType method.

    /**
     *  @return The value for the event.
     */
    public String getValue() {
    	return elementValue;
    } // End of the getValue method.
    
    /**
     * @return The code table acronym.
     */
    public String getCodeTableAcronym() {
    	return codeTableAcronym;
    } // End of the getCodeTableAcronym method.
    
    /**
     * @return The English description of this element.
     */
    public String getEnglishDescription() {
    	return englishDescription;
    } // End of the getEnglishDescription method.

    /**
     * @return The French description of this element.
     */
    public String getFrenchDescription() {
    	return frenchDescription;
    } // End of the getFrenchDescription method.    

} // End of the ResourceAccessLevelCode method.
