package org.mangorage.registrationutils.utils;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public final class EnumRegistryObjectMap<E extends Enum<E> & IRegistrable, B, O extends B> {
    public static<E extends Enum<E> & IRegistrable, B, O extends B>  EnumRegistryObjectMap<E, B, O> create(Class<E> enumClass, String majorId, Function<E, O> creator, DeferredRegister<B> deferredRegister) {
        return new EnumRegistryObjectMap<>(enumClass, majorId, creator, deferredRegister);
    }

    public record Entry<K, V>(K key, V value) {}

    private final Function<Integer, E> enumGetter;
    private final RegistryObject<O>[] backingMap;
    private final Set<Entry<E, RegistryObject<O>>> entrySet = new HashSet<>();

    private EnumRegistryObjectMap(Class<E> enumClass, String majorId, Function<E, O> creator, DeferredRegister<B> deferredRegister) {
        var list = enumClass.getEnumConstants();
        this.backingMap = new RegistryObject[list.length];
        this.enumGetter = i -> list[i];
        for (E enumConstant : list) {
            this.backingMap[enumConstant.ordinal()] = deferredRegister.register(enumConstant.getSubId() + "_" + majorId, () -> creator.apply(enumConstant));
            this.entrySet.add(new Entry<>(enumConstant, this.backingMap[enumConstant.ordinal()]));
        }
    }

    public RegistryObject<O> get(E enumType) {
        return backingMap[enumType.ordinal()];
    }

    public Collection<RegistryObject<O>> getAll() {
        return List.of(backingMap);
    }

    public Set<Entry<E, RegistryObject<O>>> entrySet() {
        return entrySet;
    }
}
