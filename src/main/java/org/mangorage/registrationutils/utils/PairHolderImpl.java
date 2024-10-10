package org.mangorage.registrationutils.utils;

public final class PairHolderImpl<A, B> implements IEnumPairHolder<A, B> {

    private final A a;
    private final B b;

    PairHolderImpl(A a, B b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public A getLeft() {
        return a;
    }

    @Override
    public B getRight() {
        return b;
    }
}
