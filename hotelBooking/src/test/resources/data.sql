DROP TABLE IF EXISTS bookings;

CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    checkInDate DATE NOT NULL,
    checkOutDate DATE NOT NULL,
    email VARCHAR(255),
    telephone VARCHAR(255) NOT NULL,
    numberOfGuests INT DEFAULT 0,
    roomType VARCHAR(255) NOT NULL,
    isPaid BOOLEAN DEFAULT false
);
INSERT INTO bookings (name, checkInDate, checkOutDate, email, telephone, isPaid, numberOfGuests, roomType)
VALUES ('Jan', '2026-03-05', '2026-10-05', 'jan@gmail.com', '1312425', TRUE, 2, 'Deluxe');

INSERT INTO bookings (name, checkInDate, checkOutDate, email, telephone, isPaid, numberOfGuests, roomType)
VALUES ('Test', '2026-03-04', '2026-12-06', 'Test@gmail.com', '353636', TRUE, 2, 'Deluxe');

