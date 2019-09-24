# places

This application was developed for the submission of a BackEnd Engineer position at ClickBus.

## Link's available for application testing

The application has been made available at the link below for testing:

    https://clickbus-places-test.herokuapp.com
    
It was applied to a Circle CI Continuous Integration tool through the link below, where it runs step by step the 
integration process continues, downloading the code from the git repository, compiling, running the tests (lifting the 
docker) and finally successfully completed, will make available in the Heroku service above its "stable" version.

    https://circleci.com/gh/betobrito/places
    
Below are some examples of using the application using a request tool, In my case the Postman was used.

<b>[GET] Functionality to query a specific place.</b>

    https://clickbus-places-test.herokuapp.com/places/1

<b>[POST] Functionality of listing places by name.</b>

    https://clickbus-places-test.herokuapp.com/places/list
    
Sample body for performing the above functionality post request

    {
        "name":"a"
    }

<b>[POST] Functionality of creating places.</b>

    https://clickbus-places-test.herokuapp.com/places/

Sample body for performing the above functionality post request

    {
        "name": "Jatiúca",
        "slug": "/jatiuca",
        "city": "Maceió"
    }
    
<b>[PUT] Place Editing Functionality</b>

    https://clickbus-places-test.herokuapp.com/places/
    
Sample body for performing the above functionality post request
    
    {
        "id": 1,
        "name": "Jatiúca",
        "slug": "/jatiuca",
        "city": "Maceió"
    }

## Development

To download project dependencies just run the command below:

    mvn clean install -DskipTests=true
    
To run tests or run the application you need to have raised the postgres and registry docker image to
that the project can register and be available for use:

    docker-compose -f src/main/docker/postgresql.yml up -d
    
After this process, if you want to run the api or the tests you need to generate the database using liquibase,
with the following command:

    mvn liquibase:update -Dliquibase.dropFirst=true

After the above steps api can be started via command line, execute the following command in the project root:

    ./mvnw - in case of linux system
    
   Or just:
    
    mvnw - in case of windows system
    
If you want to generate a project war just run the command below:

## Test execution

To perform the tests:

    ./mvnw verify
