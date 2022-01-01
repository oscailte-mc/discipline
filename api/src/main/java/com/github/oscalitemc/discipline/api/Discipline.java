package com.github.oscalitemc.discipline.api;

import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * Discipline Plugin Interface.
 * Obtain an instance via {@link DisciplineProvider}
 * TODO link specification of this class
 * TODO Flesh out.
 * @since 0.0.1
 * */
public interface Discipline {

    /**
     *
     * */
    Logger logger();

    /**
     *
     * */
    Path pluginDirectory();
}
