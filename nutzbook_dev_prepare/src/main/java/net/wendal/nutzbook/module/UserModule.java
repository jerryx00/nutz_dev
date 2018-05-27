package net.wendal.nutzbook.module;

import java.util.Date;

import javax.servlet.http.HttpSession;

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
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

@IocBean // 声明为Ioc容器中的一个Bean
@At("/user") // 整个模块的路径前缀
@Ok("json:{locked:'password|salt',ignoreNull:true}") // 忽略password和salt属性,忽略空属性的json输出
@Fail("http:500") // 抛出异常的话,就走500页面
@Filters(@By(type=CheckSession.class, args={"me", "/"})) // 检查当前Session是否带me这个属性
public class UserModule extends BaseModule {
	private static final Log log = Logs.get();
	@At
	@Filters
	public int count(HttpSession session) { // 统计用户数的方法,算是个测试点
		log.debug("session " + session.getAttribute("me"));
		int cnt = dao.count(User.class);
		log.debug("cnt is:" + cnt);
		return cnt;
	}
	
	@At
	@Filters // 覆盖UserModule类的@Filter设置,因为登陆可不能要求是个已经登陆的Session
	public Object login(@Param("name")String name, @Param("password")String password, HttpSession session) {
		//空串检查
		if (!checkUserPasswd(name) || !checkUserPasswd(password)){
			return false;
		}
		User user = dao.fetch(User.class, Cnd.where("name", "=", name).and("password", "=", password));
		log.debug("user'id is:" + user.getId() + ",session " + session.getAttribute("me"));
		if (user == null) {
			return false;
		} else {
			session.setAttribute("me", user.getId());
			return true;
		}
	}
	
	@At
	@Ok(">>:/") // 跟其他方法不同,这个方法完成后就跳转首页了
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@At
	public Object add(@Param("..")User user) { // 两个点号是按对象属性一一设置
		NutMap re = new NutMap();
		String msg = checkUser(user, true);
		if (msg != null){
			return re.setv("ok", false).setv("msg", msg);
		}
		user = dao.insert(user);
		return re.setv("ok", true).setv("data", user);
	}
	
	@At
	public Object update(@Param("..")User user) {
		NutMap re = new NutMap();
		String msg = checkUser(user, false);
		if (msg != null){
			return re.setv("ok", false).setv("msg", msg);
		}
		user.setName(null);// 不允许更新用户名
		user.setCreateTime(null);//也不允许更新创建时间
		user.setUpdateTime(new Date());// 设置正确的更新时间
		dao.updateIgnoreNull(user);// 真正更新的其实只有password和salt
		return re.setv("ok", true);
	}
	
	@At
	public Object delete(@Param("id")int id, @Attr("me")int me) {
		if (me == id) {
			return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户!!");
		}
		dao.delete(User.class, id); // 再严谨一些的话,需要判断是否为>0
        dao.clear(UserProfile.class, Cnd.where("userId", "=", id));
		return new NutMap().setv("ok", true);
	}
	
	@At
	@Filters
	public Object query(@Param("name")String name, @Param("..")Pager pager) {
		Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
		QueryResult qr = new QueryResult();
		qr.setList(dao.query(User.class, cnd, pager));
		pager.setRecordCount(dao.count(User.class, cnd));
		qr.setPager(pager);
		return qr; //默认分页是第1页,每页20条
	}
	
	@At(value = {"/list","/index"})
	@Ok("jsp:jsp.user.list") // 真实路径是 /WEB-INF/jsp/user/list.jsp
	public void index(@Attr("me")int me) {
		log.debug("me is:" + me);
	}

	/**
	 * 检查是否空串
	 * @param str
	 * @return
	 */
	protected boolean checkUserPasswd(String str) {
		if (str == null || "".equals(str.trim())){
			return false;
		}
		return true;
	}
	protected String checkUser(User user, boolean create) {
		if (user == null) {
			return "空对象";
		}
		if (create) {
			if (Strings.isBlank(user.getName()) || Strings.isBlank(user.getPassword()))
				return "用户名/密码不能为空";
		} else {
			if (Strings.isBlank(user.getPassword()))
				return "密码不能为空";
		}
		String passwd = user.getPassword().trim();
		if (6 > passwd.length() || passwd.length() > 12) {
			return "密码长度错误";
		}
		user.setPassword(passwd);
		if (create) {
			int count = dao.count(User.class, Cnd.where("name", "=", user.getName()));
			if (count != 0) {
				return "用户名已经存在";
			}
		} else {
			if (user.getId() < 1) {
				return "用户Id非法";
			}
		}
		if (user.getName() != null)
			user.setName(user.getName().trim());
		return null;
	}
	
	@Filters
	@At
	@Fail("jsp:jsp.500")
	public void error() {
		throw new RuntimeException();
	}
}
