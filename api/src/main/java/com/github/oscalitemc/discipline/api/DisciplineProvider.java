package com.github.oscalitemc.discipline.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

/**
 * Singleton Provider of a {@link Discipline} instance.
 * @since 0.0.1
 * TODO Ensure each platform registers an instance of discipline
 * */
@DefaultQualifier(NonNull.class)
public class DisciplineProvider {

    private static @Nullable Discipline discipline;

    private DisciplineProvider() { }

    public static void register(Discipline discipline) {
        DisciplineProvider.discipline = discipline;
    }

    public static Discipline discipline() {
        if (discipline == null) {
            throw new IllegalStateException("Discipline isn't ready."); // Carbon and LuckPerms design go brr
        }

        return discipline;
    }
}
