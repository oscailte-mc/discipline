/*
 * Discipline
 *
 * Copyright (c) 2022 RÃ­an Errity (Paradaux)
 *                    Glare
 *                    Oscailte / Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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

    public static void register(final Discipline discipline) {
        DisciplineProvider.discipline = discipline;
    }

    public static Discipline discipline() {
        if (DisciplineProvider.discipline == null) {
            throw new IllegalStateException("Discipline isn't ready."); // Carbon and LuckPerms design go brr
        }

        return DisciplineProvider.discipline;
    }
}
