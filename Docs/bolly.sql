
DROP TABLE MOVIE_TYPE;
DROP TABLE TYPE;
DROP TABLE MOVIE_ACTOR;
DROP TABLE ACTOR;
DROP TABLE MOVIE;
DROP TABLE DIRECTOR;

create table `DIRECTOR` (
	`id` smallint NOT NULL AUTO_INCREMENT,
	`name` varchar(32) NOT NULL,
	`highest_rated_movie` varchar(32),
	UNIQUE KEY `dir_name_uniq` (`name`),
	PRIMARY KEY (`id`)
);

create table `MOVIE` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(32) NOT NULL,
	`online_stream_link` varchar(32),
	`rating` tinyint(2) NOT NULL,
	`review` varchar(4096),
	`director_id` smallint NOT NULL,
	`writer` varchar(128),
	`release_date` date NOT NULL,
	`release_year` smallint NOT NULL,
	`release_decade` smallint NOT NULL,
	`image_url` varchar(512),
	CONSTRAINT `fk_m_dir` FOREIGN KEY (`director_id`) REFERENCES `DIRECTOR` (`id`),
	PRIMARY KEY (`id`)
);
CREATE INDEX idx_movie_director ON MOVIE (director_id);
#CREATE INDEX idx_movie_date ON MOVIE (release_date);
CREATE UNIQUE INDEX idx_movie_year_name ON MOVIE (release_year, name);
#CREATE INDEX idx_movie_decade ON MOVIE (release_decade);
#above indexes on release times can be avoided if redis cache is introduced to cache all the required jsons for all year/times categories

create table `TYPE` (
	`id` tinyint(2) NOT NULL AUTO_INCREMENT,
	`type_name` varchar(16) NOT NULL,
	PRIMARY KEY (`id`)
);
insert into TYPE (id,type_name) values (1,'Thriller');
insert into TYPE (id,type_name) values (2,'Drama');
insert into TYPE (id,type_name) values (3,'Comedy');
insert into TYPE (id,type_name) values (4,'Romantic');
insert into TYPE (id,type_name) values (5,'Patriotic');
insert into TYPE (id,type_name) values (6,'Action');
insert into TYPE (id,type_name) values (7,'Family');
insert into TYPE (id,type_name) values (8,'Crime');
insert into TYPE (id,type_name) values (9,'Horror');
insert into TYPE (id,type_name) values (10,'Historical');
insert into TYPE (id,type_name) values (11,'Science Fiction');
insert into TYPE (id,type_name) values (12,'Animation');


create table `MOVIE_TYPE` (
	`movie_id` int NOT NULL,
	`type_id` tinyint(2) NOT NULL,
	UNIQUE KEY `mt_uniq` (`movie_id`,`type_id`),
	CONSTRAINT `fk_mt_mov` FOREIGN KEY (`movie_id`) REFERENCES `MOVIE` (`id`),
	CONSTRAINT `fk_mt_type` FOREIGN KEY (`type_id`) REFERENCES `TYPE` (`id`)
);
CREATE INDEX idx_mt_movie ON MOVIE_TYPE (movie_id);

create table `ACTOR` (
	`id` smallint NOT NULL AUTO_INCREMENT,
	`name` varchar(32) NOT NULL,
	`highest_rated_movie` varchar(32),
	UNIQUE KEY `actor_name_uniq` (`name`),
	PRIMARY KEY (`id`)
);

create table `MOVIE_ACTOR` (
	`movie_id` int NOT NULL,
	`actor_id` smallint NOT NULL,
	UNIQUE KEY `ma_uniq` (`movie_id`,`actor_id`),
	CONSTRAINT `fk_ma_mov` FOREIGN KEY (`movie_id`) REFERENCES `MOVIE` (`id`),
	CONSTRAINT `fk_ma_act` FOREIGN KEY (`actor_id`) REFERENCES `ACTOR` (`id`)
);
CREATE INDEX idx_ma_movie ON MOVIE_ACTOR (movie_id);
CREATE INDEX idx_ma_actor ON MOVIE_ACTOR (actor_id);