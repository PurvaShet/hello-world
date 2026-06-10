INSERT INTO public.customer
(id, "name", email, phone)
VALUES(1, 'Purva', 'purva.shet@gmail.com', '987654321');
INSERT INTO public.customer
(id, "name", email, phone)
VALUES(2, 'Pooja', 'pooja.shet@gmail.com', '976543218');
INSERT INTO public.customer
(id, "name", email, phone)
VALUES(3, 'Aditi', 'aditi.shetye@gmail.com', '965432178');
INSERT INTO public.customer
(id, "name", email, phone)
VALUES(4, 'Shruti', 'aditi.shetgaonkar@gmail.com', '983214675');


INSERT INTO public.vehicle
(make, model, registration_number, customer_id)
VALUES('Honda', 'City', 'GA 01 GH 1234', 1);
INSERT INTO public.vehicle
(make, model, registration_number, customer_id)
VALUES('Maruti', 'Brezza', 'GA 01 GH 1235', 2);
INSERT INTO public.vehicle
(make, model, registration_number, customer_id)
VALUES('Maruti', 'Santro', 'GA 01 GH 1236', 3);

INSERT INTO public.service_booking
(booking_date, description, status, id, vehicle_number)
VALUES('2026-06-06 22:30:00.000', 'Tyre repair', 'IN_PROGRESS', 4, 'GA 01 GH 1236');
INSERT INTO public.service_booking
(booking_date, description, status, id, vehicle_number)
VALUES('2026-06-05 22:30:00.000', 'Routine Servicing', 'COMPLETED', 5, 'GA 01 GH 1236');
INSERT INTO public.service_booking
(booking_date, description, status, id, vehicle_number)
VALUES('2026-06-06 20:30:00.000', 'AC repair', 'SCHEDULED', 2, 'GA 01 GH 1235');
INSERT INTO public.service_booking
(booking_date, description, status, id, vehicle_number)
VALUES('2026-06-06 18:30:00.000', 'Tyre pucture', 'SCHEDULED', 1, 'GA 01 GH 1234');
INSERT INTO public.service_booking
(booking_date, description, status, id, vehicle_number)
VALUES('2026-06-06 16:30:00.000', 'Tyre repair', 'SCHEDULED', 6, 'GA 01 GH 1234');