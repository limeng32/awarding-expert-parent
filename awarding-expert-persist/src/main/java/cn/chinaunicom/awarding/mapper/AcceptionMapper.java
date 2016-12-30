package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.project.persist.Project;

public interface AcceptionMapper extends MapperFace<Acception> {

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Project.class }, role = CacheRoleType.Observer)
	public Acception select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Project.class }, role = CacheRoleType.Observer)
	public Collection<Acception> selectAll(Acception t);

	@Override
	public void insert(Acception t);

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Trigger)
	public int update(Acception t);

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Acception t);

	@Override
	public void retrieve(Acception t);

	@Override
	public void retrieveOnlyNull(Acception t);

	@Override
	@CacheAnnotation(MappedClass = { Acception.class }, role = CacheRoleType.Trigger)
	public int delete(Acception t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Project.class }, role = CacheRoleType.Observer)
	public int count(Acception t);

	public void loadExpert(Expert expert, Acception acception);

	public void loadProject(Project project, Acception acception);
}
