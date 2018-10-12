package com.wts.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;


public class MainController extends Controller {

    public void index() {
        Cache cache = Redis.use("test");
        cache.set("key", "4444444");

        renderText(cache.get("key").toString());
    }

}
