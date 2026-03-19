package com.kii.plugins.alwaystrust

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class AlwaysTrust : Plugin() {
    override fun start(context: Context) {
        try {
            val trustClass = Class.forName("com.discord.utilities.uri.UriHandlerManager")
            patcher.before(trustClass, "showExternalLinkWarning", Context::class.java, String::class.java) { param ->
                val ctx = param.args[0] as? Context ?: return@before
                val url = param.args[1] as? String ?: return@before
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                ctx.startActivity(intent)
                param.result = null
            }
        } catch (e: Exception) {
            logger.warn("AlwaysTrust: ${e.message}")
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
