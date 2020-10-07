package com.robert.lostpets.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.robert.lostpets.entity.common.GenericEntity;
import com.robert.lostpets.entity.types.Role;
import com.robert.lostpets.entity.types.UserStatus;

@Entity
@Table(name = "TUser")
public class User extends GenericEntity<Long>
		implements UserDetails, GrantedAuthority {

	private static final long serialVersionUID = -2740750196437253336L;

	@NotNull(message = "{user.email.null}")
	@NotBlank(message = "{user.email.blank}")
	@Email(message = "{user.email.invalid}")
	@Column(unique = true)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "{user.password.null}")
	@NotBlank(message = "{user.password.blank}")
	private String password;

	@NotNull(message = "{user.role.null}")
	@Column(updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@NotNull(message = "{user.status.null}")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@NotNull(message = "{user.phone.null}")
	@NotBlank(message = "{user.phone.blank}")
	private String phone;

	@NotNull(message = "{user.firstName.null}")
	@NotBlank(message = "{user.firstName.blank}")
	private String firstName;

	@NotNull(message = "{user.lastName.null}")
	@NotBlank(message = "{user.lastName.blank}")
	private String lastName;

	@NotNull(message = "{user.ads.null}")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Ad> ads = new HashSet<>();

	User() {

	}

	public User(String email) {
		this.email = email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return getEmail();
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Set<Ad> getAds() {
		return Collections.unmodifiableSet(ads);
	}

	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(this);
		return authorities;
	}

	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	public String getAuthority() {
		return role.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", role=" + role + ", status=" + status
				+ ", phone=" + phone + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	// -------------- RELATIONS --------------

	Set<Ad> _getAds() {
		return ads;
	}

	public void addAd(Ad a) {
		a._setUser(this);
		ads.add(a);
	}

	public void removeAd(Ad a) {
		ads.remove(a);
		a._setUser(null);
	}
}
