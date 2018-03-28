# Reverse-proxy (SSL terminator)
## Traefik

Proxies requests to gateway (rq -> Traefik -> Zuul gateway -> Spring Boot Service)

Has weird looking config file `traefik.toml`

### Quick start
- Configured directly from docker-compose. This directory contains SSL configuration and custom settings file

### Requests

Let Traefik be launched at 172.24.32.58:80, :443 (and :8081 for UI), Zuul gateway be launched at :9000, and service at :8080

```
HTTP/2:
Request to Gateway require header "Host:gateway.local":
curl --http2-prior-knowledge -H Host:gateway.local https://172.24.32.58/uploader/upload -v --insecure
curl --http2-prior-knowledge -F "name=duck" -F "file=@duck50" -H Host:gateway.local https://localhost/uploader/upload --insecure -v
curl --http2-prior-knowledge -H Host:gateway.local https://172.24.32.58/uploader/sse/mono --insecure -v
curl --http2-prior-knowledge -H Host:gateway.local https://172.24.32.58/uploader/sse/flux --insecure -v (won't work, see notes on Zuul)

Direct service requests require header "Host:service.local":
curl -H Host:service.local https://172.24.32.58/upload --insecure -v
curl -H Host:service.local https://172.24.32.58/sse/flux --insecure -v

almost same for HTTP1.1
```


### Notes
- Supports HTTP/2 only with SSL enabled
- Routing is based on `Host` header, as written in `traefik.toml`
- Traefik has UI served at :8081
