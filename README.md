# Sparkline Server

An HTTP server that can generate sparklines that can be used in other websites or apps. Give it some values and parameters, it will give you back a sparkline in the following supported formats:

* image/svg+xml - SVG text
* text/html - SVG text above wrapped in html tags
* image/png, image/jpeg - standard image formats


## Run Tests

```
./gradlew build scalaTest
```

## Run The Application

By default the server will run on port _8080_.

### Developing locally

Build and run the code:

```
./gradlew build slurp
```

The command above will start an HTTP server listening on port 8080.  See example URLs below and try it out!


### Running on Docker
(TODO)

## Generating Sparklines
REST endpoint:

Path: **/api/sparkline.{svg|html|png|jpg}**

Method: **GET**

Query Parameters:

* **values** : comma-delimited values used for the sparkline
* **w** : width of the final SVG image
* **h** : height of the final SVG image

_Future parameters may include fgColor, bgColor, strokeWidth, etc_



Example:

```
// SVG
http://localhost:8080/api/sparkline.svg?values=5,5,5,0,10,5,5,5&w=100&h=50

// HTML
http://localhost:8080/api/sparkline.html?values=5,5,5,0,10,5,5,5&w=100&h=50

// JPEG
http://localhost:8080/api/sparkline.jpg?values=5,5,5,0,10,5,5,5&w=100&h=50

// PNG
http://localhost:8080/api/sparkline.png?values=5,5,5,0,10,5,5,5&w=100&h=50

```

