package net.wendal.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2018/5/15.
 */
@Table("t_tom")
public class Tom {
    @Id
    private int tid;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public Jerry getJerry() {
        return jerry;
    }

    public void setJerry(Jerry jerry) {
        this.jerry = jerry;
    }

    @Column
    private String name;

    @Column("jid")
    private int jid;

    //key表示对方表字段的key, 即是target中的字段
    @One(target = Jerry.class, field="jid", key = "id")
    private Jerry jerry;
}
