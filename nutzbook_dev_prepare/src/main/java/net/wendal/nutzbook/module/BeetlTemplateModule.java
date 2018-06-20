package net.wendal.nutzbook.module;

import net.wendal.nutzbook.bean.User;
import net.wendal.nutzbook.bean.UserProfile;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

@IocBean // 声明为Ioc容器中的一个Bean, 这个不能少，否则就会出现 NullPointerException 异常

@At("/beetl") // 整个模块的路径前缀
public class BeetlTemplateModule extends BaseModule{

    private static final Log log = Logs.get();

    @At({"/hello/?", "/hello/"})
    @Ok("beetl:/hello.html")
    @Fail("void") // beelt的机制导致只能使用void,详细原因看nutzbook中的代码吧
    public Object hello() {
        log.debug("BeetlTemplateModule begin hello... ");
        QueryResult qr = new QueryResult();
        Pager pager = dao.createPager(1, 20);
        pager.setRecordCount(dao.count(UserProfile.class));
        log.debug("QueryResult qr: " + qr);
        log.debug("UserProfile count: " + dao.count(UserProfile.class));

        qr.setPager(pager);
        qr.setList(dao.query(UserProfile.class, null, pager));
        return qr;
    }


    @At("/json")
//    @Ok("beetl:/hello.html")
    @Fail("void") // beelt的机制导致只能使用void,详细原因看nutzbook中的代码吧
    public Object json() {
        log.debug("BeetlTemplateModule begin json... ");
        QueryResult qr = new QueryResult();
        Pager pager = dao.createPager(1, 20);
        pager.setRecordCount(dao.count(UserProfile.class));
        qr.setPager(pager);
        qr.setList(dao.query(UserProfile.class, null, pager));
        return qr;
    }


    @At("/query")
    @Ok("json:full")
    public Object query(@Param("name")String name, @Param("..")Pager pager) {
        log.debug("BeetlTemplateModule begin query... ");
        Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
        QueryResult qr = new QueryResult();
        qr.setList(dao.query(User.class, cnd, pager));
        pager.setRecordCount(dao.count(User.class, cnd));
        qr.setPager(pager);
        return qr; //默认分页是第1页,每页20条
    }
}
