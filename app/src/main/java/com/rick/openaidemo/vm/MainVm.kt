package com.rick.openaidemo.vm

import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.jetpackmvvm.other.NoNullLiveData
import com.rick.openaidemo.bean.MsgBean

class MainVm : BaseViewModel() {
    var input = NoNullLiveData("")
    var msgs = NoNullLiveData<MutableList<MsgBean>>(mutableListOf())
}