package org.mangorage.registrationutils.utils;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mangorage.registrationutils.IRegistrable;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class PairEnumRegistryObjectMap<K extends Enum<K> & IRegistrable, BR, BL, R extends BR, L extends BL> {

    public static <K extends Enum<K> & IRegistrable, BR, BL, R extends BR, L extends BL> PairEnumRegistryObjectMap<K, BR, BL, R, L> create(Class<K> enumClass, String id, DeferredRegister<BR> registerL, DeferredRegister<BL> registerR, Function<K, R> creatorL, BiFunction<K, EnumRegistryObjectMap<K, BR, R>,L> creatorR) {
        var map = EnumRegistryObjectMap.create(enumClass, id, creatorL, registerL);
        return create(
                map,
                e -> EnumRegistryObjectMap.create(enumClass, id, e2 -> creatorR.apply(e2, map), registerR)
        );
    }


    public static <K extends Enum<K> & IRegistrable, BR, BL, R extends BR, L extends BL> PairEnumRegistryObjectMap<K, BR, BL, R, L> create(EnumRegistryObjectMap<K, BR, R> rightMap, Function<EnumRegistryObjectMap<K, BR, R>, EnumRegistryObjectMap<K, BL, L>> leftMap) {
        return new PairEnumRegistryObjectMap<>(rightMap, leftMap.apply(rightMap));
    }

    private final EnumRegistryObjectMap<K, BR, R> rightMap;
    private final EnumRegistryObjectMap<K, BL, L> leftMap;

    private PairEnumRegistryObjectMap(EnumRegistryObjectMap<K, BR, R> rightMap, EnumRegistryObjectMap<K, BL, L> leftMap) {
        this.rightMap = rightMap;
        this.leftMap = leftMap;
    }

    public RegistryObject<R> getRight(K key) {
        return rightMap.get(key);
    }

    public RegistryObject<L> getLeft(K key) {
        return leftMap.get(key);
    }

    public EnumRegistryObjectMap<K, BR, R> getRightMap() {
        return rightMap;
    }

    public EnumRegistryObjectMap<K, BL, L> getLeftMap() {
        return leftMap;
    }
}
