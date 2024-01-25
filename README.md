Added End points to Create,Update,Search,View and Delete booking

**Create Booking API**

**Sample CURL Request**
curl --location 'http://localhost:8080/bookings' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Test",
  "checkInDate": "2024-03-05",
  "checkOutDate": "2024-02-05",
  "email": "test.doe@example.com",
  "telephone": "132435346",
  "numberOfGuests": 2,
  "roomType": "Standard",
  "isPaid": true
}'

**Sample output**
{
    "id": 1,
    "name": "Test",
    "checkInDate": "2024-03-05",
    "checkOutDate": "2024-02-05",
    "email": "test.doe@example.com",
    "telephone": "132435346",
    "numberOfGuests": 2,
    "roomType": "Standard",
    "paid": false
}

**Update Booking API**

**Request** 
curl --location --request PUT 'http://localhost:8080/bookings/1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Test UpdateUser",
  "checkInDate": "2024-03-05",
  "checkOutDate": "2024-02-05",
  "email": "testupdate.doe@example.com",
  "telephone": "132435346",
  "numberOfGuests": 2,
  "roomType": "Standard",
  "isPaid": true
}'

**Response**
{
    "id": 1,
    "name": "Test UpdateUser",
    "checkInDate": "2024-03-05",
    "checkOutDate": "2024-02-05",
    "email": "testupdate.doe@example.com",
    "telephone": "132435346",
    "numberOfGuests": 2,
    "roomType": "Standard",
    "isPaid": false
}

**Search with id**

**Request**
curl --location --request GET 'http://localhost:8080/bookings/1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Test UpdateUser",
  "checkInDate": "2024-03-05",
  "checkOutDate": "2024-02-05",
  "email": "testupdate.doe@example.com",
  "telephone": "132435346",
  "numberOfGuests": 2,
  "roomType": "Standard",
  "isPaid": true
}'

Response
{
    "id": 1,
    "name": "Test UpdateUser",
    "checkInDate": "2024-03-05",
    "checkOutDate": "2024-02-05",
    "email": "testupdate.doe@example.com",
    "telephone": "132435346",
    "numberOfGuests": 2,
    "roomType": "Standard",
    "isPaid": false
}

**View all booking details**

**Request**
curl --location --request GET 'http://localhost:8080/bookings' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Test UpdateUser",
  "checkInDate": "2024-03-05",
  "checkOutDate": "2024-02-05",
  "email": "testupdate.doe@example.com",
  "telephone": "132435346",
  "numberOfGuests": 2,
  "roomType": "Standard",
  "isPaid": true
}'

**Response**
{
    "id": 1,
    "name": "Test UpdateUser",
    "checkInDate": "2024-03-05",
    "checkOutDate": "2024-02-05",
    "email": "testupdate.doe@example.com",
    "telephone": "132435346",
    "numberOfGuests": 2,
    "roomType": "Standard",
    "isPaid": false
}

**Delete API**

**Request**
curl --location --request DELETE 'http://localhost:8080/bookings/1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Test UpdateUser",
  "checkInDate": "2024-03-05",
  "checkOutDate": "2024-02-05",
  "email": "testupdate.doe@example.com",
  "telephone": "132435346",
  "numberOfGuests": 2,
  "roomType": "Standard",
  "isPaid": true
}'

**Response**
Booking with id 1 successfully deleted


****
