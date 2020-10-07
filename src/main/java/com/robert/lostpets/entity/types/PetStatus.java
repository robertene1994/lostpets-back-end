package com.robert.lostpets.entity.types;

public enum PetStatus {

	FOUND("FOUND"), LOST("LOST");

	private String status;

	PetStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static PetStatus fromString(String status) {
		for (PetStatus s : PetStatus.values()) {
			if (s.getStatus().equalsIgnoreCase(status)) {
				return s;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (PetStatus s : PetStatus.values()) {
			sb.append(s.getStatus());
			if (!s.equals(PetStatus.values()[PetStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
