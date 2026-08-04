package io.github.willowbranch.willowtweaks.config;

import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import fi.dy.masa.malilib.hotkeys.IHotkey;

import java.util.List;

public class KeybindProvider implements IKeybindProvider
{
    @Override
    public void addKeysToMap(IKeybindManager manager)
    {
        for (FeatureToggle toggle : FeatureToggle.VALUES)
        {
            manager.addKeybindToMap(toggle.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager)
    {
        manager.addHotkeysForCategory(
                "WillowTweaks",
                "Feature Toggles",
                FeatureToggle.VALUES.stream()
                        .map(toggle -> (IHotkey) toggle.getKeybind())
                        .toList()
        );
    }
}