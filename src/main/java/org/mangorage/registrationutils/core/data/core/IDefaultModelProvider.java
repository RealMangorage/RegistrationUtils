package org.mangorage.registrationutils.core.data.core;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;

import java.util.function.Function;

// Default Provider, doesnt need it for blocks.
// like cube_all.json
public interface IDefaultModelProvider<T> {
    void generate(BlockModelProvider provider);
    T[] applyParents(BlockStateProvider blockStateProvider, String name, TextureMap textureMap);
    void create(BlockStateProvider blockStateProvider, Block block, String name, TextureMap textureMap, boolean includeBlockItems);
}
