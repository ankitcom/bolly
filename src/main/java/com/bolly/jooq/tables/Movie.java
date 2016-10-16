/**
 * This class is generated by jOOQ
 */
package com.bolly.jooq.tables;


import com.bolly.jooq.Bolly;
import com.bolly.jooq.Keys;
import com.bolly.jooq.tables.records.MovieRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Movie extends TableImpl<MovieRecord> {

    private static final long serialVersionUID = 1547452295;

    /**
     * The reference instance of <code>bolly.MOVIE</code>
     */
    public static final Movie MOVIE = new Movie();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovieRecord> getRecordType() {
        return MovieRecord.class;
    }

    /**
     * The column <code>bolly.MOVIE.id</code>.
     */
    public final TableField<MovieRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.name</code>.
     */
    public final TableField<MovieRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.online_stream_link</code>.
     */
    public final TableField<MovieRecord, String> ONLINE_STREAM_LINK = createField("online_stream_link", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

    /**
     * The column <code>bolly.MOVIE.rating</code>.
     */
    public final TableField<MovieRecord, Integer> RATING = createField("rating", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.review</code>.
     */
    public final TableField<MovieRecord, String> REVIEW = createField("review", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

    /**
     * The column <code>bolly.MOVIE.director_id</code>.
     */
    public final TableField<MovieRecord, Integer> DIRECTOR_ID = createField("director_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.writer</code>.
     */
    public final TableField<MovieRecord, String> WRITER = createField("writer", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

    /**
     * The column <code>bolly.MOVIE.release_date</code>.
     */
    public final TableField<MovieRecord, Date> RELEASE_DATE = createField("release_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.release_year</code>.
     */
    public final TableField<MovieRecord, Integer> RELEASE_YEAR = createField("release_year", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>bolly.MOVIE.release_decade</code>.
     */
    public final TableField<MovieRecord, Integer> RELEASE_DECADE = createField("release_decade", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>bolly.MOVIE</code> table reference
     */
    public Movie() {
        this("MOVIE", null);
    }

    /**
     * Create an aliased <code>bolly.MOVIE</code> table reference
     */
    public Movie(String alias) {
        this(alias, MOVIE);
    }

    private Movie(String alias, Table<MovieRecord> aliased) {
        this(alias, aliased, null);
    }

    private Movie(String alias, Table<MovieRecord> aliased, Field<?>[] parameters) {
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
    public Identity<MovieRecord, Integer> getIdentity() {
        return Keys.IDENTITY_MOVIE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MovieRecord> getPrimaryKey() {
        return Keys.KEY_MOVIE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MovieRecord>> getKeys() {
        return Arrays.<UniqueKey<MovieRecord>>asList(Keys.KEY_MOVIE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<MovieRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MovieRecord, ?>>asList(Keys.FK_M_DIR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie as(String alias) {
        return new Movie(alias, this);
    }

    /**
     * Rename this table
     */
    public Movie rename(String name) {
        return new Movie(name, null);
    }
}
