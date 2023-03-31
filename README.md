The client-side is built with HTML, Bootstrap, CSS, and a little bit of JavaScript, while the server-side is built with Spring Boot and it is using MySQL Server 
for storing the data. To integrate the client-side with the server-side, I have used Thymeleaf and REST API.

The application consists of users who can register, login and logout. To ensure this, I have used Spring Security. There are cars added by users, mechanics, and types of
activities that can be performed in the service, which are added from the administrator. The idea is that the customer has a personal profile and can add his car or cars,
describing the problems that need to be fixed. After a car is registered, the customer can choose which mechanic is responsible for repairing his car. To make 
this choice easier, everyone has an opinion about each mechanic, which is indicated by other customers. 
Mechanics specialize in one or more things related to repairing cars.
