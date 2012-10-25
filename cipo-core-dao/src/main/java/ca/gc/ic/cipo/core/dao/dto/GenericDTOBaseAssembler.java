package ca.gc.ic.cipo.core.dao.dto;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.Mapper;

/**
 * GenericDTOAssembler is a generic class that implements the conversion 
 * of DTOs to / from entities model using the third-party library 
 * Dozer from Apache - http://dozer.sourceforge.net/.  
 * 
 * @author DenisJ1
 */
public abstract class GenericDTOBaseAssembler<TDto, TModel> implements GenericEntityAssembler<TDto, TModel> { 

	/** Logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	/** Class of the Dto type. */
	@SuppressWarnings("unchecked")
	protected Class<TDto> dtoClass = ((Class<TDto>) ((ParameterizedType) getClass().
			getGenericSuperclass()).getActualTypeArguments()[1]);

	/** Class of the model type. */
	@SuppressWarnings("unchecked")
	protected Class<TModel> modelClass = ((Class<TModel>) ((ParameterizedType) getClass().
			getGenericSuperclass()).getActualTypeArguments()[1]);


	public GenericDTOBaseAssembler() {
		super();
	}

	public Class<TDto> getDtoClazz() {
		return dtoClass;
	}
	
	public Class<TModel> getModelClazz() {
		return modelClass;
	}

	@Override
	public List<TDto> toDtos(List<TModel> modelList) {
		List<TDto> dtos= new ArrayList<TDto>();
		if (!modelList.isEmpty()) {
			for (TModel model : modelList) {
				dtos.add(toDto(model));
			}
		}
		return dtos;
	}
	
	@Override
	public List<TModel> toModels(List<TDto> dtoList) {
		List<TModel> entities = new ArrayList<TModel>();
		if (!dtoList.isEmpty()) {
			for (TDto dto : dtoList) {
				entities.add(toModel(dto));
			}
		}
		return entities;
	}
}
