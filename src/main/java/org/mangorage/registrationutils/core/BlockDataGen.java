package org.mangorage.registrationutils.core;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.mangorage.registrationutils.RegistrationUtils;

public class BlockDataGen extends BlockStateProvider {
    public BlockDataGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RegistrationUtils.MODID, exFileHelper);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    public ResourceLocation blockTexture(Block block, String id) {
        return key(block).withPath(p -> ModelProvider.BLOCK_FOLDER + '/' + id);
    }

    private BlockModelBuilder tint(BlockModelBuilder builder) {
        return builder
                .element()
                .allFaces(((direction, faceBuilder) -> faceBuilder.tintindex(0).texture("#top")))
                .end();

    }

    private BlockModelBuilder tintAll(BlockModelBuilder builder) {
        return builder
                .element()
                .to(16, 16, 16)
                .allFaces(((direction, faceBuilder) -> faceBuilder.tintindex(0).texture("#all")))
                .end();

    }


    public void slabBlockTint(SlabBlock slab, ResourceLocation tintTexture, boolean includeBlockItem) {

        var top = models()
                .cubeAll(name(slab) + "_top", tintTexture)
                .element()
                .from(0, 8, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        var bottom = models()
                .cubeAll(name(slab) + "_bottom", tintTexture)
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        var doubled = models()
                .cubeAll(name(slab) + "_double", tintTexture)
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        getVariantBuilder(slab)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubled));

        if (includeBlockItem)
            simpleBlockItem(slab, bottom);
    }

    @Override
    protected void registerStatesAndModels() {
        RegistrationUtils.WOOD_PLANKS.getRightMap().getAll().forEach(b -> {
            simpleBlockWithItem(
                    b.get(),
                    tintAll(models().cubeAll(name(b.get()), blockTexture(b.get(), "stone")))
            );
        });

        RegistrationUtils.WOOD_SLAB.getRightMap().getAll().forEach(b -> {
            slabBlockTint(b.get(), blockTexture(b.get(), "stone"), true);
        });
    }
}
