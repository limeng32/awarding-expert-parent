package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Award;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Award.class })
public interface AwardMapper extends MapperFace<Award> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Award select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Award> selectAll(Award t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Award selectOne(Award t);

	@Override
	public void insert(Award t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Award t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Award t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Award t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Award t);
}
