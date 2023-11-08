CREATE TABLE customer
(
    dni            varchar(20) NOT NULL,
    full_name       varchar(50) NOT NULL,
    credit_card    varchar(20) NOT NULL,
    total_flights  int NOT NULL,
    total_lodgings int NOT NULL,
    total_tours    int NOT NULL,
    phone_number  varchar(20) NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY ( dni )
);

COMMENT ON COLUMN customer.dni IS 'Documento nacional de identidad del cliente';
COMMENT ON COLUMN customer.full_name IS 'Nombre completo del cliente';
COMMENT ON COLUMN customer.credit_card IS 'Tarjeta de crédito del cliente';
COMMENT ON COLUMN customer.total_flights IS 'Total de vuelos del cliente';
COMMENT ON COLUMN customer.total_lodgings IS 'Total de alojamientos del cliente';
COMMENT ON COLUMN customer.total_tours IS 'Total de recorridos del cliente';
COMMENT ON COLUMN customer.phone_number IS 'Número de teléfono del cliente';

CREATE TABLE fly
(
    "id_fly"           bigserial NOT NULL,
    origin_lat   decimal NOT NULL,
    origin_lng   decimal NOT NULL,
    destiny_lng  decimal NOT NULL,
    destiny_lat  decimal NOT NULL,
    origin_name  varchar(20) NOT NULL,
    destiny_name varchar(20) NOT NULL,
    aero_line varchar(20) NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT pk_fly PRIMARY KEY ( "id_fly" )
);

COMMENT ON COLUMN fly.id_fly IS 'Identificador del vuelo';
COMMENT ON COLUMN fly.origin_lat IS 'Latitud de origen del vuelo';
COMMENT ON COLUMN fly.origin_lng IS 'Longitud de origen del vuelo';
COMMENT ON COLUMN fly.destiny_lng IS 'Longitud de destino del vuelo';
COMMENT ON COLUMN fly.destiny_lat IS 'Latitud de destino del vuelo';
COMMENT ON COLUMN fly.origin_name IS 'Nombre del origen del vuelo';
COMMENT ON COLUMN fly.destiny_name IS 'Nombre del destino del vuelo';
COMMENT ON COLUMN fly.aero_line IS 'Nombre de la aerolínea';
COMMENT ON COLUMN fly.price IS 'Precio del vuelo';



CREATE TABLE hotel
(
    "id_hotel"      bigserial NOT NULL,
    name    varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    rating int NOT NULL,
    price    double precision NOT NULL,
    CONSTRAINT pk_hotel PRIMARY KEY ( "id_hotel" )
);

COMMENT ON COLUMN hotel.id_hotel IS 'Identificador del hotel';
COMMENT ON COLUMN hotel.name IS 'Nombre del hotel';
COMMENT ON COLUMN hotel.address IS 'Dirección del hotel';
COMMENT ON COLUMN hotel.rating IS 'Clasificación del hotel';
COMMENT ON COLUMN hotel.price IS 'Precio del hotel';


CREATE TABLE tour
(
    "id_tour"             bigserial NOT NULL,
    id_customer       varchar(20) NOT NULL,
    CONSTRAINT pk_tour PRIMARY KEY ( "id_tour" ),
    CONSTRAINT fk_customer FOREIGN KEY ( id_customer ) REFERENCES customer ( dni ) ON DELETE NO ACTION
);

COMMENT ON COLUMN tour.id_tour IS 'Identificador del tour';
COMMENT ON COLUMN tour.id_customer IS 'Identificador del cliente';


CREATE TABLE reservation
(
    "id_reservation"             uuid NOT NULL,
    date_reservation timestamp NOT NULL,
    date_start       date NOT NULL,
    date_end         date NULL,
    total_days       int NOT NULL,
    price            double precision not null,
    tour_id          bigint NULL,
    hotel_id         bigint NULL,
    customer_id      varchar(20) NOT NULL,
    CONSTRAINT pk_reservation PRIMARY KEY ( "id_reservation" ),
    CONSTRAINT fk_customer_r FOREIGN KEY ( customer_id ) REFERENCES customer ( dni ) ON DELETE NO ACTION ,
    CONSTRAINT fk_hotel_r FOREIGN KEY ( hotel_id ) REFERENCES hotel ( "id_hotel" ) ON DELETE NO ACTION ,
    CONSTRAINT fk_tour_r FOREIGN KEY ( tour_id ) REFERENCES tour ( "id_tour" ) ON DELETE CASCADE
);

COMMENT ON COLUMN reservation.id_reservation IS 'Identificador de la reservación';
COMMENT ON COLUMN reservation.date_reservation IS 'Fecha de la reservación';
COMMENT ON COLUMN reservation.date_start IS 'Fecha de inicio de la reservación';
COMMENT ON COLUMN reservation.date_end IS 'Fecha de fin de la reservación';
COMMENT ON COLUMN reservation.total_days IS 'Total de días de la reservación';
COMMENT ON COLUMN reservation.price IS 'Precio de la reservación';
COMMENT ON COLUMN reservation.tour_id IS 'Identificador del recorrido';
COMMENT ON COLUMN reservation.hotel_id IS 'Identificador del hotel';
COMMENT ON COLUMN reservation.customer_id IS 'Identificador del cliente';

CREATE TABLE ticket
(
    "id_ticket"           uuid NOT NULL,
    price          double precision NOT NULL,
    fly_id         bigint NOT NULL,
    customer_id    varchar(20) NOT NULL,
    departure_date timestamp NOT NULL,
    arrival_date   timestamp NOT NULL,
    purchase_date  timestamp NOT NULL,
    tour_id   bigint,
    CONSTRAINT pk_ticket PRIMARY KEY ( "id_ticket" ),
    CONSTRAINT fk_customer_t FOREIGN KEY ( customer_id ) REFERENCES customer ( dni )ON DELETE NO ACTION,
    CONSTRAINT fk_fly_t FOREIGN KEY ( fly_id ) REFERENCES fly ( "id_fly" ) ON DELETE NO ACTION,
    CONSTRAINT fk_tour_t FOREIGN KEY ( tour_id ) REFERENCES tour ( "id_tour" ) ON DELETE CASCADE
);

COMMENT ON COLUMN ticket.id_ticket IS 'Identificador del ticket';
COMMENT ON COLUMN ticket.price IS 'Precio total de los servicios';
COMMENT ON COLUMN ticket.fly_id IS 'Identificador del vuelo';
COMMENT ON COLUMN ticket.customer_id IS 'Identificador del cliente';
COMMENT ON COLUMN ticket.departure_date IS 'Fecha de la salida';
COMMENT ON COLUMN ticket.arrival_date IS 'Fecha de la llegada';
COMMENT ON COLUMN ticket.purchase_date IS 'Fecha de la compra';
COMMENT ON COLUMN ticket.tour_id IS 'Identificador del recorrido';
