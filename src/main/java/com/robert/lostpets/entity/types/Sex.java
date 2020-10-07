package com.robert.lostpets.entity.types;

public enum Sex {

	MALE("MALE"), FEMALE("FEMALE");

	private String type;

	Sex(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static Sex fromString(String type) {
		for (Sex s : Sex.values()) {
			if (s.getType().equalsIgnoreCase(type)) {
				return s;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (Sex s : Sex.values()) {
			sb.append(s.getType());
			if (!s.equals(Sex.values()[Sex.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
