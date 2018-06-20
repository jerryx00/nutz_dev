package net.wendal.nutzbook;

import jetbrick.template.web.nutz.JetTemplateViewMaker;
import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
										   "*anno", "net.wendal",
										   "*tx"})
@Modules(scanPackage=true)
@ChainBy(args="mvc/nutzbook-mvc-chain.js")
//集成Beetl模板
@Views({BeetlViewMaker.class})

//集成jetbrick-template-2x模板
//@Views({JetTemplateViewMaker.class})

@Ok("json:full")
@Fail("jsp:jsp.500")
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
public class MainModule {
}
