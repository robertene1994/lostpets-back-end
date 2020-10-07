package com.robert.lostpets.business.ad.impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.business.ad.AdService;
import com.robert.lostpets.business.ad.validator.AdValidator;
import com.robert.lostpets.business.util.CodeGenerator;
import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;
import com.robert.lostpets.persistence.AdRepository;

/**
 * Clase que implementa la interfaz AdService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.ad.AdService
 */
@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdValidator validator;

	@Autowired
	private AdRepository adRepository;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public Ad find(Long id) throws BusinessException {
		validator.findById(id);
		return adRepository.findOne(id);
	}

	@Override
	public List<Ad> findAll() {
		return StreamSupport
				.stream(adRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Ad save(Ad ad) throws BusinessException {
		validator.save(ad);
		return adRepository.save(ad);
	}

	@Override
	public Ad update(Ad ad) throws BusinessException {
		validator.update(ad);
		return adRepository.save(ad);
	}

	@Override
	public Boolean deleteById(Long id) throws BusinessException {
		validator.deleteById(id);
		try {
			adRepository.delete(id);
			return true;
		} catch (DataAccessException e) {
			throw new BusinessException("id", "ad.delete.id.exception", id);
		}
	}

	@Override
	public Ad findByCode(String code) throws BusinessException {
		validator.findByCode(code);
		return adRepository.findByCode(code);
	}

	@Override
	public List<Ad> findByUserId(Long id) throws BusinessException {
		validator.findByUserId(id);
		return adRepository.findByUserId(id);
	}

	@Override
	public Boolean save(Ad ad, MultipartFile image) throws BusinessException {
		validator.save(ad, image);
		String code = null;

		do {
			code = CodeGenerator.random();
		} while (adRepository.findByCode(code) != null);

		ad.setCode(code);

		if (image != null && !image.isEmpty()) {
			try {
				File destination = resourceLoader
						.getResource("WEB-INF/resources/img/" + code + ".jpg")
						.getFile();
				image.transferTo(destination);
				ad.setPhoto("resources/img/" + code + ".jpg");
			} catch (Exception e) {
				return false;
			}
		}

		adRepository.save(ad);
		return true;
	}

	@Override
	public Boolean update(Ad ad, MultipartFile image) throws BusinessException {
		validator.update(ad, image);
		String code = ad.getCode();
		ad.setId(adRepository.findByCode(code).getId());

		if (image != null && !image.isEmpty()) {
			try {
				File destination = resourceLoader
						.getResource("WEB-INF/resources/img/" + code + ".jpg")
						.getFile();
				image.transferTo(destination);
			} catch (Exception e) {
				return false;
			}
		}

		adRepository.save(ad);
		return true;
	}

	@Override
	public Boolean updateAdStatus(String code, AdStatus status)
			throws BusinessException {
		validator.updateAdStatus(code, status);
		Ad ad = adRepository.findByCode(code);

		if (ad == null)
			throw new BusinessException("ad", "ad.update.code.invalid");

		ad.setAdStatus(status);
		adRepository.save(ad);
		return true;
	}
}
