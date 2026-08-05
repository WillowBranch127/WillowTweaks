package io.github.willowbranch.willowtweaks.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.ConfigType;
import fi.dy.masa.malilib.config.IHotkeyTogglable;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.hotkeys.*;
import io.github.willowbranch.willowtweaks.hotkeys.KeyCallbackToggleFeature;


public enum FeatureToggle implements IHotkeyTogglable
{
    SHOW_PLAYER_LIST(
            "alwaysShowPlayerList",
            false,
            "Always show player list",
            ""
    ),

    AUTO_FLY(
            "autoFly",
            false,
            "Automatically use fireworks",
            ""
    ),

    AUTO_FLY_BOOST(
            "autoFlyBoost",
            false,
            "Automatically boost elytra speed",
            ""
    );


    private final ConfigBoolean config;
    private final IKeybind keybind;


    FeatureToggle(String name, boolean value, String comment, String hotkey)
    {
        this.config = new ConfigBoolean(
                name,
                value,
                comment
        );

        this.keybind = KeybindMulti.fromStorageString(
                hotkey,
                KeybindSettings.DEFAULT
        );
/*
        this.keybind.setCallback(new IHotkeyCallback()
        {
            @Override
            public boolean onKeyAction(KeyAction action, IKeybind key)
            {
                if (action == KeyAction.PRESS)
                {
                    toggle();
                }

                return true;
            }
        });


 */

/*
        this.keybind.setCallback(
                new KeyCallbackToggleBooleanConfigWithMessage(this.config)
        );

 */

        this.keybind.setCallback(
                new KeyCallbackToggleFeature(this)
        );
    }


    public ConfigBoolean getConfig()
    {
        return config;
    }


    @Override
    public IKeybind getKeybind()
    {
        System.out.println("get keybind: " + keybind.getStringValue());
        return keybind;
    }


    @Override
    public boolean getBooleanValue()
    {
        return config.getBooleanValue();
    }


    @Override
    public boolean getDefaultBooleanValue()
    {
        return false;
    }


    @Override
    public void setBooleanValue(boolean value)
    {
        System.out.println("toggle " + value);
        config.setBooleanValue(value);
    }


    public void toggle()
    {
        setBooleanValue(!getBooleanValue());
    }


    public static final ImmutableList<FeatureToggle> VALUES =
            ImmutableList.copyOf(values());


    @Override
    public ConfigType getType()
    {
        return config.getType();
    }


    @Override
    public String getName()
    {
        return config.getName();
    }


    @Override
    public String getComment()
    {
        return config.getComment();
    }


    @Override
    public String getTranslatedName()
    {
        return config.getTranslatedName();
    }


    @Override
    public void setPrettyName(String prettyName)
    {
        config.setPrettyName(prettyName);
    }


    @Override
    public void setTranslatedName(String translatedName)
    {
        config.setTranslatedName(translatedName);
    }


    @Override
    public void setComment(String comment)
    {
        config.setComment(comment);
    }


    @Override
    public boolean isDirty()
    {
        return config.isDirty();
    }


    @Override
    public void markDirty()
    {
        config.markDirty();
    }


    @Override
    public void markClean()
    {
        config.markClean();
    }


    @Override
    public void checkIfClean()
    {
        config.checkIfClean();
    }


    @Override
    public void setValueFromJsonElement(JsonElement element)
    {
        config.setValueFromJsonElement(element);
    }


    @Override
    public JsonElement getAsJsonElement()
    {
        return config.getAsJsonElement();
    }
}