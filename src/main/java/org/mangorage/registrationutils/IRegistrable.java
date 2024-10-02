package org.mangorage.registrationutils;

public interface IRegistrable {
    default String getSubId() {
        return toString().toLowerCase();
    }
}
