package com.lunifera.graduate.dtos.mapper;

import com.lunifera.graduate.dtos.BaseDto;
import com.lunifera.graduate.entities.Base;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;

/**
 * This class maps the dto {@link BaseDto} to and from the entity {@link Base}.
 * 
 */
@SuppressWarnings("all")
public class BaseDtoMapper<DTO extends BaseDto, ENTITY extends Base> implements IMapper<DTO, ENTITY> {
  private IMapperAccess mapperAccess;
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToDtoMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToDtoMapper(dtoClass, entityClass);
  }
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToEntityMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToEntityMapper(dtoClass, entityClass);
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void bindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = mapperAccess;
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void unbindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = null;
  }
  
  /**
   * Creates a new instance of the entity
   */
  public Base createEntity() {
    return new Base();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public BaseDto createDto() {
    return new BaseDto();
  }
  
  /**
   * Maps the entity {@link Base} to the dto {@link BaseDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final BaseDto dto, final Base entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
  }
  
  /**
   * Maps the dto {@link BaseDto} to the entity {@link Base}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final BaseDto dto, final Base entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
  }
  
  public String createDtoHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
  
  public String createEntityHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
}
