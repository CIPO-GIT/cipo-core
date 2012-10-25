package ca.gc.ic.cipo.core.dao.dto;

import java.util.List;

/**
 * Interface class providing a contract for classes which are able 
 * to assembles / disassembles (map / convert) Data Transfer Object 
 * (DTO) to / from entities model.
 * 
 * @author DenisJ1
 */
public interface EntityAssembler {   

	/**
	 * Convert the given entity model into a DTO.
	 * 
	 * @param dto  The DTO class type to convert to.
	 * @param model  Then entity model to map.
	 * 
	 * @return A DTO object with values mapped from <code>model</code>.
	 */
	public <TDto, TModel> TDto toDto(Class<TDto> dtoClass, TModel model);

	/**
	 * Convert the given entity model into a DTO.
	 * 
	 * @param dto  The DTO object to convert to.
	 * @param model  Then entity model to map.
	 * 
	 * @return A DTO object with values mapped from <code>model</code>.
	 */
	public <TDto, TModel> TDto toDto(TDto dto, TModel model);

	/**
	 * Convert the given list of entity model into a list of DTOs.
	 * 
	 * @param dto  The DTO class type to convert to.
	 * @param model  Then list of entities model to map.
	 * 
	 * @return A list of DTO objects.
	 */
	public <TDto, TModel> List<TDto> toDtos(Class<TDto> dtoClass, List<TModel> modelList);
	
	/**
	 * Convert the given Data Transfer Object (DTO) into an entity model. 
	 * 
	 * @param modelClass The entity model class to convert to.
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The entity model with values mapped from <code>dto</code>.
	 */
	public <TDto, TModel> TModel toModel(Class<TModel> modelClass, TDto dto);
	
	/**
	 * Convert the given Data Transfer Object (DTO) into an entity model. 
	 * 
	 * @param modelClass The entity model object to convert to.
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The entity model with values mapped from <code>dto</code>.
	 */
	public <TDto, TModel> TModel toModel(TModel model, TDto dto);
	
	/**
	 * Convert the given list of Data Transfer Object (DTO) into a 
	 * list of entities model. 
	 * 
	 * @param modelClass The entity model class to convert to.
	 * @param dto The DTO object to convert to an entity.
	 * 
	 * @return The list of entities model.
	 */
	public <TDto, TModel> List<TModel> toModels(Class<TModel> modelClass, List<TDto> dtoList);

	
}
