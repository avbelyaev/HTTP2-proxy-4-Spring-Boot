#!/usr/bin/env bash

url=https://localhost:8080/upload

# -w "@curl-format.txt" - format output
# --insecure - trust unverified SSL sertificate
# -F - multipart data part
# -v - verbose output

echo "Direct requests to application at localhost:8080"
echo "--- HTTP 1.1 ---"
echo "Nginx proxy. GET"
curl -w "@curl-format.txt" ${url} --insecure -v


printf "\n\n\nCreating 50mb file named duck\n"
mkfile -n 50m duck

echo "Nginx proxy. POST. Multipart data 50mb"
curl -w "@curl-format.txt" -F "name=duck" -F "file=@duck" ${url} --insecure -v
