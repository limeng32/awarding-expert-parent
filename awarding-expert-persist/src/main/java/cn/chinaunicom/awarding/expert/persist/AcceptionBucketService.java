package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.AcceptionBucketMapper;
import limeng32.mirage.util.service.ServiceSupport;

@Service
public class AcceptionBucketService extends ServiceSupport<AcceptionBucket> implements AcceptionBucketMapper {

	@Autowired
	private AcceptionBucketMapper mapper;

	@Override
	public AcceptionBucket select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(AcceptionBucket t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(AcceptionBucket t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<AcceptionBucket> selectAll(AcceptionBucket t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(AcceptionBucket t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(AcceptionBucket t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(AcceptionBucket t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadAcception(Acception acception, AcceptionBucket acceptionBucket) {
		acception.removeAllAcceptionBucket();
		acceptionBucket.setAcception(acception);
		acception.setAcceptionBucket(mapper.selectAll(acceptionBucket));
	}

	@Override
	public AcceptionBucket selectOne(AcceptionBucket t) {
		return supportSelectOne(mapper, t);
	}
}