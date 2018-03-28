# Reverse-proxy (SSL terminator)
## Nginx

Proxies requests to service and can be use as Zuul replacement

### Quick start
- Build proxy image: `docker build -t jinx .`
- get Jinxed! :)


### Requests

Let Nginx be launched on 80, 443 ports. Let service be launched behind it

```
HTTP/2:
curl --http2-prior-knowledge https://localhost/upload --insecure -v
curl --http2-prior-knowledge -F "name=duck" -F "file=@duck" http://localhost/upload --insecure -v

curl --http2-prior-knowledge https://localhost/sse/mono --insecure -v
curl --http2-prior-knowledge https://localhost/sse/flux --insecure -v
```

### Notes
- Unlike Zuul, Nginx supports HTTP/2 only with SSL enabled
- Consider which protocol is used in upstream and use it in `proxy_pass`
