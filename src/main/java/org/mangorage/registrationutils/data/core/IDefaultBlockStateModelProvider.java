package org.mangorage.registrationutils.data.core;

import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;

// Default Provider, doesnt need it for blocks.
// like cube_all.json
public interface IDefaultBlockStateModelProvider {
    void generate(BlockStateProvider provider);
    BlockModelBuilder[] applyParents(BlockStateProvider provider, String name, TextureMap textureMap);
    void create(BlockStateProvider provider, IBlockWithName blockWithName, TextureMap textureMap, boolean includeBlockItems);
}
