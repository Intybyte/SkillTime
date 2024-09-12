package me.vaan.skillTime

import dev.aurelium.auraskills.api.event.skill.XpGainEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object TimeXPListener : Listener {

    @EventHandler
    fun onXpGain(event: XpGainEvent) {
        val time = event.player.world.time
        val skillName = event.skill.name().lowercase()

        for (entry in TimeStorage.timeMap) {
            if (time !in entry.key) continue

            for (skillEntry in entry.value) {
                if (!skillEntry.first.contains(skillName)) continue
                event.amount *= skillEntry.second
            }
        }
    }
}