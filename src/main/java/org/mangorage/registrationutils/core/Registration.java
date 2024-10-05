package org.mangorage.registrationutils.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mangorage.registrationutils.Color;
import org.mangorage.registrationutils.utils.PairEnumRegistryObjectMap;

import static org.mangorage.registrationutils.RegistrationUtils.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final PairEnumRegistryObjectMap<Color, Block, Item, Block, BlockItem> WOOD_PLANKS = PairEnumRegistryObjectMap.create(
            Color.class,
            "wood_planks",
            BLOCKS,
            ITEMS,
            e -> new Block(BlockBehaviour.Properties.of()),
            (e, m) -> new BlockItem(m.get(e).get(), new Item.Properties())
    );

    public static final PairEnumRegistryObjectMap<Color, Block, Item, SlabBlock, BlockItem> WOOD_SLABS = PairEnumRegistryObjectMap.create(
            Color.class,
            "wood_slab",
            BLOCKS,
            ITEMS,
            e -> new SlabBlock(BlockBehaviour.Properties.of()),
            (e, m) -> new BlockItem(m.get(e).get(), new Item.Properties())
    );

    public static final PairEnumRegistryObjectMap<Color, Block, Item, StairBlock, BlockItem> WOOD_STAIRS = PairEnumRegistryObjectMap.create(
            Color.class,
            "wood_stairs",
            BLOCKS,
            ITEMS,
            e -> new StairBlock(WOOD_SLABS.getRight(e).get().defaultBlockState(), BlockBehaviour.Properties.of()),
            (e, m) -> new BlockItem(m.get(e).get(), new Item.Properties())
    );

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("regi", () ->
        CreativeModeTab.builder()
                .icon(Items.OAK_PLANKS::getDefaultInstance)
                .title(Component.literal("Registration Utils Tab"))
                .displayItems((itemDisplayParameters, output) -> {
                    WOOD_PLANKS.getLeftMap().getAll().forEach(i -> output.accept(i.get()));
                    WOOD_SLABS.getLeftMap().getAll().forEach(i -> output.accept(i.get()));
                    WOOD_STAIRS.getLeftMap().getAll().forEach(i -> output.accept(i.get()));
                })
                .build()
    );

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
    }
}
