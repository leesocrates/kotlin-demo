package cn.example.businesstwo;

import cn.example.baselib.module.Module;
import cn.example.module_annotation.AnMethod;
import cn.example.module_annotation.Modules;

@Modules()
public class BsTwoModule implements Module {
    @Override
    public void onCreate() {

    }

    @AnMethod(name = "lee")
    @Override
    public void onPostCreate() {

    }
}
