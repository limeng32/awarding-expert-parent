package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.ExpertAvoids;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Task.class, Acception.class, Expert.class }, TriggerClass = {
		ExpertAvoids.class })
public interface ExpertAvoidsMapper extends MapperFace<ExpertAvoids> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertAvoids select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<ExpertAvoids> selectAll(ExpertAvoids t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertAvoids selectOne(ExpertAvoids t);

	@Override
	public void insert(ExpertAvoids t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(ExpertAvoids t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(ExpertAvoids t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(ExpertAvoids t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(ExpertAvoids t);

	public void loadTask(Task task, ExpertAvoids expertAvoids);

	public void loadAcception(Acception acception, ExpertAvoids expertAvoids);

	public void loadExpert(Expert expert, ExpertAvoids expertAvoids);
}
