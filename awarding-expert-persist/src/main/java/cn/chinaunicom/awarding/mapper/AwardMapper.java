package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Award;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

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
