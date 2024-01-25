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

