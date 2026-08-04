package io.github.willowbranch.willowtweaks.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.dy.masa.malilib.config.options.BooleanHotkeyGuiWrapper;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase.ConfigOptionWrapper;

import io.github.willowbranch.willowtweaks.Reference;
import io.github.willowbranch.willowtweaks.config.Configs;
import io.github.willowbranch.willowtweaks.config.FeatureToggle;


public class GuiConfigs extends GuiConfigsBase
{
    public GuiConfigs()
    {
        super(
                10,
                50,
                Reference.MOD_ID,
                null,
                "willowtweaks.gui.title.configs",
                Reference.MOD_VERSION
        );
    }


    @Override
    public List<ConfigOptionWrapper> getConfigs()
    {
        List<ConfigOptionWrapper> list = new ArrayList<>();

        list.addAll(
                ConfigOptionWrapper.createFor(
                        Configs.Generic.OPTIONS
                )
        );

        list.addAll(
                ConfigOptionWrapper.createFor(
                        Arrays.stream(FeatureToggle.values())
                                .map(toggle ->
                                        new BooleanHotkeyGuiWrapper(
                                                toggle.getConfig().getPrettyName(),
                                                toggle.getConfig(),
                                                toggle.getKeybind()
                                        )
                                )
                                .toList()
                )
        );

        return list;
    }
}