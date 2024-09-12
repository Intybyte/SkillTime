package me.vaan.skillTime

import org.bukkit.configuration.file.FileConfiguration

typealias SkillMultiplier = Pair<String, Double>

object TimeStorage {
    val timeMap = HashMap<IntRange, List<SkillMultiplier>>()

    fun loadConfig(config: FileConfiguration) {

        for (line in config.getKeys(false)) {
            val time = line.split("-")
            if (time.size != 2) {
                throw RuntimeException("Couldn't read line: $line, format must be <Int>-<Int>")
            }

            val range = parseString(time[0]) .. parseString(time[1])

            val allSkillEntries = config.getConfigurationSection(line)!!.getKeys(false)
            val skillList = ArrayList<SkillMultiplier>(allSkillEntries.size)

            for (skillEntry in allSkillEntries) {
                val multiplier = config.getDouble("$line.$skillEntry")
                skillList.add(SkillMultiplier(skillEntry, multiplier))
            }

            timeMap[range] = skillList
        }

    }

    private fun parseString(s: String) : Int {
        return when(s) {
            "day" -> 1000
            "noon" -> 6000
            "night" -> 13000
            "midnight" -> 18000
            else -> {
                val parse = s.toInt()
                parse
            }
        }
    }
}