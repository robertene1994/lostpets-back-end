package com.robert.lostpets.business.ad.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.business.ad.validator.AdValidator;
import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;

/**
 * Clase que implementa la interfaz AdValidator.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.ad.validator.AdValidator
 */
@Component
public class AdValidatorImpl implements AdValidator {

	@Override
	public void findById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "ad.find.id.required");
	}

	@Override
	public void save(Ad ad) throws BusinessException {
		if (ad == null)
			throw new BusinessException("ad", "ad.save.ad.required");
	}

	@Override
	public void update(Ad ad) throws BusinessException {
		if (ad == null)
			throw new BusinessException("ad", "ad.update.ad.required");
	}

	@Override
	public void deleteById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "ad.delete.id.required");
	}

	@Override
	public void findByCode(String code) throws BusinessException {
		if (code == null || code.trim().isEmpty())
			throw new BusinessException("id", "ad.find.code.required");
	}

	@Override
	public void findByUserId(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "ad.findByUser.id.required");
	}

	@Override
	public void save(Ad ad, MultipartFile image) throws BusinessException {
		if (ad == null)
			throw new BusinessException("ad", "ad.save.ad.required");
		if (image == null || image.isEmpty())
			throw new BusinessException("image", "ad.save.image.required");
	}

	@Override
	public void update(Ad ad, MultipartFile image) throws BusinessException {
		if (ad == null)
			throw new BusinessException("ad", "ad.update.ad.required");
		if (ad.getCode() == null || ad.getCode().trim().isEmpty())
			throw new BusinessException("code", "ad.update.ad.code.required");
		if (image == null || image.isEmpty())
			throw new BusinessException("image", "ad.update.image.required");
	}

	@Override
	public void updateAdStatus(String code, AdStatus status)
			throws BusinessException {
		if (code == null || code.trim().isEmpty())
			throw new BusinessException("code", "ad.update.code.required");
		if (status == null)
			throw new BusinessException("status", "ad.update.status.required");
	}
}
