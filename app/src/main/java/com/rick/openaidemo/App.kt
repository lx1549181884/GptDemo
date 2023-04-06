package com.rick.openaidemo

import android.app.Application
import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.rick.jetpackmvvm.util.NetUtil
import com.tencent.bugly.crashreport.CrashReport

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "44952c159f", true)
        ToastUtils.getDefaultMaker()
            .setBgColor(Color.BLACK)
            .setTextColor(Color.WHITE)
            .setGravity(Gravity.CENTER, 0, 0)
        LogUtils.getConfig().setLogHeadSwitch(false).setBorderSwitch(false)
        NetUtil.envList = listOf(NetUtil.Env("", "https://www.baidu.com"))
    }
}