package net.wendal.modules;


import net.wendal.nutzbook.bean.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class TomTest extends Assert {

    //private static final Log log = Logs.get();

    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    protected Dao dao;


    @Test
    public void test_tom_jerry_create() {
        Tom tom=new Tom();
        tom.setName("汉克");
        Jerry jerry=new Jerry();
        jerry.setName("可可");
        tom.setJerry(jerry);

        dao.insertWith(tom, "jerry");

        assertNotNull(tom);
    }


    @Test
    public void test_upd(){
        dao.update(Tom.class, Chain.make("name","zz-1"), Cnd.where("tid","<",10));
    }

    @Test
    public void test_query1(){
        List<Tom> userList = dao.query(Tom.class, Cnd.wrap("name like '%zz%' and tid<5"), dao.createPager(2, 4));
    }
}
