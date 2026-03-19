package com.kii.plugins.silenttyping

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.before
import com.discord.stores.StoreUserTyping

@AliucordPlugin
class SilentTyping : Plugin() {
    override fun start(context: Context) {
        patcher.before<StoreUserTyping>("setUserTyping", Long::class.java) {
            it.result = null
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
