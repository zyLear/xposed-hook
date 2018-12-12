package com.zylear.root.xposedhook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TestHook implements IXposedHookLoadPackage {

    XSharedPreferences xsp;
    Class Dex;
    Method Dex_getBytes;
    Method getDex;
    String packagename;


    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if ((!lpparam.packageName.equals("com.zylear.learn.myapplication"))) {
            XposedBridge.log("当前程序包名与设定不一致或者包名为空");
            return;
        }
        XposedBridge.log("进来了");

        XposedHelpers.findAndHookMethod("com.zylear.learn.myapplication.MainActivity", lpparam.classLoader, "show", new XC_MethodHook() {

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });
    }


}