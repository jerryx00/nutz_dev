package net.wendal.modules;


import net.wendal.nutzbook.bean.Master;
import net.wendal.nutzbook.bean.Pet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class MasterTest extends Assert {

    //private static final Log log = Logs.get();

    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    protected Dao dao;


    public void test_master_ins() {
        Master master = new Master();
        master.setName("master " + new Date());

        List<Pet> list = new ArrayList<Pet>();
        Pet pet1 = new Pet();
        pet1.setName("X1");

        Pet pet2 = new Pet();
        pet1.setName("X2");
        list.add(pet2);

        master.setPets(list);
        dao.insertWith(list, "master");
    }
}