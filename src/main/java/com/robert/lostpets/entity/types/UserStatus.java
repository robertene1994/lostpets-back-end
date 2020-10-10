package com.robert.lostpets.entity.types;

public enum UserStatus {

	ENABLED("ENABLED"), DISABLED("DISABLED");

	private String status;

	UserStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static UserStatus fromString(String userStatus) {
		for (UserStatus uS : UserStatus.values()) {
			if (uS.getStatus().equalsIgnoreCase(userStatus)) {
				return uS;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (UserStatus uS : UserStatus.values()) {
			sb.append(uS.getStatus());
			if (!uS.equals(UserStatus.values()[UserStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
