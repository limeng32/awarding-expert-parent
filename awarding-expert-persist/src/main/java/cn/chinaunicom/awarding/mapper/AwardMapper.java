package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Award;

public interface AwardMapper extends MapperFace<Award> {

	@Override
	@CacheAnnotation(MappedClass = {}, role = CacheRoleType.Observer)
	public Award select(Object id);

	@Override
	@CacheAnnotation(MappedClass = {}, role = CacheRoleType.Observer)
	public Collection<Award> selectAll(Award t);

	@Override
	public void insert(Award t);

	@Override
	@CacheAnnotation(MappedClass = { Award.class }, role = CacheRoleType.Trigger)
	public int update(Award t);

	@Override
	@CacheAnnotation(MappedClass = { Award.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Award t);

	@Override
	public void retrieve(Award t);

	@Override
	public void retrieveOnlyNull(Award t);

	@Override
	@CacheAnnotation(MappedClass = { Award.class }, role = CacheRoleType.Trigger)
	public int delete(Award t);

	@Override
	@CacheAnnotation(MappedClass = {}, role = CacheRoleType.Observer)
	public int count(Award t);
}
