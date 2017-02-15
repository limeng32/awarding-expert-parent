package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ExpertAvoidsMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class ExpertAvoidsService extends ServiceSupport<ExpertAvoids> implements ExpertAvoidsMapper {
	@Autowired
	private ExpertAvoidsMapper mapper;

	@Override
	public ExpertAvoids select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(ExpertAvoids t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(ExpertAvoids t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<ExpertAvoids> selectAll(ExpertAvoids t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(ExpertAvoids t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(ExpertAvoids t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(ExpertAvoids t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, ExpertAvoids expertAvoids) {
		task.removeAllExpertAvoids();
		expertAvoids.setTask(task);
		task.setExpertAvoids(mapper.selectAll(expertAvoids));
	}

	@Override
	public void loadAcception(Acception acception, ExpertAvoids expertAvoids) {
		acception.removeAllExpertAvoids();
		expertAvoids.setAcception(acception);
		acception.setExpertAvoids(mapper.selectAll(expertAvoids));
	}

	@Override
	public void loadExpert(Expert expert, ExpertAvoids expertAvoids) {
		expert.removeAllExpertAvoids();
		expertAvoids.setExpert(expert);
		expert.setExpertAvoids(mapper.selectAll(expertAvoids));
	}

	@Override
	public ExpertAvoids selectOne(ExpertAvoids t) {
		return supportSelectOne(mapper, t);
	}
}