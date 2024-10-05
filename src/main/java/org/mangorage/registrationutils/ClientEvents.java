package org.mangorage.registrationutils;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mangorage.registrationutils.core.ColorProvider;
import org.mangorage.registrationutils.core.Registration;

@Mod.EventBusSubscriber(modid = RegistrationUtils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    public static void registerBlock(RegisterColorHandlersEvent.Block event) {
        Registration.WOOD_PLANKS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
        Registration.WOOD_SLABS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
        Registration.WOOD_STAIRS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
    }

    @SubscribeEvent
    public static void registerItem(RegisterColorHandlersEvent.Item event) {
        Registration.WOOD_PLANKS.getLeftMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
        Registration.WOOD_SLABS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
        Registration.WOOD_STAIRS.getRightMap().entrySet().forEach(e -> {
            event.register(
                    ColorProvider.of(e.key().getColor()),
                    e.value().get()
            );
        });
    }
}
