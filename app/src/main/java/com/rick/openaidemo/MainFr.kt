package com.rick.openaidemo

import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.lifecycleScope
import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ToastUtils
import com.rick.jetpackmvvm.base.BaseFragment
import com.rick.openaidemo.databinding.FrMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFr : BaseFragment<FrMainBinding, MainVm>() {
    private val openAI by lazy {
//        OpenAI("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")
        OpenAI("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")
    }

    override fun initView(binding: FrMainBinding, vm: MainVm) {
        binding.vm = vm
        binding.fr = this
        binding.text.movementMethod = ScrollingMovementMethod.getInstance()
    }

    fun send() {
        val value = vm.input.value
        if (value.isBlank()) {
            ToastUtils.showLong("请输入内容")
            return
        }
        KeyboardUtils.hideSoftInput(requireActivity())
        vm.input.value = ""
        vm.text.value = value
        val req = CompletionRequest(
            model = ModelId("text-davinci-003"),
            prompt = value,
            maxTokens = 1000
        )
        lifecycleScope.launch {
            openAI.completions(req).collectLatest {
                vm.text.value += it.choices[0].text
                vm.text.value = vm.text.value
            }
        }
    }

    fun clear() {
        vm.input.value = ""
    }
}