package com.kii.plugins.messagelogger

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.discord.models.message.Message
import com.discord.stores.StoreStream

@AliucordPlugin
class MessageLogger : Plugin() {
    companion object {
        val deletedMessages = mutableMapOf<Long, String>()
    }
    override fun start(context: Context) {
        try {
            val storeClass = Class.forName("com.discord.stores.StoreMessages")
            patcher.before(storeClass, "handleMessageDelete",
                Long::class.java, Long::class.java, Boolean::class.java
            ) { param ->
                val channelId = param.args[0] as Long
                val messageId = param.args[1] as Long
                val msg = StoreStream.getMessages().getMessage(channelId, messageId)
                if (msg != null) {
                    deletedMessages[messageId] = msg.content ?: ""
                    logger.info("Deleted: ${msg.author?.username}: ${msg.content}")
                }
            }
        } catch (e: Exception) {
            logger.warn("MessageLogger: ${e.message}")
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
