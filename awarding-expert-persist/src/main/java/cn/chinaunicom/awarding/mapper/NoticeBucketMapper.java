package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Notice;
import cn.chinaunicom.awarding.expert.persist.NoticeBucket;

public interface NoticeBucketMapper extends MapperFace<NoticeBucket> {

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Observer)
	public NoticeBucket select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Observer)
	public Collection<NoticeBucket> selectAll(NoticeBucket t);

	@Override
	public void insert(NoticeBucket t);

	@Override
	@CacheAnnotation(MappedClass = { NoticeBucket.class }, role = CacheRoleType.Trigger)
	public int update(NoticeBucket t);

	@Override
	@CacheAnnotation(MappedClass = { NoticeBucket.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(NoticeBucket t);

	@Override
	public void retrieve(NoticeBucket t);

	@Override
	public void retrieveOnlyNull(NoticeBucket t);

	@Override
	@CacheAnnotation(MappedClass = { NoticeBucket.class }, role = CacheRoleType.Trigger)
	public int delete(NoticeBucket t);

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Observer)
	public int count(NoticeBucket t);

	public void loadNotice(Notice Notice, NoticeBucket NoticeBucket);
}
