package org.mangorage.registrationutils.core;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.generators.BlockStateModelGenerator;

@Mod.EventBusSubscriber(modid = RegistrationUtils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {
    @SubscribeEvent
    public static void onData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        var efh = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new BlockStateModelGenerator(output, efh));
    }
}
