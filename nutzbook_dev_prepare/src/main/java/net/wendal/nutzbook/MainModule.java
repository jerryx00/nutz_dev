package net.wendal.nutzbook;

import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
										   "*anno", "net.wendal",
										   "*tx"})
@Modules(scanPackage=true)
@ChainBy(args="mvc/nutzbook-mvc-chain.js")
@Views({BeetlViewMaker.class})

@Ok("json:full")
@Fail("jsp:jsp.500")
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
public class MainModule {
}
