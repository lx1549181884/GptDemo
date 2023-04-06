package com.rick.openaidemo.vm

import androidx.lifecycle.MutableLiveData
import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.jetpackmvvm.other.NoNullLiveData
import com.rick.openaidemo.bean.VersionBean

class UpgradeVm : BaseViewModel() {
    var versionInfo = MutableLiveData<VersionBean>()
    var progress = NoNullLiveData(0.0) // 下载进度0.0-100.0
    var state = NoNullLiveData(0) // 0未下载 1下载中 2下载成功 3下载失败
}