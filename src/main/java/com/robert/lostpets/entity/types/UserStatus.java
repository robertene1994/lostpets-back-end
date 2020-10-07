package com.robert.lostpets.entity.types;

public enum UserStatus {

	ENABLED("ENABLED"), DISABLED("DISABLED");

	private String userStatus;

	UserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public static UserStatus fromString(String userStatus) {
		for (UserStatus uS : UserStatus.values()) {
			if (uS.getUserStatus().equalsIgnoreCase(userStatus)) {
				return uS;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (UserStatus uS : UserStatus.values()) {
			sb.append(uS.getUserStatus());
			if (!uS.equals(UserStatus.values()[UserStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
