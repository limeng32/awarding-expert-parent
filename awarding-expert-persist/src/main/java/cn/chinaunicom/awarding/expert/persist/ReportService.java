package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ReportMapper;
import cn.chinaunicom.awarding.project.persist.Task;
import limeng32.mirage.util.service.ServiceSupport;

@Service
public class ReportService extends ServiceSupport<Report> implements ReportMapper {
	@Autowired
	private ReportMapper mapper;

	@Override
	public Report select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Report t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Report t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Report> selectAll(Report t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Report t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(Report t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Report t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, Report report) {
		task.removeAllReport();
		report.setTask(task);
		task.setReport(mapper.selectAll(report));
	}

	@Override
	public Report selectOne(Report t) {
		return supportSelectOne(mapper, t);
	}
}