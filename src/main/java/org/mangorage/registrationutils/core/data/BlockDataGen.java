package org.mangorage.registrationutils.core.data;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.Registration;
import org.mangorage.registrationutils.core.data.core.IDefaultModelProvider;
import org.mangorage.registrationutils.core.data.models.TintableSlabModel;

public class BlockDataGen extends BlockStateProvider {
    private static final ResourceLocation BLOCK_MODEL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/block");
    private static final IDefaultModelProvider TINTABLE_SLABS = new TintableSlabModel();


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

    protected void buildParents() {
        // STAIRS
        models()
                .withExistingParent("tintable_stairs", BLOCK_MODEL)
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

        models()
                .withExistingParent("tintable_stairs_inner", BLOCK_MODEL)
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

        models()
                .withExistingParent("tintable_stairs_outer", BLOCK_MODEL)
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

        TINTABLE_SLABS.generate(models());
    }

    public void fullBlockWithTint(Block block, ResourceLocation tintTexture, boolean includeBlockItem) {
        var model = models().cubeAll(name(block), tintTexture)
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((d, f) -> f.tintindex(0).texture("#all"))
                .end();

        getVariantBuilder(block)
                .partialState()
                .addModels(new ConfiguredModel(model));

        if (includeBlockItem)
            simpleBlockItem(block, model);
    }

    public void slabBlockTint(SlabBlock block, ResourceLocation tintTexture, boolean includeBlockItem) {
        var top = models()
                .withExistingParent(name(block) + "_top", TINTABLE_SLABS.getParent(0))
                .texture("all", tintTexture);

        var bottom = models()
                .withExistingParent(name(block) + "_bottom", TINTABLE_SLABS.getParent(1))
                .texture("all", tintTexture);

        var doubled = models()
                .withExistingParent(name(block) + "_double", TINTABLE_SLABS.getParent(2))
                .texture("all", tintTexture);

        getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubled));

        if (includeBlockItem)
            simpleBlockItem(block, bottom);
    }

    public void stairBlockTint(StairBlock block, ResourceLocation tintTexture, boolean includeBlockItem) {
        var stairs = models()
                .withExistingParent(name(block), ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs"))
                .texture("all", tintTexture);
        var stairsInner = models()
                .withExistingParent(name(block) + "_inner", ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs_inner"))
                .texture("all", tintTexture);
        var stairsOuter = models()
                .withExistingParent(name(block) + "_outer", ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "tintable_stairs_outer"))
                .texture("all", tintTexture);

        getVariantBuilder(block)
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
                            .modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StairBlock.WATERLOGGED);

        if (includeBlockItem)
            simpleBlockItem(
                    block,
                    stairs
            );
    }

    @Override
    protected void registerStatesAndModels() {

        buildParents();

        Registration.WOOD_PLANKS.getRightMap().getAll().forEach(b -> {
            fullBlockWithTint(b.get(), blockTexture(b.get(), "wood"), true);
        });

        Registration.WOOD_SLABS.getRightMap().getAll().forEach(b -> {
            slabBlockTint(b.get(), blockTexture(b.get(), "wood"), true);
        });

        Registration.WOOD_STAIRS.getRightMap().getAll().forEach(b -> {
            stairBlockTint(b.get(), blockTexture(b.get(), "wood"), true);
        });
    }
}
