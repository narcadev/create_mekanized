package com.narca.createmekanized.registry;

import com.mojang.serialization.MapCodec;
import com.narca.createmekanized.CreateMekanized;
import com.narca.createmekanized.conditions.ConfigEnabledCondition;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

// Recipe condition types, used in "neoforge:conditions"
public class ModConditions {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, CreateMekanized.MODID);

    // create_mekanized:config_enabled
    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<ConfigEnabledCondition>> CONFIG_ENABLED = CONDITION_CODECS.register("config_enabled", () -> ConfigEnabledCondition.CODEC);

    public static void register(IEventBus modEventBus) {
        CONDITION_CODECS.register(modEventBus);
    }
}
