package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.ExpertMapper;

@Service
public class ExpertService extends ServiceSupport<Expert> implements ExpertMapper {
	@Autowired
	private ExpertMapper mapper;

	@Override
	public Expert select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Expert t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Expert t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Expert> selectAll(Expert t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Expert t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(Expert t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Expert t) {
		return supportCount(mapper, t);
	}

	@Override
	public Expert selectOne(Expert t) {
		return supportSelectOne(mapper, t);
	}

}