package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.NoticeBucketMapper;
import limeng32.mirage.util.service.ServiceSupport;

@Service
public class NoticeBucketService extends ServiceSupport<NoticeBucket> implements NoticeBucketMapper {
	@Autowired
	private NoticeBucketMapper mapper;

	@Override
	public NoticeBucket select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(NoticeBucket t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(NoticeBucket t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<NoticeBucket> selectAll(NoticeBucket t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(NoticeBucket t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(NoticeBucket t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(NoticeBucket t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadNotice(Notice Notice, NoticeBucket NoticeBucket) {
		Notice.removeAllNoticeBucket();
		NoticeBucket.setNotice(Notice);
		Notice.setNoticeBucket(mapper.selectAll(NoticeBucket));
	}

	@Override
	public NoticeBucket selectOne(NoticeBucket t) {
		return supportSelectOne(mapper, t);
	}
}