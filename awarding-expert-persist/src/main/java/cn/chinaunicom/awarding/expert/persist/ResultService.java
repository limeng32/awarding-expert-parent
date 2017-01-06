package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ResultMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class ResultService extends ServiceSupport<Result> implements
		ResultMapper {
	@Autowired
	private ResultMapper mapper;

	@Override
	public Result select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Result t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Result t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Result> selectAll(Result t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Result t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(Result t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(Result t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(Result t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Result t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, Result result) {
		task.removeAllResult();
		result.setTask(task);
		task.setResult(mapper.selectAll(result));
	}

	@Override
	public void loadAcception(Acception acception, Result result) {
		acception.removeAllResult();
		result.setAcception(acception);
		acception.setResult(mapper.selectAll(result));
	}

	@Override
	public void loadAward(Award award, Result result) {
		award.removeAllResult();
		result.setAward(award);
		award.setResult(mapper.selectAll(result));
	}
}