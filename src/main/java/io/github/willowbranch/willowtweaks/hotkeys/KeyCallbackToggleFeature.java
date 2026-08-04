package io.github.willowbranch.willowtweaks.hotkeys;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.util.InfoUtils;

import io.github.willowbranch.willowtweaks.config.FeatureToggle;

public class KeyCallbackToggleFeature implements IHotkeyCallback
{
    private final FeatureToggle feature;

    public KeyCallbackToggleFeature(FeatureToggle feature)
    {
        this.feature = feature;
    }


    @Override
    public boolean onKeyAction(KeyAction action, IKeybind key)
    {
        if (action == KeyAction.PRESS)
        {
            feature.toggle();

            InfoUtils.printBooleanConfigToggleMessage(
                    feature.getPrettyName(),
                    feature.getBooleanValue()
            );
        }

        return true;
    }
}