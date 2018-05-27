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

import java.util.Date;


@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class PetTest extends Assert {

    //private static final Log log = Logs.get();

    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    protected Dao dao;




    public void test_pet_ins() {
        Pet pet = new Pet();
        pet.setName("XiaoBai");
        pet.setCreateTime(new Date());
        pet.setUpdateTime(new Date());

        Master master = new Master();
        master.setName("Peter");
        master.setCreateTime(new Date());
        master.setUpdateTime(new Date());

        pet.setMaster(master);
//        dao.insert(master);

        dao.insertWith(pet, "master");

        assertNotNull(pet);
        assertNotNull(pet.getName());
    }


    public void test_pet_query() {
        Pet pet = dao.fetch(Pet.class, "XiaoBai3");
        dao.fetchLinks(pet, "master");

        Pet pet1 = dao.fetchLinks(dao.fetch(Pet.class, "XiaoBai5"), "master");

        assertNotNull(pet);
        assertNotNull(pet1);
    }


    public void test_pet_upd_link() {
        Pet pet = dao.fetch(Pet.class, "XiaoBai3");
        dao.fetchLinks(pet, "master");

        assertNotNull(pet);
    }
    public void test_pet_upd() {
        Pet pet = dao.fetch(Pet.class,"XiaoBai3");

//        Pet pet1 = dao.updateWith(dao.fetch(Pet.class, "XiaoBai5"), "master");
        dao.updateWith(pet,"master");

        assertNotNull(pet);
    }

    @Test
    public void test_pet_del() {
        Pet pet = dao.fetch(Pet.class, "XiaoBai5");
        dao.deleteWith(pet, "master");
    }

//    如下代码，将只 查询Pet 的 id 和 name 属性：
    @Test
    public void test_pet_dao1(){
        // 新的写法
        FieldFilter ff =  FieldFilter.create(Pet.class, "^id|name$");
        Pet pet1 = Daos.ext(dao, ff).fetch(Pet.class, 7);
    }

    //    如下代码，将只更新 Pet 的 id 和 name 属性：
    @Test
    public void test_pet_dao2(){

        Pet pet = dao.fetch(Pet.class, 8);
        pet.setName("ABC-8");
        pet.setCreateTime(new Date());
        pet.setUpdateTime(new Date());
        pet.setMid(3);

        // 新的写法
        FieldFilter ff =  FieldFilter.create(Pet.class, "^id|name$");

        int p1 = Daos.ext(dao, ff).update(pet);
    }
}