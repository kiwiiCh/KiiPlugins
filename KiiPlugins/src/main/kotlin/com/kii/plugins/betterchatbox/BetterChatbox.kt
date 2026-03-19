package com.kii.plugins.betterchatbox

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.utils.DimenUtils.dp

@AliucordPlugin
class BetterChatbox : Plugin() {
    override fun start(context: Context) {
        logger.info("BetterChatbox started!")
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
