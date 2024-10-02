package org.mangorage.registrationutils;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mangorage.registrationutils.core.ColorProvider;

@Mod.EventBusSubscriber(modid = RegistrationUtils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    public static void registerBlock(RegisterColorHandlersEvent.Block event) {
        RegistrationUtils.WOOD_PLANKS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.getKey().getColor()),
                    e.getValue().get()
            );
        });
        RegistrationUtils.WOOD_SLAB.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.getKey().getColor()),
                    e.getValue().get()
            );
        });
    }

    @SubscribeEvent
    public static void registerItem(RegisterColorHandlersEvent.Item event) {
        RegistrationUtils.WOOD_PLANKS.getLeftMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.getKey().getColor()),
                    e.getValue().get()
            );
        });
        RegistrationUtils.WOOD_SLAB.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.getKey().getColor()),
                    e.getValue().get()
            );
        });
    }
}
