package com.narca.createmekanized.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.narca.createmekanized.Config;
import com.narca.createmekanized.compat.CreateHeating;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;

import net.minecraft.world.level.block.state.BlockState;

// Create Basins has no API to recognise external heat sources, this mixin tells Create that Mekanism Heaters that they have a "heated" block state
@Mixin(BasinBlockEntity.class)
public abstract class BasinBlockEntityMixin {

    // This handler must be static
    @Inject(method = "getHeatLevelOf", at = @At("HEAD"), cancellable = true)
    private static void createMekanized$mekanismHeaters(BlockState state, CallbackInfoReturnable<HeatLevel> cir) {
        // In-case the config is not loaded, use Create as the fall-back
        if (!Config.SPEC.isLoaded() || !Config.ENABLE_BASIN_HEATING.get())
            return;
        if (CreateHeating.isActiveMekanismHeater(state))
            // KINDLED is recognised for "heated" recipes
            cir.setReturnValue(HeatLevel.KINDLED);
    }
}