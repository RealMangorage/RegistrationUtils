package org.mangorage.registrationutils.data.models.block;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.data.core.IBlockWithName;
import org.mangorage.registrationutils.data.core.IDefaultBlockStateModelProvider;
import org.mangorage.registrationutils.data.core.TextureMap;

import static org.mangorage.registrationutils.data.models.ModelConstants.BLOCK_MODEL;

public final class TintableStairsModel implements IDefaultBlockStateModelProvider {
    private static final IDefaultBlockStateModelProvider TINTABLE_STAIRS_MODEl = new TintableStairsModel();

    public static IDefaultBlockStateModelProvider of() {
        return TINTABLE_STAIRS_MODEl;
    }

    private final ResourceLocation PARENT = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs");
    private final ResourceLocation PARENT_INNER =  ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs_inner");
    private final ResourceLocation PARENT_OUTER = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs_outer");

    private TintableStairsModel() {}

    @Override
    public void generate(BlockStateProvider provider) {
        provider.models()
                .withExistingParent("tintable_stairs", BLOCK_MODEL)
                .texture("particle", "#all")
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 135, 0)
                .translation(0, 0, 0)
                .scale(0.625f, 0.625f, 0.625f)
                .end()
                .end()
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end()
                .element()
                .from(8, 8, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        provider.models()
                .withExistingParent("tintable_stairs_inner", BLOCK_MODEL)
                .texture("particle", "#all")
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end()
                .element()
                .from(8, 8, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end()
                .element()
                .from(0, 8, 8)
                .to(8, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        provider.models()
                .withExistingParent("tintable_stairs_outer", BLOCK_MODEL)
                .texture("particle", "#all")
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end()
                .element()
                .from(8, 8, 8)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

    }

    @Override
    public BlockModelBuilder[] applyParents(BlockStateProvider blockStateProvider, String name, TextureMap textureMap) {
        var texture = textureMap.getKeys().get("all");

        var stairs = blockStateProvider.models()
                .withExistingParent(name, PARENT)
                .texture("all", texture);
        var stairsInner = blockStateProvider.models()
                .withExistingParent(name + "_inner", PARENT_INNER)
                .texture("all", texture);
        var stairsOuter = blockStateProvider.models()
                .withExistingParent(name + "_outer", PARENT_OUTER)
                .texture("all", texture);

        return new BlockModelBuilder[]{stairs, stairsInner, stairsOuter};
    }

    @Override
    public void create(BlockStateProvider blockStateProvider, IBlockWithName blockWithName, TextureMap textureMap, boolean includeBlockItems) {
        var models = applyParents(blockStateProvider, blockWithName.name(), textureMap);

        blockStateProvider.getVariantBuilder(blockWithName.block())
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                        yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
                    }
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                        yRot += 90; // Top stairs are rotated 90 degrees clockwise
                    }
                    yRot %= 360;
                    boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(shape == StairsShape.STRAIGHT ? models[0] : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? models[1] : models[2])
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StairBlock.WATERLOGGED);
        if (includeBlockItems)
            blockStateProvider.simpleBlockItem(blockWithName.block(), models[0]);
    }
}
