package net.wendal.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("t_pet")
public class Pet extends Pojo {

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Column
    private  int mid;
    @Column
    @Name
    private  String name;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
    //key表示对方表字段的key, 即是target中的字段
    @One(target = Master.class, field="mid", key = "id")
    private Master master;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
