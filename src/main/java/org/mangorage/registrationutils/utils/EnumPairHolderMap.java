package org.mangorage.registrationutils.utils;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public abstract sealed class EnumPairHolderMap<E extends Enum<E>, A, B> permits EnumPairHolderMap.Immutable, EnumPairHolderMap.Mutable {

    public static <E extends Enum<E>, A, B> EnumPairHolderMap<E, A, B> create() {
        return new Mutable<>();
    }


    EnumPairHolderMap() {}

    abstract public void addRight(IEnumPairHolder<E, A> a);
    abstract public void addLeft(IEnumPairHolder<E, B> b);
    abstract public List<IEnumPairHolder<E, A>> getRightList();
    abstract public List<IEnumPairHolder<E, B>> getLeftList();
    abstract public EnumPairHolderMap<E, A, B> immutable();

    static final class Mutable<E extends Enum<E>, A, B> extends EnumPairHolderMap<E, A, B> {
        private final List<IEnumPairHolder<E, A>> listA;
        private final List<IEnumPairHolder<E, B>> listB;
        private final Immutable<E, A, B> immutableList;

        public Mutable() {
            this.listA = new ArrayList<>();
            this.listB = new ArrayList<>();
            this.immutableList = new Immutable<>(ImmutableList.copyOf(listA), ImmutableList.copyOf(listB));
        }

        public void addRight(IEnumPairHolder<E, A> a) {
            this.listA.add(a);
            immutableList.updateLeft(this);
        }

        public void addLeft(IEnumPairHolder<E, B> b) {
            this.listB.add(b);
            immutableList.updateRight(this);
        }

        public List<IEnumPairHolder<E, A>> getRightList() {
            return listA;
        }

        public List<IEnumPairHolder<E, B>> getLeftList() {
            return listB;
        }

        @Override
        public EnumPairHolderMap<E, A, B> immutable() {
            return immutableList;
        }

    }

    static final class Immutable<E extends Enum<E>, A, B> extends EnumPairHolderMap<E, A, B> {
        private final Object[] locks = new Object[]{new Object(), new Object()};
        private List<IEnumPairHolder<E, A>> immutableListA;
        private List<IEnumPairHolder<E, B>> immutableListB;

        Immutable(List<IEnumPairHolder<E, A>> immutableListA, List<IEnumPairHolder<E, B>> immutableListB) {
            this.immutableListA = immutableListA;
            this.immutableListB = immutableListB;
        }

        void updateRight(Mutable<E, A, B> map) {
            synchronized (locks[0]) {
                this.immutableListA = ImmutableList.copyOf(map.listA);
            }
        }

        void updateLeft(Mutable<E, A, B> map) {
            synchronized (locks[1]) {
                this.immutableListB = ImmutableList.copyOf(map.listB);
            }
        }

        @Override
        public void addRight(IEnumPairHolder<E, A> a) {
            throw new IllegalStateException("Cant add to an Immutable Map");
        }

        @Override
        public void addLeft(IEnumPairHolder<E, B> b) {
            throw new IllegalStateException("Cant add to an Immutable Map");
        }

        @Override
        public List<IEnumPairHolder<E, A>> getRightList() {
            return immutableListA;
        }

        @Override
        public List<IEnumPairHolder<E, B>> getLeftList() {
            return immutableListB;
        }

        @Override
        public EnumPairHolderMap<E, A, B> immutable() {
            return this;
        }
    }
}
