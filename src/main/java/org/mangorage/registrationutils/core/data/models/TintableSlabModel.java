package org.mangorage.registrationutils.core.data.models;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.data.core.IDefaultModelProvider;
import org.mangorage.registrationutils.core.data.core.TextureMap;

import static org.mangorage.registrationutils.core.data.models.ModelConstants.BLOCK_MODEL;

public class TintableSlabModel implements IDefaultModelProvider<BlockModelBuilder> {
    private static final IDefaultModelProvider<BlockModelBuilder> TINTABLE_SLABS = new TintableSlabModel();

    public static IDefaultModelProvider<BlockModelBuilder> of() {
        return TINTABLE_SLABS;
    }

    private final ResourceLocation PARENT_TOP = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_top");
    private final ResourceLocation PARENT_BOTTOM = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_bottom");
    private final ResourceLocation PARENT_DOUBLE = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_double");

    private TintableSlabModel() {}

    @Override
    public void generate(BlockModelProvider models) {
        models
                .withExistingParent("tintable_slab_top", BLOCK_MODEL)
                .texture("particle", "#all")
                .element()
                .from(0, 8, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        models
                .withExistingParent("tintable_slab_bottom", BLOCK_MODEL)
                .texture("particle", "#all")
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        models
                .withExistingParent("tintable_slab_double", BLOCK_MODEL)
                .texture("particle", "#all")
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();
    }

    @Override
    public BlockModelBuilder[] applyParents(BlockStateProvider provider, String name, TextureMap textureMap) {
        var top = provider.models()
                .withExistingParent(name + "_top", PARENT_TOP);
        var bottom = provider.models()
                .withExistingParent(name + "_bottom", PARENT_BOTTOM);
        var doubled = provider.models()
                .withExistingParent(name + "_double", PARENT_DOUBLE);

        var keys = textureMap.getKeys();
        var allTextures = keys.get("all");

        if (allTextures != null) {
            top
                    .texture("all", allTextures);
            bottom
                    .texture("all", allTextures);
            doubled
                    .texture("all", allTextures);
        } else {
            // TODO: LATER, FIX PARENT MODEL....
        }

        return new BlockModelBuilder[]{top, bottom, doubled};
    }

    @Override
    public void create(BlockStateProvider blockStateProvider, Block block, String name, TextureMap textureMap, boolean includeBlockItem) {
        var models = applyParents(blockStateProvider, name, textureMap);

        blockStateProvider.getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(models[0]))
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(models[1]))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(models[2]));

        if (includeBlockItem)
            blockStateProvider.simpleBlockItem(block, models[1]);
    }

}
