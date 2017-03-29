package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.account.persist.Account;
import cn.chinaunicom.awarding.expert.persist.Expert;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Account.class }, TriggerClass = { Expert.class })
public interface ExpertMapper extends MapperFace<Expert> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Expert select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Expert> selectAll(Expert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Expert selectOne(Expert t);

	@Override
	public void insert(Expert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Expert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Expert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Expert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Expert t);

}
