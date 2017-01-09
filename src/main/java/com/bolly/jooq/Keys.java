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
import com.bolly.jooq.tables.records.ActorRecord;
import com.bolly.jooq.tables.records.DirectorRecord;
import com.bolly.jooq.tables.records.MovieActorRecord;
import com.bolly.jooq.tables.records.MovieRecord;
import com.bolly.jooq.tables.records.MovieTypeRecord;
import com.bolly.jooq.tables.records.TypeRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>bolly</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ActorRecord, Short> IDENTITY_ACTOR = Identities0.IDENTITY_ACTOR;
    public static final Identity<DirectorRecord, Short> IDENTITY_DIRECTOR = Identities0.IDENTITY_DIRECTOR;
    public static final Identity<MovieRecord, Integer> IDENTITY_MOVIE = Identities0.IDENTITY_MOVIE;
    public static final Identity<TypeRecord, Byte> IDENTITY_TYPE = Identities0.IDENTITY_TYPE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ActorRecord> KEY_ACTOR_PRIMARY = UniqueKeys0.KEY_ACTOR_PRIMARY;
    public static final UniqueKey<ActorRecord> KEY_ACTOR_ACTOR_NAME_UNIQ = UniqueKeys0.KEY_ACTOR_ACTOR_NAME_UNIQ;
    public static final UniqueKey<DirectorRecord> KEY_DIRECTOR_PRIMARY = UniqueKeys0.KEY_DIRECTOR_PRIMARY;
    public static final UniqueKey<DirectorRecord> KEY_DIRECTOR_DIR_NAME_UNIQ = UniqueKeys0.KEY_DIRECTOR_DIR_NAME_UNIQ;
    public static final UniqueKey<MovieRecord> KEY_MOVIE_PRIMARY = UniqueKeys0.KEY_MOVIE_PRIMARY;
    public static final UniqueKey<MovieRecord> KEY_MOVIE_IDX_MOVIE_YEAR_NAME = UniqueKeys0.KEY_MOVIE_IDX_MOVIE_YEAR_NAME;
    public static final UniqueKey<MovieActorRecord> KEY_MOVIE_ACTOR_MA_UNIQ = UniqueKeys0.KEY_MOVIE_ACTOR_MA_UNIQ;
    public static final UniqueKey<MovieTypeRecord> KEY_MOVIE_TYPE_MT_UNIQ = UniqueKeys0.KEY_MOVIE_TYPE_MT_UNIQ;
    public static final UniqueKey<TypeRecord> KEY_TYPE_PRIMARY = UniqueKeys0.KEY_TYPE_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<MovieRecord, DirectorRecord> FK_M_DIR = ForeignKeys0.FK_M_DIR;
    public static final ForeignKey<MovieActorRecord, MovieRecord> FK_MA_MOV = ForeignKeys0.FK_MA_MOV;
    public static final ForeignKey<MovieActorRecord, ActorRecord> FK_MA_ACT = ForeignKeys0.FK_MA_ACT;
    public static final ForeignKey<MovieTypeRecord, MovieRecord> FK_MT_MOV = ForeignKeys0.FK_MT_MOV;
    public static final ForeignKey<MovieTypeRecord, TypeRecord> FK_MT_TYPE = ForeignKeys0.FK_MT_TYPE;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<ActorRecord, Short> IDENTITY_ACTOR = createIdentity(Actor.ACTOR, Actor.ACTOR.ID);
        public static Identity<DirectorRecord, Short> IDENTITY_DIRECTOR = createIdentity(Director.DIRECTOR, Director.DIRECTOR.ID);
        public static Identity<MovieRecord, Integer> IDENTITY_MOVIE = createIdentity(Movie.MOVIE, Movie.MOVIE.ID);
        public static Identity<TypeRecord, Byte> IDENTITY_TYPE = createIdentity(Type.TYPE, Type.TYPE.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ActorRecord> KEY_ACTOR_PRIMARY = createUniqueKey(Actor.ACTOR, "KEY_ACTOR_PRIMARY", Actor.ACTOR.ID);
        public static final UniqueKey<ActorRecord> KEY_ACTOR_ACTOR_NAME_UNIQ = createUniqueKey(Actor.ACTOR, "KEY_ACTOR_actor_name_uniq", Actor.ACTOR.NAME);
        public static final UniqueKey<DirectorRecord> KEY_DIRECTOR_PRIMARY = createUniqueKey(Director.DIRECTOR, "KEY_DIRECTOR_PRIMARY", Director.DIRECTOR.ID);
        public static final UniqueKey<DirectorRecord> KEY_DIRECTOR_DIR_NAME_UNIQ = createUniqueKey(Director.DIRECTOR, "KEY_DIRECTOR_dir_name_uniq", Director.DIRECTOR.NAME);
        public static final UniqueKey<MovieRecord> KEY_MOVIE_PRIMARY = createUniqueKey(Movie.MOVIE, "KEY_MOVIE_PRIMARY", Movie.MOVIE.ID);
        public static final UniqueKey<MovieRecord> KEY_MOVIE_IDX_MOVIE_YEAR_NAME = createUniqueKey(Movie.MOVIE, "KEY_MOVIE_idx_movie_year_name", Movie.MOVIE.RELEASE_YEAR, Movie.MOVIE.NAME);
        public static final UniqueKey<MovieActorRecord> KEY_MOVIE_ACTOR_MA_UNIQ = createUniqueKey(MovieActor.MOVIE_ACTOR, "KEY_MOVIE_ACTOR_ma_uniq", MovieActor.MOVIE_ACTOR.MOVIE_ID, MovieActor.MOVIE_ACTOR.ACTOR_ID);
        public static final UniqueKey<MovieTypeRecord> KEY_MOVIE_TYPE_MT_UNIQ = createUniqueKey(MovieType.MOVIE_TYPE, "KEY_MOVIE_TYPE_mt_uniq", MovieType.MOVIE_TYPE.MOVIE_ID, MovieType.MOVIE_TYPE.TYPE_ID);
        public static final UniqueKey<TypeRecord> KEY_TYPE_PRIMARY = createUniqueKey(Type.TYPE, "KEY_TYPE_PRIMARY", Type.TYPE.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<MovieRecord, DirectorRecord> FK_M_DIR = createForeignKey(com.bolly.jooq.Keys.KEY_DIRECTOR_PRIMARY, Movie.MOVIE, "fk_m_dir", Movie.MOVIE.DIRECTOR_ID);
        public static final ForeignKey<MovieActorRecord, MovieRecord> FK_MA_MOV = createForeignKey(com.bolly.jooq.Keys.KEY_MOVIE_PRIMARY, MovieActor.MOVIE_ACTOR, "fk_ma_mov", MovieActor.MOVIE_ACTOR.MOVIE_ID);
        public static final ForeignKey<MovieActorRecord, ActorRecord> FK_MA_ACT = createForeignKey(com.bolly.jooq.Keys.KEY_ACTOR_PRIMARY, MovieActor.MOVIE_ACTOR, "fk_ma_act", MovieActor.MOVIE_ACTOR.ACTOR_ID);
        public static final ForeignKey<MovieTypeRecord, MovieRecord> FK_MT_MOV = createForeignKey(com.bolly.jooq.Keys.KEY_MOVIE_PRIMARY, MovieType.MOVIE_TYPE, "fk_mt_mov", MovieType.MOVIE_TYPE.MOVIE_ID);
        public static final ForeignKey<MovieTypeRecord, TypeRecord> FK_MT_TYPE = createForeignKey(com.bolly.jooq.Keys.KEY_TYPE_PRIMARY, MovieType.MOVIE_TYPE, "fk_mt_type", MovieType.MOVIE_TYPE.TYPE_ID);
    }
}
