package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.AwardMapper;
import limeng32.mirage.util.service.ServiceSupport;

@Service
public class AwardService extends ServiceSupport<Award> implements AwardMapper {
	@Autowired
	private AwardMapper mapper;

	@Override
	public Award select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Award t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Award t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Award> selectAll(Award t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Award t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(Award t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Award t) {
		return supportCount(mapper, t);
	}

	@Override
	public Award selectOne(Award t) {
		return supportSelectOne(mapper, t);
	}
}