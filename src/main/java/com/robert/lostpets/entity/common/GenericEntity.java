package com.robert.lostpets.entity.common;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity<I extends Serializable>
		implements Serializable {

	private static final long serialVersionUID = -4577406731226661603L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private I id;

	public I getId() {
		return id;
	}

	public void setId(I id) {
		this.id = id;
	}
}
