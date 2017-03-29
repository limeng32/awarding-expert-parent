package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Gallery;
import cn.chinaunicom.awarding.expert.persist.Result;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Result.class }, TriggerClass = { Gallery.class })
public interface GalleryMapper extends MapperFace<Gallery> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Gallery select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Gallery> selectAll(Gallery t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Gallery selectOne(Gallery t);

	@Override
	public void insert(Gallery t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Gallery t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Gallery t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Gallery t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Gallery t);

	public void loadResult(Result result, Gallery gallery);
}
