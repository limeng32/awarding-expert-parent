package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.AcceptionMapper;
import cn.chinaunicom.awarding.project.persist.Project;

@Service
public class AcceptionService extends ServiceSupport<Acception> implements AcceptionMapper {
	@Autowired
	private AcceptionMapper mapper;

	@Override
	public Acception select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Acception t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Acception t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Acception> selectAll(Acception t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Acception t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(Acception t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Acception t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadProject(Project project, Acception acception) {
		project.removeAllAcception();
		acception.setProject(project);
		project.setAcception(mapper.selectAll(acception));
	}

	@Override
	public void loadExpert(Expert expert, Acception acception) {
		expert.removeAllAcception();
		acception.setExpert(expert);
		expert.setAcception(mapper.selectAll(acception));
	}

	@Override
	public Acception selectOne(Acception t) {
		return supportSelectOne(mapper, t);
	}
}