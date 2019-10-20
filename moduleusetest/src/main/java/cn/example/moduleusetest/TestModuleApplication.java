package cn.example.moduleusetest;

import android.app.Application;

import cn.example.baselib.module.Module;
import cn.example.module_annotation.AnMethod;
import cn.example.module_annotation.Modules;

@Modules
public class TestModuleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
