package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.account.persist.Account;
import cn.chinaunicom.awarding.expert.persist.Expert;

public interface ExpertMapper extends MapperFace<Expert> {

	@Override
	@CacheAnnotation(MappedClass = { Account.class }, role = CacheRoleType.Observer)
	public Expert select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Account.class }, role = CacheRoleType.Observer)
	public Collection<Expert> selectAll(Expert t);

	@Override
	public void insert(Expert t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class }, role = CacheRoleType.Trigger)
	public int update(Expert t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Expert t);

	@Override
	public void retrieve(Expert t);

	@Override
	public void retrieveOnlyNull(Expert t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class }, role = CacheRoleType.Trigger)
	public int delete(Expert t);

	@Override
	@CacheAnnotation(MappedClass = { Account.class }, role = CacheRoleType.Observer)
	public int count(Expert t);

}
