package org.mangorage.registrationutils.core.data.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;

// Default Provider, doesnt need it for blocks.
// like cube_all.json
public interface IDefaultModelProvider {
    void generate(BlockModelProvider provider);
    ResourceLocation getParent(int index);
}
