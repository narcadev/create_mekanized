package com.narca.createmekanized;

import net.neoforged.neoforge.common.ModConfigSpec;

// Client-only settings
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.push("tooltips");
    }

    // Configure display of Mekanism heater tooltips
    public static final ModConfigSpec.BooleanValue SHOW_HEATER_TOOLTIP = BUILDER
            .comment(" Show \"Compatible with Create\" on Mekanism heaters, heating functionality remains")
            .define("showHeaterTooltip", true);

    static {
        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();
}
