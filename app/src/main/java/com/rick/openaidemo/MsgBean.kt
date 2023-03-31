package com.rick.openaidemo

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatMessage

@OptIn(BetaOpenAI::class)
data class MsgBean constructor(val time: Long, var chatMsg: ChatMessage)