package com.robert.lostpets.entity.types;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Pet implements Serializable {

	private static final long serialVersionUID = -4420054730657853231L;

	@NotNull(message = "{pet.name.null}")
	@NotBlank(message = "{pet.name.blank}")
	private String name;

	@NotNull(message = "{pet.type.null}")
	@NotBlank(message = "{pet.type.blank}")
	private String type;

	@NotNull(message = "{pet.race.null}")
	@NotBlank(message = "{pet.race.blank}")
	private String race;

	@NotNull(message = "{pet.sex.null}")
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@NotNull(message = "{pet.colour.null}")
	@NotBlank(message = "{pet.colour.blank}")
	private String colour;

	@NotNull(message = "{pet.microchipId.null}")
	@NotBlank(message = "{pet.microchipId.blank}")
	private String microchipId;

	Pet() {

	}

	public Pet(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getMicrochipId() {
		return microchipId;
	}

	public void setMicrochipId(String microchipId) {
		this.microchipId = microchipId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result
				+ ((microchipId == null) ? 0 : microchipId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Pet other = (Pet) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (microchipId == null) {
			if (other.microchipId != null)
				return false;
		} else if (!microchipId.equals(other.microchipId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (sex != other.sex)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + ", type=" + type + ", race=" + race
				+ ", sex=" + sex + ", colour=" + colour + ", microchipId="
				+ microchipId + "]";
	}
}
