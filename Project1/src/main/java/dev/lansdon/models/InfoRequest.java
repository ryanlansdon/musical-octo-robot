package dev.lansdon.models;

import javax.persistence.*;

@Entity
@Table(name="info_request")
public class InfoRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="editor_id")
	private Editor editor;
	@ManyToOne
	@JoinColumn(name="requested_id")
	private User reqUser;
	@Column(name="request")
	private String requestText;
	@Column(name="response")
	private String responseText;
	
	public String getRequestText() {
		return requestText;
	}
	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Editor getEditor() {
		return editor;
	}
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	public User getReqUser() {
		return reqUser;
	}
	public void setReqUser(User reqUser) {
		this.reqUser = reqUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reqUser == null) ? 0 : reqUser.hashCode());
		result = prime * result + ((requestText == null) ? 0 : requestText.hashCode());
		result = prime * result + ((responseText == null) ? 0 : responseText.hashCode());
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
		InfoRequest other = (InfoRequest) obj;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reqUser == null) {
			if (other.reqUser != null)
				return false;
		} else if (!reqUser.equals(other.reqUser))
			return false;
		if (requestText == null) {
			if (other.requestText != null)
				return false;
		} else if (!requestText.equals(other.requestText))
			return false;
		if (responseText == null) {
			if (other.responseText != null)
				return false;
		} else if (!responseText.equals(other.responseText))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InfoRequest [id=" + id + ", editor=" + editor + ", reqUser=" + reqUser + ", requestText=" + requestText
				+ ", responseText=" + responseText + "]";
	}
}
