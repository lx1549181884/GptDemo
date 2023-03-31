package com.rick.openaidemo

import com.rick.jetpackmvvm.base.BaseViewModel
import com.rick.jetpackmvvm.other.NoNullLiveData

class MainVm : BaseViewModel() {
    var input = NoNullLiveData("")
    var msgs = NoNullLiveData<MutableList<MsgBean>>(mutableListOf())
}