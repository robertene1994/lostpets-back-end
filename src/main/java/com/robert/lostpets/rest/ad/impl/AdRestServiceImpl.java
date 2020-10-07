package com.robert.lostpets.rest.ad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.business.ad.AdService;
import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;
import com.robert.lostpets.rest.ad.AdRestService;

/**
 * Clase que implementa la interfaz AdRestService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.rest.ad.AdRestService
 */
@RestController
public class AdRestServiceImpl implements AdRestService {

	@Autowired
	private AdService adService;

	@Override
	public Ad findByCode(@PathVariable("code") String code)
			throws BusinessException {
		return adService.findByCode(code);
	}

	@Override
	public List<Ad> findByUserId(@PathVariable("userId") Long userId) throws BusinessException {
		return adService.findByUserId(userId);
	}

	@Override
	public List<Ad> findAll() {
		return adService.findAll();
	}

	@Override
	public Boolean save(@RequestPart("ad") Ad ad, MultipartFile image)
			throws BusinessException {
		return adService.save(ad, image);
	}

	@Override
	public Boolean update(@RequestPart("ad") Ad ad, MultipartFile image)
			throws BusinessException {
		return adService.update(ad, image);
	}

	@Override
	public Boolean updateAdStatus(@PathVariable("code") String code,
			AdStatus status) throws BusinessException {
		return adService.updateAdStatus(code, status);
	}
}
