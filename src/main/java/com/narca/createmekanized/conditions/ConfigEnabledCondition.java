package com.narca.createmekanized.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.narca.createmekanized.Config;

import net.neoforged.neoforge.common.conditions.ICondition;

// Recipe condition that only loads a recipe when a boolean in our config is true.
// Used in recipe JSON as:
// "neoforge:conditions": [{ "type": "create_mekanized:config_enabled", "option": "enableShardTier" }]
public record ConfigEnabledCondition(String option) implements ICondition {
    public static final MapCodec<ConfigEnabledCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("option").forGetter(ConfigEnabledCondition::option)
    ).apply(instance, ConfigEnabledCondition::new));

    @Override
    public boolean test(IContext context) {
        return Config.isEnabled(option);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
