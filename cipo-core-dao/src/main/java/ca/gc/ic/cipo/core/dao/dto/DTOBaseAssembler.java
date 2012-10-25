package ca.gc.ic.cipo.core.dao.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTOBaseAssembler is an abstract class providing utility function to
 * all EntityAssembler that implements the conversion of DTOs to / from 
 * entities model.   
 * 
 * @author DenisJ1
 */
public abstract class DTOBaseAssembler implements EntityAssembler {

	@Override
	public <TDto, TModel> List<TDto> toDtos(Class<TDto> dtoClass, List<TModel> modelList) {
		List<TDto> dtos= new ArrayList<TDto>();
		if (!modelList.isEmpty()) {
			for (TModel model : modelList) {
				dtos.add(toDto(dtoClass, model));
			}
		}
		return dtos;
	}
	
	@Override
	public <TDto, TModel> List<TModel> toModels(Class<TModel> modelClass, List<TDto> dtoList) {
		List<TModel> entities = new ArrayList<TModel>();
		if (!dtoList.isEmpty()) {
			for (TDto dto : dtoList) {
				entities.add(toModel(modelClass, dto));
			}
		}
		return entities;
	}
}
