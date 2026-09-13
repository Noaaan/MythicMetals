package com.mythicmetals.api.v2;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Called whenever a Material is created and registered.
 */
public interface MaterialRegistrationCallback {
    Event<MaterialRegistrationCallback> EVENT = EventFactory.createArrayBacked(MaterialRegistrationCallback.class,
        callbacks -> {
            return material -> {
                for (var listener : callbacks) {
                    listener.onRegister(material);
                }
            };
        });

    void onRegister(Material material);
}
