package net.wendal.nutzbook.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_user")
public class User extends BasePojo {

	@Id
	private int id;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column
	private int uid;
	@Name
	@Column
	private String name;
	@Column("passwd")
	private String password;
	@Column
	private String salt;

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	//key表示对方表字段的key, 即是target中的字段
	@One(target = UserProfile.class,field = "id",key="userId")
	protected UserProfile profile;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
