package org.mangorage.registrationutils.core.data.models;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.data.core.IDefaultModelProvider;
import org.mangorage.registrationutils.core.data.core.TextureMap;

import static org.mangorage.registrationutils.core.data.models.ModelConstants.BLOCK_MODEL;

public class TintableBlockModel implements IDefaultModelProvider<BlockModelBuilder> {
    private static final TintableBlockModel TINTABLE_BLOCK_MODEL = new TintableBlockModel();

    public static TintableBlockModel of() {
        return TINTABLE_BLOCK_MODEL;
    }

    private final ResourceLocation PARENT = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_block");

    @Override
    public void generate(BlockModelProvider models) {
        models
                .withExistingParent("tintable_block", BLOCK_MODEL)
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((d, b) -> b.texture("#" + d.getName()).tintindex(0))
                .end();
    }

    @Override
    public BlockModelBuilder[] applyParents(BlockStateProvider blockStateProvider, String name, TextureMap textureMap) {
        var model = blockStateProvider.models()
                .withExistingParent(name, PARENT);

        textureMap.getKeys().forEach(model::texture);

        return new BlockModelBuilder[]{model};
    }

    @Override
    public void create(BlockStateProvider blockStateProvider, Block block, String name, TextureMap textureMap, boolean includeBlockItems) {
        var model = applyParents(blockStateProvider, name, textureMap)[0];
        blockStateProvider
                .getVariantBuilder(block)
                .partialState()
                .addModels(new ConfiguredModel(model));
        if (includeBlockItems)
            blockStateProvider.simpleBlockItem(block, model);
    }
}
