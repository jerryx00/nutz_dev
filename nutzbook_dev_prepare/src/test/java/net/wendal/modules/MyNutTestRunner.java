package net.wendal.modules;

import net.wendal.nutzbook.MainModule;
import org.junit.runners.model.InitializationError;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mock.NutTestRunner;


public class MyNutTestRunner extends NutTestRunner  {
    public MyNutTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    public Class<?> getMainModule() {
        return MainModule.class;
    }

    /**
     * 可覆盖createIoc,实现参数覆盖, bean替换,等定制.
     */
    protected Ioc createIoc() {
        Ioc ioc = super.createIoc();
        PropertiesProxy conf = ioc.get(PropertiesProxy.class, "conf");
//        conf.put("db.url", "jdbc:h2:~/test");
        return ioc;
    }
}
