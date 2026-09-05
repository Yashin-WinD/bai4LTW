package vn.ute.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "`User`")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name = "username")
	private String userName;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "password")
	private String passWord;

	private String avatar;

	@Column(name = "roleid")
	private int roleid;

	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate")
	private Date createdDate;

	/** 0: chưa kích hoạt, 1: đã kích hoạt */
	private Integer status;

	private String otp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "otpExpire")
	private java.util.Date otpExpire;

	public User() {
	}

	public User(int id, String email, String userName, String fullName, String passWord, String avatar, int roleId,
			String phone, Date createdDate) {
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleId;
		this.phone = phone;
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return status == null || status == 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleId() {
		return roleid;
	}

	public void setRoleId(int roleId) {
		this.roleid = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public java.util.Date getOtpExpire() {
		return otpExpire;
	}

	public void setOtpExpire(java.util.Date otpExpire) {
		this.otpExpire = otpExpire;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", fullName=" + fullName + ", avatar="
				+ avatar + ", roleId=" + roleid + ", phone=" + phone + ", createdDate=" + createdDate + "]";
	}
}
