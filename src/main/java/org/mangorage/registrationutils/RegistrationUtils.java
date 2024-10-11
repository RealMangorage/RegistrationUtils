package org.mangorage.registrationutils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mangorage.registrationutils.core.Registration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RegistrationUtils.MODID)
public class RegistrationUtils {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "registrationutils";


    public RegistrationUtils(FMLJavaModLoadingContext context) {
        Registration.init(context.getModEventBus());
    }
}
