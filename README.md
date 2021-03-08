# Solution

This document highlights the most important aspects of my solution to the proposed exercise.

## Let's start with the controller

### API RESTful
To comply with API Rest principles, the application defines the API by using nouns instead of verbs (e.g. "/payments" instead of "/charge"). Also, HATEOAS has been implemented to help to "discover" which resources are available. Apart from this, Swagger is configured to document all the endpoints (you can check it in [http://localhost:8090/swagger-ui.html#/](http://localhost:8090/swagger-ui.html#/)).

### Error management
The application intercepts any exception by using the "@RestControllerAdvice" annotation. All the exceptions are controlled and properly redirected to the client by a unified interface (with ErrorDto). This management can be found in RestResponseExceptionHandler.java.

### Validations
The controller takes into account the potential errors in the inputs by using "javax.validation".

## So now we have to talk about the service layer

This is of little interest, but we could mention that the service controls some scenarios (e.g. inexistent wallet ID or insufficient balance). Besides, the mapping between entities and DTOs is made by the MapStruct library. **Note:** Be aware of the fact that MapStruct creates the implementation of the mappers when compiling.

## That was brief, let's continue with the repository layer

Here, taking into account a system with high concurrency, the application locks the entity every time we are going to modify it to assure high consistency. But we don't want to lock the entity if we are just reading it, we rather allow multiple readings at the same time. So, to allow this, two repositories have been created, one for writing and the other for reading. Then, if we are getting an entity to modify it, we consult the writing repository, which has this flag: "@Lock(LockModeType.PESSIMISTIC_WRITE)". But, if we just want to consult the entity, we use the read repository, which doesn't have any annotation. **Note:** In the read repository, we could have used the predefined method "findOne", but in this version of Spring, it doesn't return an Optional.

Worthy of note is the use of the "@RepositoryRestResource(exported = false)" annotation. With this, we avoid that Spring Data REST automatically creates endpoints for CRUD operations. But don't worry, you don't need them. There is a file called "data.sql" which populates the database with 3 wallets (ids: 1, 2, and 3).

## Last but not least, the miscellaneous section

### Lombok
I could have used Lombok to achieve a cleaner code. However, I preferred to simplify the configuration of the IDE for this exercise to avoid potential problems executing it.

### Logging
Apart from the logs defined explicitly, the application uses AspectJ to intercept every save from the repository layer to show the changes in the database.

### Testing
For unit testing, I have partially covered the functionality to demonstrate testing in controller and service layers. In particular, I tested the following methods:
- WalletController.getWallet(...)
- WalletService.charge(...)

For controllers, I used the MockMvc class. And for both layers, I used BDDMockito (a super cool way of using Mockito with BDD style).