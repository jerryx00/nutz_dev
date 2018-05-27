package net.wendal.modules;


import net.wendal.nutzbook.bean.Master;
import net.wendal.nutzbook.bean.Pet;
import net.wendal.nutzbook.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;


@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class UserTest extends Assert {

    //private static final Log log = Logs.get();

    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    protected Dao dao;


    @Test
    public void test_dao_ok() {
        Dao dao = ioc.get(Dao.class);
        assertNotNull(dao);
    }

    @Test
    public void test_user_service_create_user() {
        dao.create(User.class, false);
        User user = new User();
        user.setName("wendal");
        user.setPassword("aa");
        user = dao.insert(user);

        user = dao.fetch(User.class, "wendal");
        assertNotNull(user);
        assertNotNull(user.getName());
        dao.delete(user);
    }

}
