package com.narca.createmekanized.registry;

import com.narca.createmekanized.CreateMekanized;

import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// Add Create_Mekanized chemicals using Mekanism Chemical type
public class ModChemicals {
    public static final DeferredRegister<Chemical> CHEMICALS = DeferredRegister.create(MekanismAPI.CHEMICAL_REGISTRY_NAME, CreateMekanized.MODID);

    // Zinc Based Chemicals
    public static final DeferredHolder<Chemical, Chemical> DIRTY_ZINC = CHEMICALS.register("dirty_zinc", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(ModItems.ZINC_COLOR))); // Slurry type
    public static final DeferredHolder<Chemical, Chemical> CLEAN_ZINC = CHEMICALS.register("clean_zinc", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(ModItems.ZINC_COLOR))); // Slurry type
    public static final DeferredHolder<Chemical, Chemical> ZINC = CHEMICALS.register("zinc", () -> new Chemical(ChemicalBuilder.infuseType().tint(ModItems.ZINC_COLOR))); // Infuse type

    public static void register(IEventBus modEventBus) {
        CHEMICALS.register(modEventBus);
    }
}
