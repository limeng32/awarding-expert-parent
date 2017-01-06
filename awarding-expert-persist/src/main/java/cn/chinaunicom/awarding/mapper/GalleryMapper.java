package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Gallery;
import cn.chinaunicom.awarding.expert.persist.Result;

public interface GalleryMapper extends MapperFace<Gallery> {

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Observer)
	public Gallery select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Observer)
	public Collection<Gallery> selectAll(Gallery t);

	@Override
	public void insert(Gallery t);

	@Override
	@CacheAnnotation(MappedClass = { Gallery.class }, role = CacheRoleType.Trigger)
	public int update(Gallery t);

	@Override
	@CacheAnnotation(MappedClass = { Gallery.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Gallery t);

	@Override
	public void retrieve(Gallery t);

	@Override
	public void retrieveOnlyNull(Gallery t);

	@Override
	@CacheAnnotation(MappedClass = { Gallery.class }, role = CacheRoleType.Trigger)
	public int delete(Gallery t);

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Observer)
	public int count(Gallery t);

	public void loadResult(Result result, Gallery gallery);
}
