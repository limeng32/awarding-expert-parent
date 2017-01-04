package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.ExpertTeam;
import cn.chinaunicom.awarding.expert.persist.ExpertTeamExpert;

public interface ExpertTeamExpertMapper extends MapperFace<ExpertTeamExpert> {

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, ExpertTeam.class }, role = CacheRoleType.Observer)
	public ExpertTeamExpert select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, ExpertTeam.class }, role = CacheRoleType.Observer)
	public Collection<ExpertTeamExpert> selectAll(ExpertTeamExpert t);

	@Override
	public void insert(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(MappedClass = { ExpertTeamExpert.class }, role = CacheRoleType.Trigger)
	public int update(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(MappedClass = { ExpertTeamExpert.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(ExpertTeamExpert t);

	@Override
	public void retrieve(ExpertTeamExpert t);

	@Override
	public void retrieveOnlyNull(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(MappedClass = { ExpertTeamExpert.class }, role = CacheRoleType.Trigger)
	public int delete(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, ExpertTeam.class }, role = CacheRoleType.Observer)
	public int count(ExpertTeamExpert t);

	public void loadExpert(Expert expert, ExpertTeamExpert expertTeamExpert);

	public void loadExpertTeam(ExpertTeam expertTeam,
			ExpertTeamExpert expertTeamExpert);
}
