
-- CREATE onboarding_config table

CREATE TABLE onboarding_config (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	region_id int4 NOT NULL,
	disabled bool NOT NULL DEFAULT true,

	CONSTRAINT onboarding_config_pk PRIMARY KEY (id)
);


-- CREATE stage table

CREATE TABLE stage (
	id serial NOT NULL,
	title varchar(150) NULL,
	description text NULL,
	"order" int4 NOT NULL,
	disabled bool NOT NULL DEFAULT false,
	onboarding_config_id int4 NOT NULL,
	analytics_event_name(100) varchar NULL,

	CONSTRAINT stage_pk PRIMARY KEY (id),
	CONSTRAINT stage_fk_onboarding_config FOREIGN KEY (onboarding_config_id) REFERENCES onboarding_config(id)
);


-- CREATE element_type table

CREATE TABLE element_type (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	description text NULL,

	CONSTRAINT element_type_pk PRIMARY KEY (id),
	CONSTRAINT element_type_un UNIQUE (name)
);


-- CREATE form_element table

CREATE TABLE form_element (
	id serial NOT NULL,
	"label" varchar(100) NULL,
	placeholder varchar(100) NULL,
	type_id int4 NOT NULL,
	property varchar(100) NOT NULL,
	required bool NOT NULL DEFAULT false,
	required_text varchar(150) NULL,
	conditional bool NOT NULL DEFAULT false,
	"order" int4 NOT NULL,
	disabled bool NOT NULL DEFAULT false,
	stage_id int4 NOT NULL,
	analytics_group varchar(100) NULL,
	analytics_property varchar(100) NULL,
	conditional_element int4 NULL,

	CONSTRAINT form_element_pk PRIMARY KEY (id),
	CONSTRAINT form_element_fk_stage FOREIGN KEY (stage_id) REFERENCES stage(id),
	CONSTRAINT form_element_fk_element_type FOREIGN KEY (type_id) REFERENCES element_type(id),
	CONSTRAINT form_element_fk_form_element FOREIGN KEY (conditional_element) REFERENCES form_element(id)
);


-- CREATE form_option table

CREATE TABLE form_option (
	id serial NOT NULL,
	display_name text NOT NULL,
	value text NULL,
	img_url text NULL,
	"order" int4 NOT NULL,
	conditional_element int4 NULL,
	disabled bool NOT NULL DEFAULT false,
	form_element_id int4 NOT NULL,

	CONSTRAINT form_option_pk PRIMARY KEY (id),
	CONSTRAINT form_option_fk_form_element FOREIGN KEY (form_element_id) REFERENCES form_element(id),
	CONSTRAINT form_option_fk_form_element_conditional FOREIGN KEY (conditional_element) REFERENCES form_element(id)
);

