package org.mangorage.registrationutils.core;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public record ColorProvider(int color) implements BlockColor, ItemColor {
    public static ColorProvider of(int color) {
        return new ColorProvider(color);
    }

    @Override
    public int getColor(BlockState p_92567_, @Nullable BlockAndTintGetter p_92568_, @Nullable BlockPos p_92569_, int p_92570_) {
        return color;
    }

    @Override
    public int getColor(ItemStack p_92672_, int p_92673_) {
        return color;
    }
}
