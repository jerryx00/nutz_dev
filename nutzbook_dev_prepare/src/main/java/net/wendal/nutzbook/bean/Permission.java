package net.wendal.nutzbook.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_permission")
public class Permission extends BasePojo {

    @Id
    protected long id;
    @Name
    protected String name;
    @Column("al")
    protected String alias;
    @Column("dt")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;
}