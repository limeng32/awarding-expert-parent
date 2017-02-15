package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.ExpertTeam;
import cn.chinaunicom.awarding.expert.persist.ExpertTeamExpert;
import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Expert.class, ExpertTeam.class }, TriggerClass = { ExpertTeamExpert.class })
public interface ExpertTeamExpertMapper extends MapperFace<ExpertTeamExpert> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertTeamExpert select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<ExpertTeamExpert> selectAll(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public ExpertTeamExpert selectOne(ExpertTeamExpert t);

	@Override
	public void insert(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(ExpertTeamExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(ExpertTeamExpert t);

	public void loadExpert(Expert expert, ExpertTeamExpert expertTeamExpert);

	public void loadExpertTeam(ExpertTeam expertTeam, ExpertTeamExpert expertTeamExpert);
}
