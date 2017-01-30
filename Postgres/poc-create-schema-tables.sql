CREATE TABLE poc.t_config
(
  domain text NOT NULL,
  name text NOT NULL,
  value text NOT NULL,
  description character varying(200) NOT NULL,
  CONSTRAINT pk_config PRIMARY KEY (domain,name)
);

ALTER TABLE poc.t_config OWNER TO poc;