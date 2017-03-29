package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.ExpertTeam;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Task.class, Award.class }, TriggerClass = { ExpertTeam.class })
public interface ExpertTeamMapper extends MapperFace<ExpertTeam> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertTeam select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<ExpertTeam> selectAll(ExpertTeam t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertTeam selectOne(ExpertTeam t);

	@Override
	public void insert(ExpertTeam t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(ExpertTeam t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(ExpertTeam t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(ExpertTeam t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(ExpertTeam t);

	public void loadTask(Task task, ExpertTeam expertTeam);

	public void loadAward(Award award, ExpertTeam expertTeam);
}
