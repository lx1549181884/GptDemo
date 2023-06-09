package com.rick.openaidemo

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.rick.jetpackmvvm.base.BaseActivity
import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.openaidemo.databinding.AcAppBinding
import com.tencent.bugly.crashreport.CrashReport

class AppAc : BaseActivity<AcAppBinding, BaseViewModel>() {
    override fun init(binding: AcAppBinding, vm: BaseViewModel) {
        CrashReport.initCrashReport(applicationContext, "44952c159f", true);
        supportActionBar?.hide()
        ToastUtils.getDefaultMaker().setBgColor(Color.BLACK).setTextColor(Color.WHITE)
            .setGravity(Gravity.CENTER, 0, 0)
    }
}