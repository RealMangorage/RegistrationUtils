package org.mangorage.registrationutils.core.data.core;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class TextureMap {
    public static TextureMap create() {
        return new TextureMap();
    }

    private HashMap<String, ResourceLocation> textures = new HashMap<>();

    private TextureMap() {}

    public TextureMap texture(String id, ResourceLocation value) {
        this.textures.put(id, value);
        return this;
    }

    public TextureMap texture(Direction direction, ResourceLocation value) {
        return texture(direction.getName(), value);
    }

    public TextureMap textureAll(Function<Direction, ResourceLocation> textures) {
        for (Direction direction : Direction.values()) {
            texture(direction, textures.apply(direction));
        }
        return this;
    }

    public Map<String, ResourceLocation> getKeys() {
        return ImmutableMap.copyOf(textures);
    }
}
