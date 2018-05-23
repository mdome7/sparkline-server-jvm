# Developer Notes


Run the Application
----------------

You can run the application locally with the following command:

```
./gradlew run
```

Then point your browser to [http://localhost:8080/api/sparkline.png?values=5,5,5,0,10,5,5,5&w=100&h=50](http://localhost:8080/api/sparkline.png?values=5,5,5,0,10,5,5,5&w=100&h=50)



Run on Docker
----------------

During development, you can test the application and Docker image
by running it on a Docker server.

Note: You must be connected to a Docker server before running these commands.

Build and run the image with the following command:

```
./gradlew dockerBuild dockerRun
```

Stop the container with the following command:

```
./gradlew dockerStop
```