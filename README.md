# Simple Java Springboot Microservices Project
Consist of 2 Project : surya1 & surya2. This project using Postgresql Database.
- surya1 : Service CRUD Person, consist of several records like userId, name, and email. This service run on the background using docker compose.
- surya2 : Service send email on API send email and need userId for the request, this API will hit get person by id from service Person to get the email of the person, then send a dummy email.
