package net.wendal.nutzbook.module;

import net.wendal.nutzbook.bean.Pet;
import net.wendal.nutzbook.bean.User;
import net.wendal.nutzbook.bean.UserProfile;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

@IocBean // 声明为Ioc容器中的一个Bean
@At("/form") // 整个模块的路径前缀
@Fail("http:500") // 抛出异常的话,就走500页面
@Filters

public class FormModule extends BaseModule {
	private static final Log log = Logs.get();


	@At("/add")
	@Ok("jsp:jsp.form.add") // 真实路径是 /WEB-INF/jsp/user/list.jsp
	public void add(@Attr("me")int me) {
		log.debug("me is:" + me);
	}

	@At("/petadd")
	@Ok("jsp:jsp.form.pet_add") // 真实路径是 /WEB-INF/jsp/user/list.jsp
	public void petadd(@Attr("me")int me) {
		log.debug("me is:" + me);
	}


	@At("/petinsert")
	public Object petinsert(@Param("::pet")Pet pet) { // 两个点号是按对象属性一一设置
		log.debug("Form ->petinsert" );
		NutMap re = new NutMap();
		pet = dao.insert(pet);
		return re.setv("ok", true).setv("data", pet);
	}


	@At
	public Object insert(@Param("..")User user) { // 两个点号是按对象属性一一设置
		log.debug("Form ->insert" );
		NutMap re = new NutMap();
		user = dao.insert(user);
		return re.setv("ok", true).setv("data", user);
	}
	
	@At
	public Object update(@Param("..")User user) {
		NutMap re = new NutMap();
		user.setName(null);// 不允许更新用户名
		user.setCreateTime(null);//也不允许更新创建时间
		user.setUpdateTime(new Date());// 设置正确的更新时间
		dao.updateIgnoreNull(user);// 真正更新的其实只有password和salt
		return re.setv("ok", true);
	}


	@Filters
	@At
	@Fail("jsp:jsp.500")
	public void error() {
		throw new RuntimeException();
	}
}
