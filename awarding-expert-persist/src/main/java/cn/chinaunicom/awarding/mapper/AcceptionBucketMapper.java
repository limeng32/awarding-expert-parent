package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.AcceptionBucket;

@CacheRoleAnnotation(ObserverClass = { Acception.class }, TriggerClass = { AcceptionBucket.class })
public interface AcceptionBucketMapper extends MapperFace<AcceptionBucket> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public AcceptionBucket select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<AcceptionBucket> selectAll(AcceptionBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public AcceptionBucket selectOne(AcceptionBucket t);

	@Override
	public void insert(AcceptionBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(AcceptionBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(AcceptionBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(AcceptionBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(AcceptionBucket t);

	public void loadAcception(Acception acception, AcceptionBucket acceptionBucket);
}
