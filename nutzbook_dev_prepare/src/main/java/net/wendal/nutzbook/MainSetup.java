package net.wendal.nutzbook;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.wendal.nutzbook.bean.User;

import org.apache.commons.mail.HtmlEmail;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.quartz.Scheduler;

public class MainSetup implements Setup {
	private static final Log log = Logs.get();

	public void init(NutConfig conf) {
		Ioc ioc = conf.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "net.wendal.nutzbook", false);
		
		// 初始化默认根用户
		if (dao.count(User.class) == 0) {
			log.debug("开始初始化admin用户......");
			User user = new User();
			user.setName("admin");
			user.setPassword("123456");
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			dao.insert(user);
		}
		
		// 获取quartz的Scheduler,这样就自动触发了计划任务的启动
		ioc.get(Scheduler.class);


		// 测试发送邮件,可以用UserProfileModule中来测试
//		try {
//			log.info("开始发送邮件......");
//            Date day=new Date();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            //邮件开始
//			HtmlEmail email = ioc.get(HtmlEmail.class);
//			email.setSubject("测试NutzBook");
//            email.setMsg("This is a test mail ... :-)" + df.format(day));
//			email.addTo("8688041@qq.com");//请务必改成您自己的邮箱啊!!!
//			email.buildMimeMessage();
//			email.sendMimeMessage();
//		} catch (Exception e) {
//            log.info("发送邮件失败......");
//			e.printStackTrace();
//		}
	}
	
	public void destroy(NutConfig conf) {
	}

}
