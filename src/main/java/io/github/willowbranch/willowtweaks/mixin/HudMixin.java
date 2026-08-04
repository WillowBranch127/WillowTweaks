package io.github.willowbranch.willowtweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.willowbranch.willowtweaks.config.FeatureToggle;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Hud.class)
public class HudMixin
{
    @ModifyExpressionValue(
            method = "extractTabList",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/KeyMapping;isDown()Z"
            )
    )
    private boolean willowtweaks$forceTabKey(boolean original)
    {
        return original || FeatureToggle.SHOW_PLAYER_LIST.getBooleanValue();
    }
}