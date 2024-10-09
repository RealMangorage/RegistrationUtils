package org.mangorage.registrationutils.data.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;

public class Helpers {
    public static ResourceLocation getBlockTexture(String modId, String textureId) {
        return ResourceLocation.fromNamespaceAndPath(modId, ModelProvider.BLOCK_FOLDER + "/" + textureId);
    }
}
