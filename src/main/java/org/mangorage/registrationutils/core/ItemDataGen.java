package org.mangorage.registrationutils.core;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mangorage.registrationutils.RegistrationUtils;

public class ItemDataGen extends ItemModelProvider {
    public ItemDataGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegistrationUtils.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        RegistrationUtils.WOOD_PLANKS.getLeftMap().getAll().forEach(i -> {
            basicItem(i.get());
        });
    }
}
