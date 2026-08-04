package io.github.willowbranch.willowtweaks.config;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class Hotkeys
{
    public static final ConfigHotkey OPEN_CONFIG =
            new ConfigHotkey(
                    "willowtweaks.hotkey.open_config",
                    "W,C"
            );
}