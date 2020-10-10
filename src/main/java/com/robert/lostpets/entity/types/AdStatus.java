package com.robert.lostpets.entity.types;

public enum AdStatus {

	ENABLED("ENABLED"), DISABLED("DISABLED");

	private String status;

	AdStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static AdStatus fromString(String adStatus) {
		for (AdStatus aS : AdStatus.values()) {
			if (aS.getStatus().equalsIgnoreCase(adStatus)) {
				return aS;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (AdStatus aS : AdStatus.values()) {
			sb.append(aS.getStatus());
			if (!aS.equals(AdStatus.values()[AdStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
