package com.rick.openaidemo

import android.graphics.Color
import android.view.Gravity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ToastUtils
import com.rick.jetpackmvvm.base.BaseFragment
import com.rick.jetpackmvvm.base.BaseListAdapter
import com.rick.openaidemo.databinding.FrMainBinding
import com.rick.openaidemo.databinding.ItemChatBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@BetaOpenAI
class MainFr : BaseFragment<FrMainBinding, MainVm>() {
    private val openAI by lazy {
//        OpenAI("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")
        OpenAI("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")
    }

    override fun initView(binding: FrMainBinding, vm: MainVm) {
        binding.vm = vm
        binding.fr = this
        binding.adapter = Adapter()
    }

    fun send() {
        // 输入内容检查
        val value = vm.input.value
        if (value.isBlank()) {
            ToastUtils.showLong("请输入内容")
            return
        }
        // 清空输入内容，隐藏软键盘
        vm.input.value = ""
        KeyboardUtils.hideSoftInput(requireActivity())

        // 创建添加用户消息
        val userChatMsg = ChatMessage(ChatRole.User, value)
        val time = System.currentTimeMillis()
        vm.msgs.value.add(MsgBean(time, userChatMsg))
        vm.msgs.value = vm.msgs.value

        // 创建请求
        val req =
            ChatCompletionRequest(ModelId("gpt-3.5-turbo"), vm.msgs.value.map { it.chatMsg })

        // 创建添加助手消息
        val assistantMsg =
            MsgBean(time + 1, ChatMessage(role = ChatRole.Assistant, ""))
        vm.msgs.value.add(assistantMsg)
        vm.msgs.value = vm.msgs.value

        // 发起请求
        lifecycleScope.launch {
            openAI.chatCompletions(req).collectLatest {
                it.choices[0].delta?.content?.let { c ->
                    // 更新助手消息
                    val index = vm.msgs.value.indexOfFirst { it.time == assistantMsg.time }
                    val msgBean = vm.msgs.value[index]
                    msgBean.chatMsg =
                        ChatMessage(role = ChatRole.Assistant, msgBean.chatMsg.content + c)
                    binding.adapter?.notifyItemChanged(index)
                }
            }
        }
    }

    fun clearInput() {
        vm.input.value = ""
    }

    fun clearList() {
        vm.msgs.value = mutableListOf()
    }

    class Adapter :
        BaseListAdapter<MsgBean, ItemChatBinding>(object : DiffUtil.ItemCallback<MsgBean>() {
            override fun areItemsTheSame(oldItem: MsgBean, newItem: MsgBean) =
                oldItem.time == newItem.time

            override fun areContentsTheSame(oldItem: MsgBean, newItem: MsgBean) = false
        }) {
        override fun initItem(position: Int, bean: MsgBean, binding: ItemChatBinding) {
            if (bean.chatMsg.role == ChatRole.User) {
                binding.layout.gravity = Gravity.END
                binding.text.setBackgroundResource(R.drawable.bubble_primary)
                binding.text.setTextColor(Color.WHITE)
            } else {
                binding.layout.gravity = Gravity.START
                binding.text.setBackgroundResource(R.drawable.bubble_gray)
                binding.text.setTextColor(Color.BLACK)
            }
            binding.text.text = bean.chatMsg.content
        }
    }
}