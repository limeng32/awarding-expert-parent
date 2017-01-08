package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.AcceptionBucket;

public interface AcceptionBucketMapper extends MapperFace<AcceptionBucket> {

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Observer)
	public AcceptionBucket select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Observer)
	public Collection<AcceptionBucket> selectAll(AcceptionBucket t);

	@Override
	public void insert(AcceptionBucket t);

	@Override
	@CacheAnnotation(MappedClass = { AcceptionBucket.class }, role = CacheRoleType.Trigger)
	public int update(AcceptionBucket t);

	@Override
	@CacheAnnotation(MappedClass = { AcceptionBucket.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(AcceptionBucket t);

	@Override
	public void retrieve(AcceptionBucket t);

	@Override
	public void retrieveOnlyNull(AcceptionBucket t);

	@Override
	@CacheAnnotation(MappedClass = { AcceptionBucket.class }, role = CacheRoleType.Trigger)
	public int delete(AcceptionBucket t);

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Observer)
	public int count(AcceptionBucket t);

	public void loadAcception(Acception acception,
			AcceptionBucket acceptionBucket);
}
