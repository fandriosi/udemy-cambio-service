CREATE TABLE cambio (
  id bigint PRIMARY KEY,
  from_currency CHAR(3) NOT NULL,
  to_currency CHAR(3) NOT NULL,
  conversion_factor decimal(65,2) NOT NULL
);

create sequence cambio_sq increment by 1 start with 1;
