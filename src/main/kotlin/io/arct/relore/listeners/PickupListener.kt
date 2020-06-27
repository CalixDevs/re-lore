package io.arct.relore.listeners

import io.arct.relore.ReLore
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPickupItemEvent

class PickupListener(val plugin: ReLore) : Listener {
    @EventHandler
    fun pickup(event: PlayerPickupItemEvent) {
        for (tracked in plugin.items) {
            if (tracked.matches(event.item.itemStack)) {
                event.item.itemStack = tracked.addLore(event.item.itemStack)
                break
            }
        }
    }
}