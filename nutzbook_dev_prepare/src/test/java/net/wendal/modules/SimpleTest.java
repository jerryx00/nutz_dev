package net.wendal.modules;


import net.wendal.nutzbook.bean.Jerry;
import net.wendal.nutzbook.bean.Master;
import net.wendal.nutzbook.bean.Pet;
import net.wendal.nutzbook.bean.Tom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;


@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class SimpleTest extends Assert {

    //private static final Log log = Logs.get();

    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    protected Dao dao;


//    @Test
//    public void test_dao_ok() {
//        Dao dao = ioc.get(Dao.class);
//        assertNotNull(dao);
//    }


    @Test
    public void test_user_service_create_user() {
        Pet pet = new Pet();
        pet.setName("XiaoBai");
        Master master = new Master();
        master.setName("Peter");
        pet.setMaster(master);

        dao.insertWith(pet, "mid");

        assertNotNull(pet);
        assertNotNull(pet.getName());
    }

    @Test
    public void test_tom_jerry(){
        Tom tom=new Tom();
        tom.setName("汉克");
        Jerry jerry=new Jerry();
        jerry.setName("可可");
        tom.setJerry(jerry);
        dao.insertWith(tom, "jerry");

    }
}