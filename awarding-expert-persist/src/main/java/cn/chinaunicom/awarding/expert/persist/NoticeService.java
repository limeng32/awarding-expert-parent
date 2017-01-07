package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.NoticeMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class NoticeService extends ServiceSupport<Notice> implements
		NoticeMapper {
	@Autowired
	private NoticeMapper mapper;

	@Override
	public Notice select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Notice t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Notice t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Notice> selectAll(Notice t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Notice t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(Notice t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(Notice t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(Notice t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Notice t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadTask(Task task, Notice Notice) {
		task.removeAllNotice();
		Notice.setTask(task);
		task.setNotice(mapper.selectAll(Notice));
	}
}