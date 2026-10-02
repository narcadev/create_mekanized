package com.narca.createmekanized;

import java.util.Map;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Let Zinc processing tiers be configurable
    static {
        BUILDER.push("zinc_processing");
    }
    public static final ModConfigSpec.BooleanValue ENABLE_DUST_TIER = BUILDER
            .comment(" Enable 2x zinc processing: Ore into Zinc Dust in the Enrichment Chamber")
            .worldRestart()
            .define("enableDustTier", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CLUMP_TIER = BUILDER
            .comment(" Enable 3x zinc processing: Ore into Zinc Clumps in the Purification Chamber")
            .worldRestart()
            .define("enableClumpTier", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SHARD_TIER = BUILDER
            .comment(" Enable 4x zinc processing: Ore into Zinc Shards in the Chemical Injection Chamber")
            .worldRestart()
            .define("enableShardTier", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SLURRY_TIER = BUILDER
            .comment(" Enable 5x zinc processing: Ore into Dirty Zinc Slurry in the Chemical Dissolution Chamber")
            .worldRestart()
            .define("enableSlurryTier", true);

    static {
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();

    // Option names that recipes can check against
    private static final Map<String, ModConfigSpec.BooleanValue> RECIPE_TOGGLES = Map.of(
            "enableDustTier", ENABLE_DUST_TIER,
            "enableClumpTier", ENABLE_CLUMP_TIER,
            "enableShardTier", ENABLE_SHARD_TIER,
            "enableSlurryTier", ENABLE_SLURRY_TIER);

    public static boolean isEnabled(String option) {
        ModConfigSpec.BooleanValue value = RECIPE_TOGGLES.get(option);
        if (value == null) {
            CreateMekanized.LOGGER.warn("Unknown config option '{}' in a recipe condition, treating it as disabled", option);
            return false;
        }
        return value.getAsBoolean();
    }
}
