package org.mangorage.registrationutils.data.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

public interface IBlockWithName {
    static IBlockWithName of(Block block) {
        var key = ForgeRegistries.BLOCKS.getKey(block);
        var texture = key.withPath(id -> ModelProvider.BLOCK_FOLDER + "/" + id);
        return new BlockWithName(
                key.getPath(),
                block,
                key,
                texture
        );
    }

    String name();
    ResourceLocation key();
    ResourceLocation texture();
    Block block();
}
