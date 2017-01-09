/**
 * This class is generated by jOOQ
 */
package com.bolly.jooq.tables;


import com.bolly.jooq.Bolly;
import com.bolly.jooq.Keys;
import com.bolly.jooq.tables.records.MovieActorRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class MovieActor extends TableImpl<MovieActorRecord> {

    private static final long serialVersionUID = 711008674;

    /**
     * The reference instance of <code>bolly.MOVIE_ACTOR</code>
     */
    public static final MovieActor MOVIE_ACTOR = new MovieActor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovieActorRecord> getRecordType() {
        return MovieActorRecord.class;
    }

    /**
     * The column <code>bolly.MOVIE_ACTOR.movie_id</code>.
     */
    public final TableField<MovieActorRecord, Integer> MOVIE_ID = createField("movie_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE_ACTOR.actor_id</code>.
     */
    public final TableField<MovieActorRecord, Short> ACTOR_ID = createField("actor_id", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * Create a <code>bolly.MOVIE_ACTOR</code> table reference
     */
    public MovieActor() {
        this("MOVIE_ACTOR", null);
    }

    /**
     * Create an aliased <code>bolly.MOVIE_ACTOR</code> table reference
     */
    public MovieActor(String alias) {
        this(alias, MOVIE_ACTOR);
    }

    private MovieActor(String alias, Table<MovieActorRecord> aliased) {
        this(alias, aliased, null);
    }

    private MovieActor(String alias, Table<MovieActorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Bolly.BOLLY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MovieActorRecord>> getKeys() {
        return Arrays.<UniqueKey<MovieActorRecord>>asList(Keys.KEY_MOVIE_ACTOR_MA_UNIQ);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<MovieActorRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MovieActorRecord, ?>>asList(Keys.FK_MA_MOV, Keys.FK_MA_ACT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieActor as(String alias) {
        return new MovieActor(alias, this);
    }

    /**
     * Rename this table
     */
    public MovieActor rename(String name) {
        return new MovieActor(name, null);
    }
}
