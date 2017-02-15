package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ExpertTeamExpertMapper;
import limeng32.mirage.util.service.ServiceSupport;

@Service
public class ExpertTeamExpertService extends ServiceSupport<ExpertTeamExpert> implements ExpertTeamExpertMapper {
	@Autowired
	private ExpertTeamExpertMapper mapper;

	@Override
	public ExpertTeamExpert select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(ExpertTeamExpert t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(ExpertTeamExpert t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<ExpertTeamExpert> selectAll(ExpertTeamExpert t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(ExpertTeamExpert t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(ExpertTeamExpert t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(ExpertTeamExpert t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadExpert(Expert expert, ExpertTeamExpert expertTeamExpert) {
		expert.removeAllExpertTeamExpert();
		expertTeamExpert.setExpert(expert);
		expert.setExpertTeamExpert(mapper.selectAll(expertTeamExpert));
	}

	@Override
	public void loadExpertTeam(ExpertTeam expertTeam, ExpertTeamExpert expertTeamExpert) {
		expertTeam.removeAllExpertTeamExpert();
		expertTeamExpert.setExpertTeam(expertTeam);
		expertTeam.setExpertTeamExpert(mapper.selectAll(expertTeamExpert));
	}

	@Override
	public ExpertTeamExpert selectOne(ExpertTeamExpert t) {
		return supportSelectOne(mapper, t);
	}
}