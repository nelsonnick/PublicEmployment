package com.wts.common;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import com.wts.controller.MainController;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {

    /**
     * 配置常量
     */
    @Override
    public void configConstant(Constants me) {
        PropKit.use("a_little_config.txt");
        me.setInjectDependency(true);
        me.setDevMode(false);
    }

    /**
     * 配置路由
     */
    @Override
    public void configRoute(Routes me) {
        me.add("/", MainController.class);
    }

    /**
     * 配置插件
     */
    @Override
    public void configPlugin(Plugins me) {
        RedisPlugin testRedis = new RedisPlugin("test", "localhost");
        me.add(testRedis);
    }

    /**
     * 配置全局拦截器
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new TxByMethods("save","update"));
    }

    /**
     * 配置处理器
     */
    @Override
    public void configHandler(Handlers me) {
        // 设置上下文路径
        me.add(new ContextPathHandler("contextPath"));
    }

    /**
     * 配置模板引擎
     */
    @Override
    public void configEngine(Engine me) {}
    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/");
    }
}
