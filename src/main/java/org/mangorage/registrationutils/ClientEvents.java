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
        Registration.LIST.getRightList().forEach(c -> {
            event.register(ColorProvider.of(c.getLeft().getColor()), c.getRight().get());
        });
    }

    @SubscribeEvent
    public static void registerItem(RegisterColorHandlersEvent.Item event) {
        Registration.LIST.getLeftList().forEach(c -> {
            event.register(ColorProvider.of(c.getLeft().getColor()), c.getRight().get());
        });

    }
}
