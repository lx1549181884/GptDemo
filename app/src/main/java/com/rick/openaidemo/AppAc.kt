package com.rick.openaidemo

import com.rick.jetpackmvvm.base.BaseActivity
import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.openaidemo.databinding.AcAppBinding
import com.rick.openaidemo.util.UpgradeUtil

class AppAc : BaseActivity<AcAppBinding, BaseViewModel>() {
    override fun init(binding: AcAppBinding, vm: BaseViewModel) {
        supportActionBar?.hide()
        UpgradeUtil.check(this)
    }
}