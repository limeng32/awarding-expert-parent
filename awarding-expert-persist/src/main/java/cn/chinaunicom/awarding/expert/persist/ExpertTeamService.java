package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ExpertTeamMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class ExpertTeamService extends ServiceSupport<ExpertTeam> implements
		ExpertTeamMapper {
	@Autowired
	private ExpertTeamMapper mapper;

	@Override
	public ExpertTeam select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(ExpertTeam t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(ExpertTeam t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<ExpertTeam> selectAll(ExpertTeam t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(ExpertTeam t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(ExpertTeam t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(ExpertTeam t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(ExpertTeam t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(ExpertTeam t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, ExpertTeam expertTeam) {
		task.removeAllExpertTeam();
		expertTeam.setTask(task);
		task.setExpertTeam(mapper.selectAll(expertTeam));
	}

	@Override
	public void loadAward(Award award, ExpertTeam expertTeam) {
		award.removeAllExpertTeam();
		expertTeam.setAward(award);
		award.setExpertTeam(mapper.selectAll(expertTeam));
	}
}