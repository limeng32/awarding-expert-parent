package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.Vote;
import cn.chinaunicom.awarding.project.persist.Task;

public interface VoteMapper extends MapperFace<Vote> {

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Expert.class, Acception.class,
			Award.class }, role = CacheRoleType.Observer)
	public Vote select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Expert.class, Acception.class,
			Award.class }, role = CacheRoleType.Observer)
	public Collection<Vote> selectAll(Vote t);

	@Override
	public void insert(Vote t);

	@Override
	@CacheAnnotation(MappedClass = { Vote.class }, role = CacheRoleType.Trigger)
	public int update(Vote t);

	@Override
	@CacheAnnotation(MappedClass = { Vote.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Vote t);

	@Override
	public void retrieve(Vote t);

	@Override
	public void retrieveOnlyNull(Vote t);

	@Override
	@CacheAnnotation(MappedClass = { Vote.class }, role = CacheRoleType.Trigger)
	public int delete(Vote t);

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Expert.class, Acception.class,
			Award.class }, role = CacheRoleType.Observer)
	public int count(Vote t);

	public void loadTask(Task task, Vote vote);

	public void loadExpert(Expert expert, Vote vote);

	public void loadAcception(Acception acception, Vote vote);

	public void loadAward(Award award, Vote vote);
}
