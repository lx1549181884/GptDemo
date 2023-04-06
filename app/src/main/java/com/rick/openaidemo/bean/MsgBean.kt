package com.rick.openaidemo.bean

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatMessage

@OptIn(BetaOpenAI::class)
data class MsgBean constructor(val time: Long, var chatMsg: ChatMessage)