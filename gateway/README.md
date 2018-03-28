# Gateway
## Spring Cloud Netflix Zuul

Gateway redirects all requests at endpoint `host:port/uploader` to `uploader` service.
- Service name `uploader` is configured in `application.yml` at `spring.application.name` property.
- Routing to `uploader` service is configured at `application.yml` of Gateway

### Quick start:

- Build jar: `mvn clean install`
- Build gateway image: `docker build -t gateway .`
- To enable SSL, uncomment `server.ssl` section in `application.yml`

### Requests:

```
HTTP/2:
curl --http2-prior-knowledge http://localhost:9000/uploader/upload -v
curl --http2-prior-knowledge -F "name=duck" -F "file=@duck" http://localhost:9000/uploader/upload -v

HTTP1.1:
curl http://localhost:9000/uploader/sse/mono -v
curl http://localhost:9000/uploader/sse/flux -v (this one won't work)
```

If SSL is enabled, requests look like this:
```
HTTP/2:
curl --http2-prior-knowledge https://localhost:9000/uploader/upload --insecure -v

HTTP1.1:
curl https://localhost:9000/uploader/sse/mono --insecure -v
```


### Notes
- Zuul doesn't support long-living connections (sockets, SSE), see [this issue](https://github.com/Netflix/zuul/issues/376).
This means it is not guaranteed to work with Webflux Flux (but somehow it works with Webflux Mono)
