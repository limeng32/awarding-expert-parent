package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.project.persist.Project;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Expert.class, Project.class }, TriggerClass = { Acception.class })
public interface AcceptionMapper extends MapperFace<Acception> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Acception select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Acception> selectAll(Acception t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Acception selectOne(Acception t);

	@Override
	public void insert(Acception t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Acception t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Acception t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Acception t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Acception t);

	public void loadExpert(Expert expert, Acception acception);

	public void loadProject(Project project, Acception acception);
}
