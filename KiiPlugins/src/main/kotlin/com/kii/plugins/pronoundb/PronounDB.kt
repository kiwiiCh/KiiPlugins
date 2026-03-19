package com.kii.plugins.pronoundb

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

@AliucordPlugin
class PronounDB : Plugin() {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val cache = mutableMapOf<Long, String>()
    private val pronounsMap = mapOf(
        "hh" to "he/him", "sh" to "she/her", "tt" to "they/them",
        "any" to "any pronouns", "ask" to "ask me", "avoid" to "avoid pronouns"
    )

    override fun start(context: Context) {
        logger.info("PronounDB started!")
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
        scope.cancel()
    }
}
