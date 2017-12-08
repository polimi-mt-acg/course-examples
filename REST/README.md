# REST Web Application: PhotoBook
This Maven project contains a simple REST web application to upload / download /
update image files to/from a server.

## Dependencies
- [Jersey](https://jersey.github.io/) 2.25.1 (a JAX-RS implementation)
- [Apache Common IO](https://commons.apache.org/proper/commons-io/) 2.5 (utility library)

## Tested configuration
- JDK 1.8.x
- [Glassfish](https://javaee.github.io/glassfish/) 4.1.0

## Usage
In this section some examples to test the REST endpoints are provided.

### /photobook
*text/plain*
```sh
$ curl http://localhost:8080/REST-0.1/rest/photobook
fileName: github, format: png, resolution: 512.0x512.0
fileName: google, format: png, resolution: 172.0x450.0
fileName: microsoft, format: png, resolution: 357.0x468.0
```

*application/json*
```sh
$ curl -H "Accept:application/json" http://localhost:8080/REST-0.1/rest/photobook
{"pics":[{"baseName":"github","format":"png","width":512.0,"height":512.0},{"baseName":"google","format":"png","width":450.0,"height":172.0},{"baseName":"microsoft","format":"png","width":468.0,"height":357.0}]}%
```

