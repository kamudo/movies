# movies
Programming Test

Simple Rest app.

You will find the complete code-base as well as a JAR which can be executed from the command like a stand alone Java application.

Here are some helpful information:

Please have a look at the Controllers in the code base for a full list of available end-points, but here are some of them:

Getting All Movies: @GET localhost:8080/movies

Getting All Comments for a movei @GET localhost:8080/movies/{id}/comments

Getting Movie Details, meaning a combined JSON of movie information and associated comments @GET localhost:8080/movieDetails/{id}

Adding a movie (need to have ROLE_ADMIN to do it): @POST localhost:8080/movies

Consumes JSON
Produces JSON and StatusCode CREATED 201 if Successful
Adding a comment(need to have ROLE_USER to do it): @POST localhost:8080/comments

Consumes JSON
Produces JSON and StatusCode CREATED 201 if Successful
To start the Application: Download a jar, and launch it with java command pointing at the jar

THe app runns in the "Tomcat" container on port 8080: localhost:8080

Postman was used to test the Restful Api.

Basic Authentication for users and their associated roles ROLE_USER and ROLE_ADMIN as per specification was added. Full set of users can be found in: movies.kamudo.com.githab.config.SecurityConfiguration.

When adding a movie one would have to be authenticated as an admin, to add comments user role is required.
