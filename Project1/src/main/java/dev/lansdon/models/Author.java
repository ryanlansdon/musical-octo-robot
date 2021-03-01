package dev.lansdon.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	private Integer points;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="author_id")
	private Set<Pitch> pitches;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="requested_id")
	private Set<InfoRequest> requests;

	public Author() {
		points = 0;
	}
	
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
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Set<Pitch> getPitches() {
		return pitches;
	}
	public void setPitches(Set<Pitch> pitches) {
		this.pitches = pitches;
	}
	public Set<InfoRequest> getRequests() {
		return requests;
	}
	public void setRequests(Set<InfoRequest> requests) {
		this.requests = requests;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitches == null) ? 0 : pitches.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((requests == null) ? 0 : requests.hashCode());
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
		Author other = (Author) obj;
		if (pitches == null) {
			if (other.pitches != null)
				return false;
		} else if (!pitches.equals(other.pitches))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (requests == null) {
			if (other.requests != null)
				return false;
		} else if (!requests.equals(other.requests))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", user=" + user + ", points=" + points + ", pitches=" + pitches + ", requests=" + requests + "]";
	}
}
