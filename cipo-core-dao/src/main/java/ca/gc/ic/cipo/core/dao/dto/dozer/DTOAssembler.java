package ca.gc.ic.cipo.core.dao.dto.dozer;

import org.apache.log4j.Logger;
import org.dozer.Mapper;

import ca.gc.ic.cipo.core.dao.dto.DTOBaseAssembler;

/**
 * DTOAssembler is a generic class that implements the conversion 
 * of DTOs to / from entities model using the third-party library 
 * Dozer from Apache - http://dozer.sourceforge.net/.  
 * 
 * @author DenisJ1
 */
public class DTOAssembler extends DTOBaseAssembler { 

	/** Logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	protected Mapper mapper;

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public DTOAssembler() {
	}

	@Override
	public <TDto, TModel> TDto toDto(Class<TDto> dtoClass, TModel model) {
		return mapper.map(model, dtoClass);
	}

	@Override

	public <TDto, TModel> TDto toDto(TDto dto, TModel model) {
		mapper.map(model, dto);
		return dto;
	}

	@Override
	public <TDto, TModel> TModel toModel(Class<TModel> modelClass, TDto dto) {
		return mapper.map(dto, modelClass);
	}

	@Override
	public <TDto, TModel> TModel toModel(TModel model, TDto dto) {
		mapper.map(dto, model);
		return model;
	}

}
