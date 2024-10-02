package org.mangorage.registrationutils.core;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mangorage.registrationutils.RegistrationUtils;

@Mod.EventBusSubscriber(modid = RegistrationUtils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {
    @SubscribeEvent
    public static void onData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        var efh = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new BlockDataGen(output, efh));
    }
}
