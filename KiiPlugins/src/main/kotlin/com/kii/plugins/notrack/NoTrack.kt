package com.kii.plugins.notrack

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class NoTrack : Plugin() {
    override fun start(context: Context) {
        try {
            val analyticsClass = Class.forName("com.discord.utilities.analytics.AnalyticsTracker")
            patcher.before(analyticsClass, "track", String::class.java, Map::class.java) {
                it.result = null
            }
        } catch (e: Exception) {
            logger.warn("NoTrack: ${e.message}")
        }
        try {
            val sentryClass = Class.forName("io.sentry.android.core.SentryAndroid")
            patcher.before(sentryClass, "init", Context::class.java) {
                it.result = null
            }
        } catch (e: Exception) {
            logger.debug("NoTrack: Sentry not found")
        }
    }
    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
