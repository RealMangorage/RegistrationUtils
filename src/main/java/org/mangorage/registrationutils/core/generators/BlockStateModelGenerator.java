package org.mangorage.registrationutils.core.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.Registration;
import org.mangorage.registrationutils.data.core.Helpers;
import org.mangorage.registrationutils.data.core.IBlockWithName;
import org.mangorage.registrationutils.data.core.TextureMap;
import org.mangorage.registrationutils.data.models.block.TintableBlockModel;
import org.mangorage.registrationutils.data.models.block.TintableSlabModel;
import org.mangorage.registrationutils.data.models.block.TintableStairsModel;

public class BlockStateModelGenerator extends BlockStateProvider {
    private static final ResourceLocation BLOCK_MODEL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/block");

    public BlockStateModelGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RegistrationUtils.MODID, exFileHelper);
    }

    protected void buildParents() {
        TintableBlockModel.of().generate(this);
        TintableSlabModel.of().generate(this);
        TintableStairsModel.of().generate(this);
    }

    @Override
    protected void registerStatesAndModels() {
        buildParents();

        Registration.WOOD_PLANKS.getAllLeftHolder().forEach(holder -> {
            var b = holder.getRight().get();
            TintableBlockModel.of().create(
                    this,
                    IBlockWithName.of(b),
                    TextureMap.create()
                            .textureAll(d -> Helpers.getBlockTexture(RegistrationUtils.MODID, "wood"))
                            .texture("particle", Helpers.getBlockTexture(RegistrationUtils.MODID, "wood")),
                    true);
        });

        Registration.WOOD_SLABS.getAllLeftHolder().forEach(holder -> {
            var b = holder.getRight().get();
            TintableSlabModel.of().create(
                    this,
                    IBlockWithName.of(b),
                    TextureMap.create()
                            .texture("all", Helpers.getBlockTexture(RegistrationUtils.MODID, "wood")),
                    true
            );
        });

        Registration.WOOD_STAIRS.getAllLeftHolder().forEach(holder -> {
            var b = holder.getRight().get();
            TintableStairsModel.of().create(
                    this,
                    IBlockWithName.of(b),
                    TextureMap.create()
                            .texture("all", Helpers.getBlockTexture(RegistrationUtils.MODID, "wood")),
                    true
            );
        });
    }
}
