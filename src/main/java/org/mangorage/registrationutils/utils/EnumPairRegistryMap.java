package org.mangorage.registrationutils.utils;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class EnumPairRegistryMap<
        E extends Enum<E> & IRegistryEnum,
        BA, // Base GroupA
        BB, // Base GroupB
        SA extends BA, // Super GroupA
        SB extends BB // Super GroupB
        >
{

    public static <E extends Enum<E> & IRegistryEnum, BA, BB, SA extends BA, SB extends BB> EnumPairRegistryMap<E, BA, BB, SA, SB> create(
            Class<E> eClass,
            String baseId,
            DeferredRegister<BA> deferredRegisterBaseA,
            DeferredRegister<BB> deferredRegisterBaseB,
            BiConsumer<IEnumPairHolder<E, RegistryObject<BA>>, IEnumPairHolder<E, RegistryObject<BB>>> biConsumer,
            Function<E, SA> functionA,
            BiFunction<E, SA, SB> functionB
    ) {
        return new EnumPairRegistryMap<>(eClass, baseId, deferredRegisterBaseA, deferredRegisterBaseB, biConsumer, functionA, functionB);
    }


    private final EnumMap<E, IEnumPairHolder<E, RegistryObject<SA>>> groupA;
    private final EnumMap<E, IEnumPairHolder<E, RegistryObject<SB>>> groupB;

    private EnumPairRegistryMap(
            Class<E> eClass,
            String baseId,
            DeferredRegister<BA> deferredRegisterBaseA,
            DeferredRegister<BB> deferredRegisterBaseB,
            BiConsumer<IEnumPairHolder<E, RegistryObject<BA>>, IEnumPairHolder<E, RegistryObject<BB>>> biConsumer,
            Function<E, SA> functionA,
            BiFunction<E, SA, SB> functionB
    ) {
        this.groupA = new EnumMap<>(eClass);
        this.groupB = new EnumMap<>(eClass);

        var enums = eClass.getEnumConstants();

        for (E anEnum : enums) {
            var funcAResult = deferredRegisterBaseA.register(anEnum.getSubId() + "_" + baseId, () -> functionA.apply(anEnum));
            var funcBResult = deferredRegisterBaseB.register(anEnum.getSubId() + "_" + baseId, () -> functionB.apply(anEnum, funcAResult.get()));

            var holderAResult = new PairHolderImpl<>(anEnum, funcAResult);
            var holderBResult = new PairHolderImpl<>(anEnum, funcBResult);

            biConsumer.accept(cast(holderAResult), cast(holderBResult));

            groupA.put(anEnum, holderAResult);
            groupB.put(anEnum, holderBResult);
        }
    }

    public IEnumPairHolder<E, RegistryObject<SA>> getLeftHolder(E type) {
        return groupA.get(type);
    }

    public IEnumPairHolder<E, RegistryObject<SB>> getRightHolder(E type) {
        return groupB.get(type);
    }

    public Collection<IEnumPairHolder<E, RegistryObject<SA>>> getAllLeftHolder() {
        return groupA.values();
    }

    public Collection<IEnumPairHolder<E, RegistryObject<SB>>> getAllRightHolder() {
        return groupB.values();
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(Object o) {
        return (T) o;
    }
}
