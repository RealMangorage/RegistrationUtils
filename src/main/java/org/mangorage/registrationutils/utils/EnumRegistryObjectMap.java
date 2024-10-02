package org.mangorage.registrationutils.utils;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mangorage.registrationutils.IRegistrable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class EnumRegistryObjectMap<E extends Enum<E> & IRegistrable, B, O extends B> {
    public static<E extends Enum<E> & IRegistrable, B, O extends B>  EnumRegistryObjectMap<E, B, O> create(Class<E> enumClass, String majorId, Function<E, O> creator, DeferredRegister<B> deferredRegister) {
        return new EnumRegistryObjectMap<>(enumClass, majorId, creator, deferredRegister);
    }



    private final Map<E, RegistryObject<O>> backingMap = new HashMap<>();

    private EnumRegistryObjectMap(Class<E> enumClass, String majorId, Function<E, O> creator, DeferredRegister<B> deferredRegister) {
        for (E enumConstant : enumClass.getEnumConstants()) {
            backingMap.put(
                    enumConstant,
                    deferredRegister.register(enumConstant.getSubId() + "_" + majorId, () -> creator.apply(enumConstant))
            );
        }
    }

    public RegistryObject<O> get(E enumType) {
        return backingMap.get(enumType);
    }

    public Collection<RegistryObject<O>> getAll() {
        return backingMap.values();
    }

    public Set<Map.Entry<E, RegistryObject<O>>> entrySet() {
        return backingMap.entrySet();
    }
}
