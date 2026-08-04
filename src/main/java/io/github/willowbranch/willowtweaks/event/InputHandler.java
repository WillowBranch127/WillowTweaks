package io.github.willowbranch.willowtweaks.event;

import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import fi.dy.masa.malilib.hotkeys.IKeyboardInputHandler;

import io.github.willowbranch.willowtweaks.config.FeatureToggle;
import io.github.willowbranch.willowtweaks.config.Hotkeys;
import io.github.willowbranch.willowtweaks.gui.GuiConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;


public class InputHandler implements IKeybindProvider, IKeyboardInputHandler
{
    private static final InputHandler INSTANCE = new InputHandler();


    public static InputHandler getInstance()
    {
        return INSTANCE;
    }


    @Override
    public void addKeysToMap(IKeybindManager manager)
    {
        manager.addKeybindToMap(
                Hotkeys.OPEN_CONFIG.getKeybind()
        );

        for (FeatureToggle toggle : FeatureToggle.VALUES)
        {
            manager.addKeybindToMap(
                    toggle.getKeybind()
            );
        }
    }


    @Override
    public void addHotkeys(IKeybindManager manager)
    {
        manager.addHotkeysForCategory(
                "willowtweaks",
                "willowtweaks.hotkeys.category",
                java.util.List.of(
                        Hotkeys.OPEN_CONFIG
                )
        );


    }


    @Override
    public boolean onKeyInput(KeyEvent event, boolean state)
    {
        if (state &&
                Hotkeys.OPEN_CONFIG.getKeybind().isPressed())
        {
            Minecraft.getInstance().gui.setScreen(
                    new GuiConfigs()
            );



            return true;
        }

        return false;
    }
}