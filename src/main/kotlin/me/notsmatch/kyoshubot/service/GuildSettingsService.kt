package me.notsmatch.kyoshubot.service

import me.notsmatch.kyoshubot.model.GuildSettings

class GuildSettingsService(val mongoService: MongoService, val boshuService: BoshuService) {

    fun getGuildSettings(guildId: Long) : GuildSettings {
        mongoService.apply {
            val doc = findGuildSettingsDocById(guildId)
            if(doc == null){
                val settings = GuildSettings(guildId, "everyone", 0, mutableListOf())
                replaceGuildSettings(guildId, settings.toDocument())
                return settings
            }
            return GuildSettings.fromDocument(doc)
        }
    }
}