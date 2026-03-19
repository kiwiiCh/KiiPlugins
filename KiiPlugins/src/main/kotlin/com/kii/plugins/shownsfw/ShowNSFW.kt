package com.kii.plugins.shownsfw

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class ShowNSFW : Plugin() {
    override fun start(context: Context) {
        try {
            val storeClass = Class.forName("com.discord.stores.StoreChannelsSelected")
            patcher.before(storeClass, "handleNsfwGate", Any::class.java) { param ->
                param.result = null
            }
        } catch (e: Exception) {
            logger.warn("ShowNSFW: ${e.message}")
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
