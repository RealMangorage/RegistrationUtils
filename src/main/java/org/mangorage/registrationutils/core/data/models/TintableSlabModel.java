package org.mangorage.registrationutils.core.data.models;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import org.mangorage.registrationutils.RegistrationUtils;
import org.mangorage.registrationutils.core.data.core.IDefaultModelProvider;

public class TintableSlabModel implements IDefaultModelProvider {
    private static final ResourceLocation BLOCK_MODEL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/block");

    private final ResourceLocation PARENT_TOP = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_top");
    private final ResourceLocation PARENT_BOTTOM = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_bottom");
    private final ResourceLocation PARENT_DOUBLE = ResourceLocation.fromNamespaceAndPath(RegistrationUtils.MODID, "block/tintable_slab_double");

    @Override
    public void generate(BlockModelProvider models) {
        models
                .withExistingParent("tintable_slab_top", BLOCK_MODEL)
                .element()
                .from(0, 8, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        models
                .withExistingParent("tintable_slab_bottom", BLOCK_MODEL)
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();

        models
                .withExistingParent("tintable_slab_double", BLOCK_MODEL)
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((d, a) -> a.texture("#all").tintindex(0))
                .end();
    }

    @Override
    public ResourceLocation getParent(int index) {
        if (index == 0)
            return PARENT_TOP;
        if (index == 1)
            return PARENT_BOTTOM;
        if (index == 2)
            return PARENT_DOUBLE;
        return null;
    }
}
