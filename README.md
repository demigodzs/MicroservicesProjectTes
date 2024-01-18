# This is Simple Microservices Project
Consist of 2 Project : surya1 & surya2
- surya1 : Service CRUD Person, consist of 2 important records, name & email. This service run on the background using docker compose.
- surya 2 : Service send email on API send email, need userId, this API will hit get person by id from service Person to get the email of the person, then send a dummy email.
