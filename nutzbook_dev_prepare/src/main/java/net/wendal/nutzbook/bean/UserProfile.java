package net.wendal.nutzbook.bean;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

/**
 * Created by Administrator on 2018/5/6.
 */

@Table("t_user_profile")
public class UserProfile extends BasePojo{

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Id(auto=false)
    @Column("uid")
    protected int userId;
    @Name
    @Column
    protected String nickname;
    @Column
    protected String email;
    @Column("email_checked")
    protected boolean emailChecked;
    @Column
    @JsonField(ignore=true)
    protected byte[] avatar;
    @Column
    protected String gender;
    @Column("dt")
    protected String description;
    @Column("loc")
    protected String location;
}
