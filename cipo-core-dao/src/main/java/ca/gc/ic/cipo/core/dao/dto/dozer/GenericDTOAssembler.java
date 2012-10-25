package ca.gc.ic.cipo.core.dao.dto.dozer;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.Mapper;

import ca.gc.ic.cipo.core.dao.dto.GenericDTOBaseAssembler;

/**
 * GenericDTOAssembler is a generic class that implements the conversion 
 * of DTOs to / from entities model using the third-party library 
 * Dozer from Apache - http://dozer.sourceforge.net/.  
 * 
 * @author DenisJ1
 */
public class GenericDTOAssembler<TDto, TModel> extends GenericDTOBaseAssembler<TDto, TModel> {

	/** Logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	protected Mapper mapper;

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	public GenericDTOAssembler() {
		super();
	}
	
	@Override
	public TDto toDto(TModel model) {
		return mapper.map(model, dtoClass);
	}

	@Override
	public TDto toDto(TDto dto, TModel model) {
		mapper.map(model, dto);
		return dto;
	}

	@Override
	public TModel toModel(TDto dto) {
		return mapper.map(dto, modelClass);
	}

	@Override
	public TModel toModel(TModel model, TDto dto) {
		mapper.map(dto, model);
		return model;
	}
}
