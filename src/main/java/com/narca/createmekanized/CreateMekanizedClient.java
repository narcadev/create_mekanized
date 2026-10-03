package com.narca.createmekanized;

import com.narca.createmekanized.compat.CreateHeating;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@Mod(value = CreateMekanized.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CreateMekanized.MODID, value = Dist.CLIENT)
public class CreateMekanizedClient {
    public CreateMekanizedClient(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    // Adds a tooltip to Mekanism heaters
    @SubscribeEvent
    static void onItemTooltip(ItemTooltipEvent event) {
        if (!ClientConfig.SPEC.isLoaded() || !ClientConfig.SHOW_HEATER_TOOLTIP.get())
            return;
        if (!(event.getItemStack().getItem() instanceof BlockItem blockItem))
            return;
        if (!blockItem.getBlock().defaultBlockState().is(CreateHeating.MEKANISM_HEATERS))
            return;
        if (!Config.SPEC.isLoaded() || !(Config.ENABLE_BASIN_HEATING.get() || Config.ENABLE_BOILER_HEATING.get()))
            return;

        event.getToolTip().add(Component.translatable("tooltip.create_mekanized.mekanism_heater").withStyle(ChatFormatting.GRAY));
    }
}
