CREATE TABLE public.customer (
                                 id int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
                                 "name" varchar(255) NULL,
                                 email varchar(255) NULL,
                                 phone varchar(255) NULL,
                                 CONSTRAINT customer_pkey PRIMARY KEY (id)
);


CREATE TABLE public.vehicle (
                                make varchar(255) NOT NULL,
                                model varchar(255) NOT NULL,
                                registration_number varchar(255) NOT NULL,
                                customer_id int8 NOT NULL,
                                CONSTRAINT uq_make_model_resgitrationno UNIQUE (make, model, registration_number),
                                CONSTRAINT vehicle_pkey PRIMARY KEY (registration_number),
                                CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.customer(id)
);

CREATE TABLE public.service_booking (
                                        booking_date timestamptz NOT NULL,
                                        description varchar(100) NOT NULL,
                                        status varchar(25) NOT NULL,
                                        id int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
                                        vehicle_number varchar(25) NOT NULL,
                                        CONSTRAINT service_booking_pkey PRIMARY KEY (id),
                                        CONSTRAINT uq_booking_date_time UNIQUE (booking_date),
                                        CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_number) REFERENCES public.vehicle(registration_number)
);