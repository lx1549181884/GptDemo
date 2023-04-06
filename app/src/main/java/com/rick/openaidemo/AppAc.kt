package com.rick.openaidemo

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.rick.jetpackmvvm.base.BaseActivity
import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.jetpackmvvm.util.NetUtil
import com.rick.openaidemo.databinding.AcAppBinding
import com.rick.openaidemo.util.UpgradeUtil
import com.tencent.bugly.crashreport.CrashReport

class AppAc : BaseActivity<AcAppBinding, BaseViewModel>() {
    override fun init(binding: AcAppBinding, vm: BaseViewModel) {
        supportActionBar?.hide()
        CrashReport.initCrashReport(applicationContext, "44952c159f", true)
        ToastUtils.getDefaultMaker()
            .setBgColor(Color.BLACK)
            .setTextColor(Color.WHITE)
            .setGravity(Gravity.CENTER, 0, 0)
        LogUtils.getConfig().setLogHeadSwitch(false).setBorderSwitch(false)
        NetUtil.envList = listOf(NetUtil.Env("", "https://www.baidu.com"))
        UpgradeUtil.check(this)
    }
}