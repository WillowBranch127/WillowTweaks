package io.github.willowbranch.willowtweaks.feature;


import io.github.willowbranch.willowtweaks.config.FeatureToggle;
import io.github.willowbranch.willowtweaks.config.FlySpeedConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;


public class AutoFlyBoost
{

    public static void tick()
    {
        Minecraft mc = Minecraft.getInstance();


        if (mc.player == null)
        {
            return;
        }


        if (!FeatureToggle.AUTO_FLY_BOOST.getBooleanValue())
        {
            return;
        }


        LocalPlayer player = mc.player;


        if (!player.isFallFlying())
        {
            return;
        }


        double speed =
                player.getDeltaMovement().length();


        if(speed <
                FlySpeedConfig.SPEED_THRESHOLD.getIntegerValue()/20.0)
        {
            AutoFly.tryStart(player);
        }
    }
}