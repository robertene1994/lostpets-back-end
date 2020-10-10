package com.robert.lostpets.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.robert.lostpets.entity.common.GenericEntity;
import com.robert.lostpets.entity.types.MessageStatus;

@Entity
@Table(name = "TChat")
public class Chat extends GenericEntity<Long> {

	private static final long serialVersionUID = -5092839172723500124L;

	@NotNull(message = "{chat.code.null}")
	@NotEmpty(message = "{chat.code.empty}")
	@Column(unique = true)
	private String code;

	@Transient
	private User fromUser;

	@Transient
	private User toUser;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private Message lastMessage;

	@Transient
	private Long unreadMessages;

	@NotNull(message = "{chat.messages.null}")
	@OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
	@OrderBy("date ASC")
	private Set<Message> messages = new HashSet<>();

	Chat() {

	}

	public Chat(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getFromUser() {
		if (fromUser == null && !messages.isEmpty())
			fromUser = messages.iterator().next().getFromUser();
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		if (toUser == null && !messages.isEmpty())
			toUser = messages.iterator().next().getToUser();
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Message getLastMessage() {
		if (lastMessage == null) {
			lastMessage = Collections.max(messages,
					Comparator.comparing(Message::getDate));
			lastMessage.setChat(null);
		}
		return lastMessage;
	}

	public void setLastMessage(Message lastMessage) {
		this.lastMessage = lastMessage;
	}

	public void setLastMessageCopy(Message lastMessage) {
		this.lastMessage = new Message(lastMessage.getCode());
		this.lastMessage.setId(lastMessage.getId());
		this.lastMessage.setContent(lastMessage.getContent());
		this.lastMessage.setDate(lastMessage.getDate());
		this.lastMessage.setMessageStatus(lastMessage.getMessageStatus());
		this.lastMessage.setFromUser(lastMessage.getFromUser());
		this.lastMessage.setToUser(lastMessage.getToUser());
	}

	public Long getUnreadMessages() {
		return unreadMessages;
	}

	public void setUnreadMessagesByUser(User user) {
		unreadMessages = messages.stream().filter(m -> m.getToUser()
				.equals(user)
				&& (m.getMessageStatus().equals(MessageStatus.SENT) || m
						.getMessageStatus().equals(MessageStatus.DELIVERED)))
				.count();
	}

	@JsonIgnore
	public Set<Message> getMessages() {
		return Collections.unmodifiableSet(messages);
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
		Chat other = (Chat) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chat [code=" + code + ", fromUser=" + fromUser + ", toUser="
				+ toUser + ", lastMessage=" + lastMessage + ", unreadMessages="
				+ unreadMessages + "]";
	}
}
