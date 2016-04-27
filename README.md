# Sparkline Server

An HTTP server that can generate sparklines that can be used in other websites or apps. Give it some values and parameters, it will give you back a sparkline in the following supported formats:

* image/svg+xml - SVG text
* text/html - SVG text above wrapped in html tags
* ~~(image/png, image/jpeg, image/gif)~~ - not yet supported


## Run Tests

```
./gradlew build scalaTest
```

## Run The Application

By default the server will run on port _8080_.

### Developing locally

```
./gradlew build slurp
```

### Running on Docker
(TODO)

## Generating Sparklines
REST endpoint:

Path: **/api**
Method: **GET**
Query Parameters:

* **values** : comma-delimited values used for the sparkline
* **w** : width of the final SVG image
* **h** : height of the final SVG image

TODO: Future parameters may include fgColor, bgColor, strokeWidth, etc

Example:

```
target='http://localhost:8080/api?values=5,5,5,0,10,5,5,5&width=100&height=50'

### Returns SVG (default)
curl -v $target

### Returns HTML (good for embedding sparklines in iframes)
curl -v -H 'Accept: text/html' $target

### Returns PNG,JPEG,GIF image
(TODO: not yet supported)
```

