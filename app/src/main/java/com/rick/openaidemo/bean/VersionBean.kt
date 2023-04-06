package com.rick.openaidemo.bean

import com.rick.jetpackmvvm.util.NetUtil

data class VersionBean(
    val versionShort: String,
    val build: Int,
    val changelog: String,
    val installUrl: String,
) : NetUtil.BaseResponse<VersionBean> {
    override fun getData(): VersionBean = this
}