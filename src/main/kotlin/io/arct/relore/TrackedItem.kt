package io.arct.relore

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class TrackedItem(val name: String?, val material: Material?, val lore: List<String>) {
    fun matches(item: ItemStack): Boolean {
        if (name != null && item.itemMeta?.displayName != name)
            return false

        if (material != null && item.type != material)
            return false

        return true
    }

    fun addLore(item: ItemStack): ItemStack {
        val meta = item.itemMeta

        meta.lore = lore

        item.itemMeta = meta
        return item
    }
}