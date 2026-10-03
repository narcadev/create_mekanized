package com.narca.createmekanized.compat;

import com.narca.createmekanized.Config;
import com.narca.createmekanized.CreateMekanized;
import com.simibubi.create.api.boiler.BoilerHeater;
import com.simibubi.create.api.registry.SimpleRegistry;

import mekanism.common.block.attribute.Attribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

// Lets Mekanism heaters act as heat sources for Create's Basin and Boiler.
// Which blocks count is decided by the block tag create_mekanized:mekanism_heaters.
public class CreateHeating {
    public static final TagKey<Block> MEKANISM_HEATERS = TagKey.create(Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(CreateMekanized.MODID, "mekanism_heaters"));

    // Heat a boiler gets from one active heater: 1 is the same as a fed Blaze Burner
    private static final int BOILER_HEAT = 1;

    // True when the block is in our heater tag and Mekanism reports it as running
    public static boolean isActiveMekanismHeater(BlockState state) {
        return state.is(MEKANISM_HEATERS) && Attribute.isActive(state);
    }

    // Called once during common setup, after all blocks are registered
    public static void registerBoilerHeaters() {
        BoilerHeater.REGISTRY.registerProvider(SimpleRegistry.Provider.forBlockTag(MEKANISM_HEATERS,
                (level, pos, state) -> Config.ENABLE_BOILER_HEATING.get() && Attribute.isActive(state)
                        ? BOILER_HEAT
                        : BoilerHeater.NO_HEAT));
    }
}
