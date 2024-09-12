package me.vaan.skillTime

import org.bukkit.plugin.java.JavaPlugin

class SkillTime : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        TimeStorage.loadConfig(config)
        server.pluginManager.registerEvents(TimeXPListener, this)
    }
}
