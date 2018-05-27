package net.wendal.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.Date;

public abstract class Pojo {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	private int id;

	@Column("ct")
	protected Date createTime;
	@Column("ut")
	protected Date updateTime;
	
	public String toString() {
		return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
