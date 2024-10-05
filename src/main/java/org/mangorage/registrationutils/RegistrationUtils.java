package org.mangorage.registrationutils;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mangorage.registrationutils.core.Registration;
import org.mangorage.registrationutils.utils.PairEnumRegistryObjectMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RegistrationUtils.MODID)
public class RegistrationUtils {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "registrationutils";


    public RegistrationUtils(FMLJavaModLoadingContext context) {
        Registration.init(context.getModEventBus());
    }
}
