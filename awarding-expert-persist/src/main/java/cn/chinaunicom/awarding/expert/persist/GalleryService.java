package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.GalleryMapper;

@Service
public class GalleryService extends ServiceSupport<Gallery> implements
		GalleryMapper {
	@Autowired
	private GalleryMapper mapper;

	@Override
	public Gallery select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(Gallery t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Gallery t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Gallery> selectAll(Gallery t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Gallery t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(Gallery t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(Gallery t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(Gallery t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Gallery t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadResult(Result result, Gallery gallery) {
		result.removeAllGallery();
		gallery.setResult(result);
		result.setGallery(mapper.selectAll(gallery));
	}
}