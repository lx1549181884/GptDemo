package com.rick.openaidemo.util

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.rick.jetpackmvvm.base.BaseDialog
import com.rick.jetpackmvvm.util.NetUtil
import com.rick.jetpackmvvm.util.NetUtil.download
import com.rick.openaidemo.bean.VersionBean
import com.rick.openaidemo.databinding.DialogUpgradeBinding
import com.rick.openaidemo.net.FirImService
import com.rick.openaidemo.vm.UpgradeVm

/**
 * 升级工具
 */
class UpgradeUtil private constructor(private val bean: VersionBean) :
    BaseDialog<DialogUpgradeBinding, UpgradeVm>() {
    override fun initView(binding: DialogUpgradeBinding, viewModel: UpgradeVm) {
        isCancelable = false
        viewModel.versionInfo.value = bean
        binding.vm = viewModel
        binding.dialog = this
    }

    /**
     * 开始下载
     */
    fun startDownload() {
        FileUtils.deleteFilesInDirWithFilter(requireContext().cacheDir) { it.name.endsWith(".apk") }
        download(
            viewLifecycleOwner,
            bean.installUrl,
            apkPath,
            {
                vm.state.value = 2
                install()
            },
            { _: Int?, _: String? ->
                vm.state.value = 3
            }
        ) {
            vm.state.value = 1
            vm.progress.setValue(it * 100)
        }
    }

    private val apkPath: String
        get() = requireContext().cacheDir.absolutePath + "/" + bean.versionShort + ".apk"

    /**
     * 安装
     */
    fun install() {
        AppUtils.installApp(apkPath)
    }

    companion object {
        @JvmStatic
        fun check(activity: AppCompatActivity) {
            NetUtil.request(
                activity,
                FirImService.INSTANCE.latest(),
                {
                    it?.let {
                        if (AppUtils.getAppVersionCode() < it.build) UpgradeUtil(it).show(
                            activity.supportFragmentManager,
                            null
                        )
                    }
                }
            )
        }

        /**
         * 更新按钮
         */
        @JvmStatic
        @BindingAdapter("upgradeState", "upgradeProgress")
        fun upgradeBtnText(view: Button, upgradeState: Int, upgradeProgress: Double) {
            when (upgradeState) {
                0 -> {
                    view.text = "立即更新"
                    view.isEnabled = true
                }
                1 -> {
                    view.text = String.format("下载中 %1.1f%%", upgradeProgress)
                    view.isEnabled = false
                }
                2 -> {
                    view.text = "下载成功，点击安装"
                    view.isEnabled = true
                }
                3 -> {
                    view.text = "下载失败，点击重试"
                    view.isEnabled = true
                }
            }
        }
    }
}