package com.narca.createmekanized;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.narca.createmekanized.registry.ModChemicals;
import com.narca.createmekanized.registry.ModConditions;
import com.narca.createmekanized.registry.ModCreativeTabs;
import com.narca.createmekanized.registry.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CreateMekanized.MODID)
public class CreateMekanized {
    public static final String MODID = "create_mekanized";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateMekanized(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModChemicals.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModConditions.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
