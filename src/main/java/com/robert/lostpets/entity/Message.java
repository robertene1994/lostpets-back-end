package com.robert.lostpets.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.robert.lostpets.entity.common.GenericEntity;
import com.robert.lostpets.entity.dto.DateDeserializer;
import com.robert.lostpets.entity.types.MessageStatus;

@Entity
@Table(name = "TMessage")
public class Message extends GenericEntity<Long> {

	private static final long serialVersionUID = 3510884234381426930L;

	@NotNull(message = "{message.code.null}")
	@NotEmpty(message = "{message.code.empty}")
	@Column(unique = true)
	private String code;

	@NotNull(message = "{message.content.null}")
	@NotBlank(message = "{message.content.blank}")
	private String content;

	@NotNull(message = "{message.date.null}")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@NotNull(message = "{message.messageStatus.null}")
	@Enumerated(EnumType.STRING)
	private MessageStatus messageStatus;

	@NotNull(message = "{message.fromUser.null}")
	@ManyToOne
	private User fromUser;

	@NotNull(message = "{message.toUser.null}")
	@ManyToOne
	private User toUser;

	@NotNull(message = "{message.chat.null}")
	@ManyToOne
	private Chat chat;

	Message() {

	}

	public Message(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonDeserialize(using = DateDeserializer.class)
	public Date getDate() {
		return date != null ? (Date) date.clone() : null;
	}

	public void setDate(Date date) {
		if (date != null)
			this.date = date;
	}

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
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
		Message other = (Message) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [code=" + code + ", content=" + content + ", date="
				+ date + ", messageStatus=" + messageStatus + ", fromUser="
				+ fromUser + ", toUser=" + toUser + ", chat=" + chat + "]";
	}

	// -------------- RELATIONS --------------

	void _setChat(Chat chat) {
		this.chat = chat;
	}
}
