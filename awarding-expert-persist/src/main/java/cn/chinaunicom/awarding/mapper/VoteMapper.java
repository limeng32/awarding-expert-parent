package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.Vote;
import cn.chinaunicom.awarding.project.persist.Task;
import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Task.class, Expert.class, Acception.class, Award.class }, TriggerClass = {
		Vote.class })
public interface VoteMapper extends MapperFace<Vote> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Vote select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Vote> selectAll(Vote t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Vote selectOne(Vote t);

	@Override
	public void insert(Vote t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Vote t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Vote t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Vote t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Vote t);

	public void loadTask(Task task, Vote vote);

	public void loadExpert(Expert expert, Vote vote);

	public void loadAcception(Acception acception, Vote vote);

	public void loadAward(Award award, Vote vote);
}
