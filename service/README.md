# Target service
## Spring Boot

Service supports requests to endpoints:
- `host:port/upload`, GET, POST Multipart
- Server-Sent Events (SSE) via WebFlux Mono at `host:port/sse/mono`, GET
- SSE via WebFlux Flux at `host:port/sse/flux`, GET

Undertow is enabled at the moment.

To use Apache Tomcat:
- Edit `pom.xml`. Remove tomcat exclusion in `spring-boot-starter-web` dependency
- Uncomment Tomcat bean in `Http2ServiceApplication.java`

To use Jetty:
- Edit `pom.xml`. Add tomcat exclusion in `spring-boot-starter-web` dependency
- Uncomment Jetty bean in `Http2ServiceApplication.java`

To switch back to Undertow, do same as for Jetty


## HTTP/2 performance comparison of embedded servers (Tomcat, Jetty, Undertow) running SpringBoot

### Quick start:

- Build jar: `mvn clean install`
- Build service image: `docker build -t service .`
- To enable SSL, uncomment `server.ssl` section in `application.yml`

### Requests:

```
HTTP/2:
curl --http2-prior-knowledge http://localhost:8080/upload -v
curl --http2-prior-knowledge -F "name=duck" -F "file=@duck" http://localhost:8080/upload -v

curl --http2-prior-knowledge http://localhost:8080/sse/mono -v
curl --http2-prior-knowledge http://localhost:8080/sse/flux -v

HTTP1.1:
curl http://localhost:8080/upload -v
curl -F "name=duck" -F "file=@duck" http://localhost:8080/upload -v

curl http://localhost:8080/sse/mono -v
curl http://localhost:8080/sse/flux -v
```

If SSL is enabled, requests look like this:
```
HTTP/2:
curl --http2-prior-knowledge https://localhost:8080/upload --insecure -v

HTTP1.1:
curl https://localhost:8080sse/mono --insecure -v
```

### Notes
- Undertow supports HTTP/2, but requires `spring-boot-starter-parent 1.5.10.RELEASE` as parent (pom)
- Webflux requires `spring-boot-starter-parent 2.0.0.RELEASE` as parent but fails to start with `1.5.10.RELEASE` as regular dependency,
so in order to use SSE with Undertow, HTTP/2 should be disabled (but maybe it works with Tomcat :) )
