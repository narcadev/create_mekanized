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

    // Configurable Create recipes for Mekanism
    static {
        BUILDER.push("create_recipes_in_mekanism");
    }
    public static final ModConfigSpec.BooleanValue ENABLE_COMBINER_ANDESITE_ALLOY = BUILDER
            .comment(" Combiner: Andesite + Iron or Zinc Nugget into Andesite Alloy")
            .worldRestart()
            .define("enableCombinerAndesiteAlloy", true);
    public static final ModConfigSpec.BooleanValue ENABLE_INFUSER_ANDESITE_ALLOY = BUILDER
            .comment(" Metallurgic Infuser: Andesite + Zinc infuse into Andesite Alloy")
            .worldRestart()
            .define("enableInfuserAndesiteAlloy", true);

    static {
        BUILDER.pop();
    }

    // Configurable Mekanism recipes for Create
    static {
        BUILDER.push("mekanism_recipes_in_create");
    }
    public static final ModConfigSpec.BooleanValue ENABLE_MIXER_BRONZE = BUILDER
            .comment(" Create Mixer (heated): Copper Ingots + Tin Ingot into Bronze Ingots")
            .worldRestart()
            .define("enableMixerBronze", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MIXER_STEEL = BUILDER
            .comment(" Create Mixer (superheated): Iron Ingot + Coal into Steel Ingot")
            .worldRestart()
            .define("enableMixerSteel", true);

    static {
        BUILDER.pop();
    }

    // Should Create mirror Mekanism's ore processing
    static {
        BUILDER.push("create_mirrors_mekanism_processing");
    }
    public static final ModConfigSpec.BooleanValue ENABLE_CREATE_CHAIN_STEPS = BUILDER
            .comment(" Millstone/Crushing Wheels turn Clumps into Dirty Dust, and Fan Washing turns Dirty Dust into Dust")
            .worldRestart()
            .define("enableCreateChainSteps", true);

    static {
        BUILDER.pop();
    }

    // Let Mekanism heaters be heat sources for Create.
    static {
        BUILDER.push("heating");
    }
    public static final ModConfigSpec.BooleanValue ENABLE_BASIN_HEATING = BUILDER
            .comment(" NOTE: Resistive and Fuelwood heaters are not a superheated source.")
            .comment(" Active Mekanism heaters (Resistive, Fuelwood) under a Basin count as a heated source, mirroring a fueled Blaze Burner.")
            .define("enableBasinHeating", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BOILER_HEATING = BUILDER
            .comment(" Active Mekanism heaters (Resistive, Fuelwood) under a boiler give 1 heat each, mirroring a fueled Blaze Burner.")
            .define("enableBoilerHeating", true);

    static {
        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    // Option names that recipes can check against
    private static final Map<String, ModConfigSpec.BooleanValue> RECIPE_TOGGLES = Map.ofEntries(
            Map.entry("enableDustTier", ENABLE_DUST_TIER),
            Map.entry("enableClumpTier", ENABLE_CLUMP_TIER),
            Map.entry("enableShardTier", ENABLE_SHARD_TIER),
            Map.entry("enableSlurryTier", ENABLE_SLURRY_TIER),
            Map.entry("enableCombinerAndesiteAlloy", ENABLE_COMBINER_ANDESITE_ALLOY),
            Map.entry("enableInfuserAndesiteAlloy", ENABLE_INFUSER_ANDESITE_ALLOY),
            Map.entry("enableMixerBronze", ENABLE_MIXER_BRONZE),
            Map.entry("enableMixerSteel", ENABLE_MIXER_STEEL),
            Map.entry("enableCreateChainSteps", ENABLE_CREATE_CHAIN_STEPS));

    public static boolean isEnabled(String option) {
        ModConfigSpec.BooleanValue value = RECIPE_TOGGLES.get(option);
        if (value == null) {
            CreateMekanized.LOGGER.warn("Unknown config option '{}' in a recipe condition, treating it as disabled", option);
            return false;
        }
        return value.getAsBoolean();
    }
}
