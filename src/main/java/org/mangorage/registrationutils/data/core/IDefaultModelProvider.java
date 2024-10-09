package org.mangorage.registrationutils.data.core;

import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;

// Default Provider, doesnt need it for blocks.
// like cube_all.json
public interface IDefaultModelProvider<T> {
    void generate(BlockModelProvider provider);
    T[] applyParents(BlockStateProvider blockStateProvider, String name, TextureMap textureMap);
    void create(BlockStateProvider blockStateProvider, IBlockWithName blockWithName, TextureMap textureMap, boolean includeBlockItems);
}
