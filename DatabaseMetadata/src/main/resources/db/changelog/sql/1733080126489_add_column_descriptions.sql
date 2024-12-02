--liquibase formatted sql
--changeset avivannikov:4

COMMENT ON COLUMN ford.model IS 'Модель';

COMMENT ON COLUMN ford.year_of_release IS 'Год выпуска';

COMMENT ON COLUMN ford.engine_power IS 'Мощность двигателя';

COMMENT ON COLUMN honda.model IS 'Модель';

COMMENT ON COLUMN honda.year_of_release IS 'Год выпуска';

COMMENT ON COLUMN honda.engine_power IS 'Мощность двигателя';

COMMENT ON COLUMN bmw.model IS 'Модель';

COMMENT ON COLUMN bmw.year_of_release IS 'Год выпуска';

COMMENT ON COLUMN bmw.engine_power IS 'Мощность двигателя';

COMMENT ON COLUMN bmw.is_there_a_turn_signal IS 'Наличие поворотника';

COMMENT ON COLUMN designer.ford.first_name IS 'Имя дизайнера';

COMMENT ON COLUMN designer.ford.second_name IS 'Фамилия дизайнера';

COMMENT ON COLUMN designer.ford.year_of_birth IS 'Год рождения';

COMMENT ON COLUMN designer.bmw.first_name IS 'Имя дизайнера';

COMMENT ON COLUMN designer.bmw.second_name IS 'Фамилия дизайнера';

COMMENT ON COLUMN designer.bmw.year_of_birth IS 'Год рождения';

