package io.github.willowbranch.willowtweaks.event;

import io.github.willowbranch.willowtweaks.feature.AutoFly;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTickHandler
{
    public static void register()
    {
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> AutoFly.tick()
        );
    }
}