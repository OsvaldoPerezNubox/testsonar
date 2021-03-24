
-- insert onboarding_config

INSERT INTO onboarding_config (id, "name", disabled, region_id) 
VALUES(1, 'Onboarding Comercial', false, 1);

alter sequence onboarding_config_id_seq restart with 100;

-- insert stage

INSERT INTO stage (id, title, description, "order", disabled, onboarding_config_id) VALUES
(1, 'Cuentanos sobre tu negocio', 'Todos necesitan algo un poco diferente de Nubox. Veamos qué necesitas para que podamos adaptar las cosas a tus necesidades. Puedes cambiar tu información en cualquier momento desde la configuración.', 
	1, false, 1),
(2, '¿Qué te gustaría hacer en Nubox?', 'Esto es solo para comenzar, siempre puedes hacer más en el sistema.', 
	2, false, 1),
(3, '¿Cuál es tu rol en el negocio?', 'Usaremos esta información para personalizar Nubox. Puedes campbiar esta configuración cuando lo desees desde los ajustes del sistema.', 
	3, false, 1);

alter sequence stage_id_seq restart with 100;


-- insert element_type

INSERT INTO element_type (id, "name") VALUES
(1, 'INPUT'),
(2, 'SIMPLE_SELECT'),
(3, 'MULTI_SELECT');

alter sequence element_type_id_seq restart with 10;

-- insert form_element

DO $$
DECLARE 
	REQUIRED_TEXT_DEFAULT constant text := 'Debes seleccionar una opción';
	PLACEHOLDER_DEFAULT constant text :=  'Selecciona uno';
BEGIN
	INSERT INTO form_element (id, "label", placeholder, type_id, required, property, required_text, conditional, "order", stage_id) VALUES
	(11, 'Nombre empresa', 'Ingresa el nombre', 1, true, 'companyName', 'Debes ingresar el nombre de la empresa', false, 1, 1),
	(21, '¿Qué tipo de empresa tienes?', PLACEHOLDER_DEFAULT, 2, true, 'businessType', required_text_default, false, 2, 1),
	(31, '¿Cuánto tiempo llevas en el negocio?', PLACEHOLDER_DEFAULT, 2, true, 'businessTime', required_text_default, false, 3, 1),
	(41, '¿Cómo describirías a lo que se dedica tu negocio?', PLACEHOLDER_DEFAULT, 2, true, 'businessActivity', required_text_default, false, 4, 1),
	(51, '¿Qué te gustaría hacer en Nubox?', '', 3, true, 'activitiesToDo', '', false, 1, 2),
	(61, '¿Cuál es tu rol en el negocio?', '', 2, true, 'role', '', false, 1, 3),
	(71, '¿Tienes un contador que lleve la contabilidad en tu negocio?', '', 2, true, 'hasAccountant', '', true, 1, 3);

	alter sequence form_element_id_seq restart with 100;
END $$;


-- insert form_option

INSERT INTO form_option (id, form_element_id, "order", display_name, value, disabled, conditional_element, img_url) VALUES
(1, 2, 1, 'Comerciante único', 'Comerciante único', false, NULL, NULL),
(2, 2, 2, 'Comerciante y compañía', 'Comerciante y compañía', false, NULL, NULL),
(3, 2, 3, 'Sociedad responsabilidad limitada', 'Sociedad responsabilidad limitada', false, NULL, NULL),
(4, 2, 4, 'Empresa cotizada o cooperativa ', 'Empresa cotizada o cooperativa ', false, NULL, NULL),
(5, 2, 5, 'Caridad o asociación', 'Caridad o asociación', false, NULL, NULL),
(6, 2, 6, 'Empresa', 'Empresa', false, NULL, NULL),
(7, 2, 7, 'Otra', 'Otra', false, NULL, NULL),
(8, 3, 1, 'Menos de un año', 'Menos de un año', false, NULL, NULL),
(9, 3, 2, '1-2 años', '1-2 años', false, NULL, NULL),
(10, 3, 3, '3-4 años', '3-4 años', false, NULL, NULL),
(11, 4, 1, 'Servicios de alojamiento y alimentación', 'Servicios de alojamiento y alimentación', false, NULL, NULL),
(12, 4, 2, 'Servicios administrativos y de apoyo ', 'Servicios administrativos y de apoyo ', false, NULL, NULL),
(13, 5, 1, 'Envíar y monitorear facturas', 'Envíar y monitorear facturas', false, NULL, 'https://pbs.twimg.com/profile_images/1235868806079057921/fTL08u_H_400x400.png'),
(14, 5, 2, 'Organizar tus gastos', 'Organizar tus gastos', false, NULL, NULL),
(15, 5, 3, 'Envíar y monitorear impuestos de ventas', 'Envíar y monitorear impuestos de ventas', false, NULL, NULL),
(16, 6, 1, 'Dueño del negocio', 'Dueño del negocio', false, 7, NULL),
(17, 6, 2, 'Contador', 'Contador', false, NULL, NULL),
(18, 7, 1, 'Si, actualmente existe un contador', 'Si, actualmente existe un contador', false, NULL, NULL),
(19, 7, 2, 'Me gustaria conseguir uno', 'Me gustaria conseguir uno', false, NULL, NULL);

alter sequence form_option_id_seq restart with 100;

