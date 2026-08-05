package io.github.willowbranch.willowtweaks.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.util.data.json.JsonUtils;
import fi.dy.masa.malilib.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class Configs implements IConfigHandler
{
    public static class Generic
    {
        public static final ImmutableList<IConfigBase> OPTIONS =
                ImmutableList.of(
                        Hotkeys.OPEN_CONFIG
                );
    }


    public static class Features
    {


        public static final ImmutableList<IConfigBase> OPTIONS =
                ImmutableList.of(
                        FeatureToggle.SHOW_PLAYER_LIST,
                        FeatureToggle.AUTO_FLY,
                        FeatureToggle.AUTO_FLY_BOOST,
                        FlySpeedConfig.SPEED_THRESHOLD
                );
    }

    public static final ImmutableList<IConfigBase> ALL_OPTIONS =
            ImmutableList.<IConfigBase>builder()
                    .addAll(Generic.OPTIONS)
                    .addAll(Features.OPTIONS)
                    .build();

    private static final Path FILE =
            FileUtils.getConfigDirectory()
                    .resolve("willowtweaks.json");


    @Override
    public void load()
    {
        if (Files.exists(FILE))
        {
            JsonElement element = JsonUtils.parseJsonFile(FILE);

            if (element != null && element.isJsonObject())
            {
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(
                        root,
                        "Generic",
                        Generic.OPTIONS
                );

                ConfigUtils.readConfigBase(
                        root,
                        "Features",
                        Features.OPTIONS
                );


                ConfigUtils.readHotkeyToggleOptions(
                        root,
                        "FeatureHotkeys",
                        "FeatureToggles",
                        FeatureToggle.VALUES
                );
            }
        }
    }


    @Override
    public void save()
    {
        JsonObject root = new JsonObject();

        ConfigUtils.writeConfigBase(
                root,
                "Generic",
                Generic.OPTIONS
        );

        ConfigUtils.writeConfigBase(
                root,
                "Features",
                Features.OPTIONS
        );

        ConfigUtils.writeHotkeyToggleOptions(
                root,
                "FeatureHotkeys",
                "FeatureToggles",
                FeatureToggle.VALUES
        );

        JsonUtils.writeJsonToFile(
                root,
                FILE
        );
    }
}