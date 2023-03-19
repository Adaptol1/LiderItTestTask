CREATE TABLE IF NOT EXISTS public.teams
(
    id serial NOT NULL,
    name character varying(120) NOT NULL,
    sport_kind character varying(120),
    creation_date date NOT NULL,
    CONSTRAINT teams_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.players
(
    id serial NOT NULL,
    team_id integer,
    name character varying(120) NOT NULL,
    surname character varying(120) NOT NULL,
    patronymic character varying(120),
    birth_date date NOT NULL,
    role character varying(120),
    CONSTRAINT players_pkey PRIMARY KEY (id),
    CONSTRAINT teams_fkey FOREIGN KEY (team_id)
        REFERENCES public.teams (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
