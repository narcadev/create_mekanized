package com.narca.createmekanized.registry;

import com.narca.createmekanized.CreateMekanized;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


// Register all Create_Mekanized items
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateMekanized.MODID);

    // Zinc-related items
    public static final int ZINC_COLOR = 0xA3BDAF; // RGB value for Zinc chemicals
    public static final DeferredItem<Item> DUST_ZINC = ITEMS.registerSimpleItem("dust_zinc");
    public static final DeferredItem<Item> DIRTY_DUST_ZINC = ITEMS.registerSimpleItem("dirty_dust_zinc");
    public static final DeferredItem<Item> CLUMP_ZINC = ITEMS.registerSimpleItem("clump_zinc");
    public static final DeferredItem<Item> SHARD_ZINC = ITEMS.registerSimpleItem("shard_zinc");
    public static final DeferredItem<Item> CRYSTAL_ZINC = ITEMS.registerSimpleItem("crystal_zinc");

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
