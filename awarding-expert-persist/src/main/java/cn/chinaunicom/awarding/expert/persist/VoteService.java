package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.VoteMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class VoteService extends ServiceSupport<Vote> implements VoteMapper {
	@Autowired
	private VoteMapper mapper;

	@Override
	public Vote select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Vote t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Vote t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Vote> selectAll(Vote t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Vote t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(Vote t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(Vote t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(Vote t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Vote t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, Vote vote) {
		task.removeAllVote();
		vote.setTask(task);
		task.setVote(mapper.selectAll(vote));
	}

	@Override
	public void loadExpert(Expert expert, Vote vote) {
		expert.removeAllVote();
		vote.setExpert(expert);
		expert.setVote(mapper.selectAll(vote));
	}

	@Override
	public void loadAcception(Acception acception, Vote vote) {
		acception.removeAllVote();
		vote.setAcception(acception);
		acception.setVote(mapper.selectAll(vote));
	}

	@Override
	public void loadAward(Award award, Vote vote) {
		award.removeAllVote();
		vote.setAward(award);
		award.setVote(mapper.selectAll(vote));
	}
}