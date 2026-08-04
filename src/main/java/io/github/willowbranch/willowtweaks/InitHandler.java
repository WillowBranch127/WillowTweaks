package io.github.willowbranch.willowtweaks;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;

import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;
import io.github.willowbranch.willowtweaks.config.Configs;
import io.github.willowbranch.willowtweaks.config.KeybindProvider;
import io.github.willowbranch.willowtweaks.event.InputHandler;
import io.github.willowbranch.willowtweaks.gui.GuiConfigs;

public class InitHandler implements IInitializationHandler
{
    @Override
    public void registerModHandlers()
    {
        Registry.CONFIG_SCREEN.registerConfigScreenFactory(
                new ModInfo(
                        Reference.MOD_ID,
                        Reference.MOD_NAME,
                        GuiConfigs::new
                )
        );

        ConfigManager.getInstance()
                .registerConfigHandler(
                        Reference.MOD_ID,
                        new Configs()
                );


        InputEventHandler.getKeybindManager()
                .registerKeybindProvider(InputHandler.getInstance());


        InputEventHandler.getKeybindManager()
                .updateUsedKeys();

        InputEventHandler.getInputManager()
                .registerKeyboardInputHandler(InputHandler.getInstance());



    }
}