package com.kii.plugins.notypingindicator

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class NoTypingIndicator : Plugin() {
    override fun start(context: Context) {
        try {
            val storeClass = Class.forName("com.discord.stores.StoreUserTyping")
            patcher.before(storeClass, "getTypingUsersForChannel", Long::class.java) { param ->
                param.result = emptyMap<Long, Long>()
            }
        } catch (e: Exception) {
            logger.warn("NoTypingIndicator: ${e.message}")
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
