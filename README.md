
# HTTP/2 performance comparison of embedded servers (Tomcat, Jetty, Undertow) running SpringBoot


## Undertow
### GET, Nginx(with SSL) proxy

#### Request:
```bash
curl --http2-prior-knowledge 
    -w "@curl-format.txt" 
    https://localhost/upload 
    --insecure -v
```

#### Response:
```bash
*   Trying ::1...
* TCP_NODELAY set
* Connection failed
* connect to ::1 port 443 failed: Connection refused
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 443 (#0)
* ALPN, offering h2
* ALPN, offering http/1.1
* Cipher selection: ALL:!EXPORT:!EXPORT40:!EXPORT56:!aNULL:!LOW:!RC4:@STRENGTH
* successfully set certificate verify locations:
*   CAfile: /etc/ssl/cert.pem
  CApath: none
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* TLSv1.2 (IN), TLS change cipher, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Finished (20):
* SSL connection using TLSv1.2 / ECDHE-RSA-AES256-GCM-SHA384
* ALPN, server accepted to use h2
* Server certificate:
*  subject: C=AU; ST=Some-State; O=Internet Widgits Pty Ltd
*  start date: Feb 26 07:58:56 2018 GMT
*  expire date: Feb 26 07:58:56 2019 GMT
*  issuer: C=AU; ST=Some-State; O=Internet Widgits Pty Ltd
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
* Using HTTP2, server supports multi-use
* Connection state changed (HTTP/2 confirmed)
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* Using Stream ID: 1 (easy handle 0x7ffe5500a800)
> GET /upload HTTP/2
> Host: localhost
> User-Agent: curl/7.54.0
> Accept: */*
> 
* Connection state changed (MAX_CONCURRENT_STREAMS updated)!
< HTTP/2 200 
< server: nginx/1.13.9
< date: Thu, 15 Mar 2018 18:35:06 GMT
< content-type: text/plain;charset=UTF-8
< content-length: 13
< 
* Connection #0 to host localhost left intact
Hello Spring!    time_namelookup:  0,004255
       time_connect:  0,005306
    time_appconnect:  0,060475
   time_pretransfer:  0,060729
      time_redirect:  0,000000
 time_starttransfer:  0,078632
                    ----------
         time_total:  0,078723
```


### POST, 50mb multipart data, Nginx(SSL) proxy

#### Request:
```bash
curl --http2-prior-knowledge 
    -F "name=duck" -F "file=@duck" 
    https://localhost/upload 
    --insecure -v 
    -w "@curl-format.txt"
```

#### Response:
```bash
*   Trying ::1...
* TCP_NODELAY set
* Connection failed
* connect to ::1 port 443 failed: Connection refused
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 443 (#0)
* ALPN, offering h2
* ALPN, offering http/1.1
* Cipher selection: ALL:!EXPORT:!EXPORT40:!EXPORT56:!aNULL:!LOW:!RC4:@STRENGTH
* successfully set certificate verify locations:
*   CAfile: /etc/ssl/cert.pem
  CApath: none
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* TLSv1.2 (IN), TLS change cipher, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Finished (20):
* SSL connection using TLSv1.2 / ECDHE-RSA-AES256-GCM-SHA384
* ALPN, server accepted to use h2
* Server certificate:
*  subject: C=AU; ST=Some-State; O=Internet Widgits Pty Ltd
*  start date: Feb 26 07:58:56 2018 GMT
*  expire date: Feb 26 07:58:56 2019 GMT
*  issuer: C=AU; ST=Some-State; O=Internet Widgits Pty Ltd
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
* Using HTTP2, server supports multi-use
* Connection state changed (HTTP/2 confirmed)
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* Using Stream ID: 1 (easy handle 0x7f991f006400)
> POST /upload HTTP/2
> Host: localhost
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Length: 52429093
> Content-Type: multipart/form-data; boundary=------------------------d4bb572c6eae12c5
>
* Connection state changed (MAX_CONCURRENT_STREAMS updated)!
< HTTP/2 200
< server: nginx/1.13.9
< date: Thu, 15 Mar 2018 18:35:10 GMT
< content-type: text/plain;charset=UTF-8
< content-length: 36
<
* Connection #0 to host localhost left intact
You have successfully uploaded duck!    time_namelookup:  0,005136
       time_connect:  0,005506
    time_appconnect:  0,050654
   time_pretransfer:  0,051006
      time_redirect:  0,000000
 time_starttransfer:  0,051081
                    ----------
         time_total:  4,643121
```
