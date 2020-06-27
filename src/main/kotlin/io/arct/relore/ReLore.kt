package io.arct.relore

import io.arct.relore.listeners.PickupListener
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class ReLore : JavaPlugin() {
    val items: MutableList<TrackedItem> = mutableListOf()

    override fun onEnable() {
        saveDefaultConfig()

        loadItems()
        loadEvents()

        logger.info("Ready!")
    }

    private fun loadItems() {
        for (item in config.getMapList("items")) {
            val name: String = item["name"] as? String ?: "*"
            val material: String = item["material"] as? String ?: "*"
            val lore: List<String>? = item["lore"] as? List<String>

            if (lore == null) {
                logger.warning("The configuration { name: $name, material: $material } did not have lore. Skipping...")
                continue
            }

            if (material != "*" && Material.matchMaterial(material) == null) {
                logger.warning("The configuration { name: $name, material: $material } has an invalid material. Skipping...")
                continue
            }

            val nameMatcher: String? = if (name == "*") null else ChatColor.translateAlternateColorCodes('&', name)
            val materialMatcher: Material? = if (material == "*") null else Material.matchMaterial(material)!!

            items.add(TrackedItem(nameMatcher, materialMatcher, lore.map { ChatColor.translateAlternateColorCodes('&', it) }))
        }
    }

    private fun loadEvents() {
        server.pluginManager.registerEvents(PickupListener(this), this)
    }
}