package com.robert.lostpets.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.robert.lostpets.entity.common.GenericEntity;
import com.robert.lostpets.entity.dto.DateDeserializer;
import com.robert.lostpets.entity.types.AdStatus;
import com.robert.lostpets.entity.types.LatLng;
import com.robert.lostpets.entity.types.Pet;
import com.robert.lostpets.entity.types.PetStatus;

@Entity
@Table(name = "TAd")
public class Ad extends GenericEntity<Long> {

	private static final long serialVersionUID = 1439540810618902429L;

	@NotNull(message = "{ad.code.null}")
	@NotEmpty(message = "{ad.code.empty}")
	@Column(unique = true)
	private String code;

	@NotNull(message = "{ad.date.null}")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@NotNull(message = "{ad.adStatus.null}")
	@Enumerated(EnumType.STRING)
	private AdStatus adStatus;

	@NotNull(message = "{ad.petStatus.null}")
	@Enumerated(EnumType.STRING)
	private PetStatus petStatus;

	@NotNull(message = "{ad.reward.null}")
	@Min(value = 0, message = "{ad.reward.min}")
	private Double reward;

	@Embedded
	@NotNull(message = "{ad.lastSpottedCoords.null}")
	private LatLng lastSpottedCoords;

	@Embedded
	@NotNull(message = "{ad.pet.null}")
	private Pet pet;

	@NotNull(message = "{ad.observations.null}")
	@NotBlank(message = "{ad.observations.blank}")
	private String observations;

	@NotNull(message = "{ad.photo.null}")
	private String photo;

	@NotNull(message = "{ad.user.null}")
	@ManyToOne
	private User user;

	Ad() {

	}

	public Ad(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonDeserialize(using = DateDeserializer.class)
	public Date getDate() {
		return date != null ? (Date) date.clone() : null;
	}

	public void setDate(Date date) {
		if (date != null)
			this.date = date;
	}

	public AdStatus getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(AdStatus adStatus) {
		this.adStatus = adStatus;
	}

	public PetStatus getPetStatus() {
		return petStatus;
	}

	public void setPetStatus(PetStatus petStatus) {
		this.petStatus = petStatus;
	}

	public Double getReward() {
		return reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}

	public LatLng getLastSpottedCoords() {
		return lastSpottedCoords;
	}

	public void setLastSpottedCoords(LatLng lastSpottedCoords) {
		this.lastSpottedCoords = lastSpottedCoords;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ad other = (Ad) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ad [code=" + code + ", date=" + date + ", user=" + user + "]";
	}
}
