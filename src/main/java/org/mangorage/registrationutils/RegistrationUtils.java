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
import org.mangorage.registrationutils.utils.PairEnumRegistryObjectMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RegistrationUtils.MODID)
public class RegistrationUtils {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "registrationutils";
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final PairEnumRegistryObjectMap<Color, Block, Item, Block, BlockItem> WOOD_PLANKS = PairEnumRegistryObjectMap.create(
            Color.class,
            "wood_planks",
            BLOCKS,
            ITEMS,
            e -> new Block(BlockBehaviour.Properties.of()),
            (e, m) -> new BlockItem(m.get(e).get(), new Item.Properties())
    );

    public static final PairEnumRegistryObjectMap<Color, Block, Item, SlabBlock, BlockItem> WOOD_SLAB = PairEnumRegistryObjectMap.create(
            Color.class,
            "wood_slab",
            BLOCKS,
            ITEMS,
            e -> new SlabBlock(BlockBehaviour.Properties.of()),
            (e, m) -> new BlockItem(m.get(e).get(), new Item.Properties())
    );

    public RegistrationUtils() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
