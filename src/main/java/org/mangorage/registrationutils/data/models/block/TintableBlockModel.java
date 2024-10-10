package org.mangorage.registrationutils.data.models.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.data.core.IBlockWithName;
import org.mangorage.registrationutils.data.core.IDefaultBlockStateModelProvider;
import org.mangorage.registrationutils.data.core.TextureMap;

import static org.mangorage.registrationutils.data.models.ModelConstants.BLOCK_MODEL;

public final class TintableBlockModel implements IDefaultBlockStateModelProvider {
    private static final TintableBlockModel TINTABLE_BLOCK_MODEL = new TintableBlockModel();

    public static TintableBlockModel of() {
        return TINTABLE_BLOCK_MODEL;
    }

    private final ResourceLocation PARENT = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_block");

    @Override
    public void generate(BlockStateProvider provider) {
        provider.models()
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
    public void create(BlockStateProvider blockStateProvider, IBlockWithName blockWithName, TextureMap textureMap, boolean includeBlockItems) {
        var model = applyParents(blockStateProvider, blockWithName.name(), textureMap)[0];
        blockStateProvider
                .getVariantBuilder(blockWithName.block())
                .partialState()
                .addModels(new ConfiguredModel(model));
        if (includeBlockItems)
            blockStateProvider.simpleBlockItem(blockWithName.block(), model);
    }
}
