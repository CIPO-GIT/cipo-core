package ca.gc.ic.cipo.core.dao.dto;

import java.util.List;

/**
 * Interface class providing a contract for classes which are able 
 * to assembles / disassembles (map / convert) Data Transfer Object 
 * (DTO) to / from entities model.
 * 
 * @author DenisJ1
 */
public interface GenericEntityAssembler<TDto, TModel> {

	public Class<TDto> getDtoClazz();   
	
	public Class<TModel> getModelClazz();
		
	/**
	 * Convert the given entity model into a DTO.
	 * 
	 * @param model  Then entity model to map.
	 * 
	 * @return A DTO object with values mapped from <code>model</code>.
	 */
	public TDto toDto(TModel model);

	/**
	 * Convert the given entity model into a DTO.
	 * 
	 * @param dto  The DTO object to convert to.
	 * @param model  Then entity model to map.
	 * 
	 * @return A DTO object with values mapped from <code>model</code>.
	 */
	public TDto toDto(TDto dto, TModel model);

	/**
	 * Convert the given list of entity model into a list of DTOs.
	 * 
	 * @param model  Then list of entities model to map.
	 * 
	 * @return A list of DTO objects.
	 */
	public List<TDto> toDtos(List<TModel> modelList);
	
	/**
	 * Convert the given Data Transfer Object (DTO) into an entity model. 
	 * 
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The entity model with values mapped from <code>dto</code>.
	 */
	public TModel toModel(TDto dto);
	
	/**
	 * Convert the given Data Transfer Object (DTO) into an entity model. 
	 * 
	 * @param model The entity model object to convert to.
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The entity model with values mapped from <code>dto</code>.
	 */
	public TModel toModel(TModel model, TDto dto);
	
	/**
	 * Convert the given list of Data Transfer Object (DTO) into a 
	 * list of entities model. 
	 * 
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The list of entities model.
	 */
	public List<TModel> toModels(List<TDto> dtoList);	
}
