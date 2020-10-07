package com.robert.lostpets.entity.types;

public enum MessageStatus {

	SENT("SENT"), DELIVERED("DELIVERED"), READ("READ");

	private String status;

	MessageStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static MessageStatus fromString(String status) {
		for (MessageStatus mS : MessageStatus.values()) {
			if (mS.getStatus().equalsIgnoreCase(status)) {
				return mS;
			}
		}
		return null;
	}

	public static String stringValues() {
		StringBuilder sb = new StringBuilder("[");

		for (MessageStatus mS : MessageStatus.values()) {
			sb.append(mS.getStatus());
			if (!mS.equals(
					MessageStatus.values()[MessageStatus.values().length - 1]))
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
