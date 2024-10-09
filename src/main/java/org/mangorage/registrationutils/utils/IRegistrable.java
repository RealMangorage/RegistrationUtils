package org.mangorage.registrationutils.utils;

public interface IRegistrable {
    default String getSubId() {
        return toString().toLowerCase();
    }
}
