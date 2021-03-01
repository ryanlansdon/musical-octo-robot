package dev.lansdon.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Editor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToMany
	@JoinTable(name="genre_committee",
	joinColumns=@JoinColumn(name="editor_id"),
	inverseJoinColumns=@JoinColumn(name="genre_id"))
	private Set<Genre> genres;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="editor_id")
	private Set<InfoRequest> outRequests;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="requested_id")
	private Set<InfoRequest> inRequests;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Set<Genre> getGenres() {
		return genres;
	}
	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
	public Set<InfoRequest> getOutRequests() {
		return outRequests;
	}
	public void setOutRequests(Set<InfoRequest> outRequests) {
		this.outRequests = outRequests;
	}
	public Set<InfoRequest> getInRequests() {
		return inRequests;
	}
	public void setInRequests(Set<InfoRequest> inRequests) {
		this.inRequests = inRequests;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inRequests == null) ? 0 : inRequests.hashCode());
		result = prime * result + ((outRequests == null) ? 0 : outRequests.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Editor other = (Editor) obj;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inRequests == null) {
			if (other.inRequests != null)
				return false;
		} else if (!inRequests.equals(other.inRequests))
			return false;
		if (outRequests == null) {
			if (other.outRequests != null)
				return false;
		} else if (!outRequests.equals(other.outRequests))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Editor [id=" + id + ", user=" + user + ", role=" + role + ", genres=" + genres + ", outRequests="
				+ outRequests + ", inRequests=" + inRequests + "]";
	}
}
