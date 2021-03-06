/**
 * This class is generated by jOOQ
 */
package com.bolly.jooq;


import com.bolly.jooq.tables.Actor;
import com.bolly.jooq.tables.Director;
import com.bolly.jooq.tables.Movie;
import com.bolly.jooq.tables.MovieActor;
import com.bolly.jooq.tables.MovieType;
import com.bolly.jooq.tables.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Bolly extends SchemaImpl {

    private static final long serialVersionUID = -1735695774;

    /**
     * The reference instance of <code>bolly</code>
     */
    public static final Bolly BOLLY = new Bolly();

    /**
     * The table <code>bolly.ACTOR</code>.
     */
    public final Actor ACTOR = com.bolly.jooq.tables.Actor.ACTOR;

    /**
     * The table <code>bolly.DIRECTOR</code>.
     */
    public final Director DIRECTOR = com.bolly.jooq.tables.Director.DIRECTOR;

    /**
     * The table <code>bolly.MOVIE</code>.
     */
    public final Movie MOVIE = com.bolly.jooq.tables.Movie.MOVIE;

    /**
     * The table <code>bolly.MOVIE_ACTOR</code>.
     */
    public final MovieActor MOVIE_ACTOR = com.bolly.jooq.tables.MovieActor.MOVIE_ACTOR;

    /**
     * The table <code>bolly.MOVIE_TYPE</code>.
     */
    public final MovieType MOVIE_TYPE = com.bolly.jooq.tables.MovieType.MOVIE_TYPE;

    /**
     * The table <code>bolly.TYPE</code>.
     */
    public final Type TYPE = com.bolly.jooq.tables.Type.TYPE;

    /**
     * No further instances allowed
     */
    private Bolly() {
        super("bolly", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Actor.ACTOR,
            Director.DIRECTOR,
            Movie.MOVIE,
            MovieActor.MOVIE_ACTOR,
            MovieType.MOVIE_TYPE,
            Type.TYPE);
    }
}
