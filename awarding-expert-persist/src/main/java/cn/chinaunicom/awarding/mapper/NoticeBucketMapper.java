package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Notice;
import cn.chinaunicom.awarding.expert.persist.NoticeBucket;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Notice.class }, TriggerClass = { NoticeBucket.class })
public interface NoticeBucketMapper extends MapperFace<NoticeBucket> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public NoticeBucket select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<NoticeBucket> selectAll(NoticeBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public NoticeBucket selectOne(NoticeBucket t);

	@Override
	public void insert(NoticeBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(NoticeBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(NoticeBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(NoticeBucket t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(NoticeBucket t);

	public void loadNotice(Notice Notice, NoticeBucket NoticeBucket);
}
