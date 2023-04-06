package com.rick.openaidemo.net

import com.rick.jetpackmvvm.util.NetUtil.createService
import com.rick.openaidemo.bean.VersionBean
import retrofit2.Call
import retrofit2.http.GET

open interface FirImService {
    companion object {
        @JvmField
        val INSTANCE = createService(FirImService::class.java, "http://api.bq04.com/")
    }

    @GET("apps/latest/64254eb40d81cc1bbe316b6b?api_token=eecb617bde22f87c9edb23ee06390a4d")
    fun latest(): Call<VersionBean>
}