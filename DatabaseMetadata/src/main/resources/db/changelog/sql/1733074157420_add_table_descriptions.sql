--liquibase formatted sql
--changeset avivannikov:3

COMMENT ON TABLE ford IS 'Машины марки Ford';

COMMENT ON TABLE honda IS 'Машины марки Honda';

COMMENT ON TABLE bmw IS 'Машины BMW';

COMMENT ON TABLE designer.ford IS 'Дизайнеры Ford';

COMMENT ON TABLE designer.bmw IS 'Дизайнеры BMW';

