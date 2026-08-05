package io.github.willowbranch.willowtweaks.config;

import fi.dy.masa.malilib.config.options.ConfigInteger;

public class FlySpeedConfig
{
    public static final ConfigInteger SPEED_THRESHOLD =
            new ConfigInteger(
                    "flySpeedThreshold",
                    10,
                    "Auto boost speed threshold"
            );
}