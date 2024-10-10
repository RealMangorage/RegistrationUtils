package org.mangorage.registrationutils.core;

import com.google.common.collect.ImmutableList;
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
import org.mangorage.registrationutils.utils.EnumPairRegistryMap;
import org.mangorage.registrationutils.utils.IEnumPairHolder;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.mangorage.registrationutils.RegistrationUtils.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    private static final List<IEnumPairHolder<Color, RegistryObject<Block>>> ALL_BLOCKS = new ArrayList<>();
    private static final List<IEnumPairHolder<Color, RegistryObject<Item>>> ALL_ITEMS = new ArrayList<>();

    private static final BiConsumer<IEnumPairHolder<Color, RegistryObject<Block>>,  IEnumPairHolder<Color, RegistryObject<Item>>> LIST_HANDLER = (a, b) -> {
        ALL_BLOCKS.add(a);
        ALL_ITEMS.add(b);
    };

    public static final EnumPairRegistryMap<Color, Block, Item, Block, BlockItem> WOOD_PLANKS = EnumPairRegistryMap.create(
            Color.class,
            "wood_plank",
            BLOCKS,
            ITEMS,
            LIST_HANDLER,
            e -> new Block(BlockBehaviour.Properties.of()),
            (e, p) -> new BlockItem(p, new Item.Properties())
    );

    public static final EnumPairRegistryMap<Color, Block, Item, SlabBlock, BlockItem> WOOD_SLABS = EnumPairRegistryMap.create(
            Color.class,
            "wood_slab",
            BLOCKS,
            ITEMS,
            LIST_HANDLER,
            e -> new SlabBlock(BlockBehaviour.Properties.of()),
            (e, p) -> new BlockItem(p, new Item.Properties())
    );

    public static final EnumPairRegistryMap<Color, Block, Item, StairBlock, BlockItem> WOOD_STAIRS = EnumPairRegistryMap.create(
            Color.class,
            "wood_stairs",
            BLOCKS,
            ITEMS,
            LIST_HANDLER,
            e -> new StairBlock(WOOD_PLANKS.getLeftHolder(e).getRight().get().defaultBlockState(), BlockBehaviour.Properties.of()),
            (e, p) -> new BlockItem(p, new Item.Properties())
    );

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("regi", () ->
        CreativeModeTab.builder()
                .icon(Items.OAK_PLANKS::getDefaultInstance)
                .title(Component.literal("Registration Utils Tab"))
                .displayItems((itemDisplayParameters, output) -> {
                    ALL_ITEMS.forEach(h -> {
                        output.accept(h.getRight().get());
                    });
                })
                .build()
    );

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
    }

    public static List<IEnumPairHolder<Color, RegistryObject<Block>>> getAllBlocks() {
        return ImmutableList.copyOf(ALL_BLOCKS);
    }

    public static List<IEnumPairHolder<Color, RegistryObject<Item>>> getAllItems() {
        return ImmutableList.copyOf(ALL_ITEMS);
    }
}
