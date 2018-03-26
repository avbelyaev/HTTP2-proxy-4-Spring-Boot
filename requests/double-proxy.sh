#!/usr/bin/env bash

# let service (spring.application.name=uploader) be accessible like this:
curl http://localhost:8080/upload -v


# when service (uploader, no SSL, port 8080) is behind Zuul (gateway, with SSL, port 443), send rq to zuul routes liek this:
curl https://localhost/uploader/upload --insecure -v


# when traefik (proxy, SSL, ports 80,443) is used as as termination proxy (outer),
# Zuul is behind it (gateway, no SSL, port 9000), configured with docker container label:
# - "traefik.frontend.rule=Host:gateway.local"
# and service is behind Zuul (service, no SSL, port 8080), send rq to Zuul (gateway) like this:
curl -H Host:gateway.local http://localhost/uploader/upload -v
# and it will go traefik -> zuul -> service


# if zuul is configured with traefik rule (container label) like this:
# - "traefik.frontend.rule=Header: RedirectMe, gateway"
# requests to zuul should have header:
curl -H RedirectMe:gateway http://localhost/uploader/upload -v
