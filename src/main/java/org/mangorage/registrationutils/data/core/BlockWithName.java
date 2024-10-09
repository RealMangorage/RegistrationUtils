package org.mangorage.registrationutils.data.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

record BlockWithName(String name, Block block, ResourceLocation key, ResourceLocation texture) implements IBlockWithName { }
