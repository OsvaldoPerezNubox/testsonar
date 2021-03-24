
-- CREATE onboarding_config table

CREATE TABLE onboarding_process (
	id serial NOT NULL,
	company_id int8 NOT NULL,
	user_id uuid NOT NULL,
	completed bool NOT NULL DEFAULT false,
	"data" jsonb NULL,
	created_date timestamptz(0) NOT NULL DEFAULT now(),
	modified_date timestamptz(0) NULL,
	deleted bool NOT NULL DEFAULT false,
	onboarding_config_id int4 NOT NULL,
	
	CONSTRAINT onboarding_status_pk PRIMARY KEY (id),
	CONSTRAINT onboarding_process_un UNIQUE (company_id),
	CONSTRAINT onboarding_process_fk_onboarding_config FOREIGN KEY (onboarding_config_id) REFERENCES onboarding_config(id)
);
