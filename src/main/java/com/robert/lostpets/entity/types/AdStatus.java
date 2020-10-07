package com.robert.lostpets.entity.types;

public enum AdStatus {

	ENABLED("ENABLED"), DISABLED("DISABLED");

	private String adStatus;

	AdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public static AdStatus fromString(String adStatus) {
		for (AdStatus aS : AdStatus.values()) {
			if (aS.getAdStatus().equalsIgnoreCase(adStatus)) {
				return aS;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (AdStatus aS : AdStatus.values()) {
			sb.append(aS.getAdStatus());
			if (!aS.equals(AdStatus.values()[AdStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
