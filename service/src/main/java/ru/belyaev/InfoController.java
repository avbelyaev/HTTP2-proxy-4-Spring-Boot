package ru.belyaev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author anton.belyaev@bostongene.com
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    private static Logger log = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/headers/{header}")
    String getHeaders(HttpServletRequest request, @PathVariable String header) {
        log.info("Request to headers observer has been received");
        String headerValue = request.getHeader(header);
        return "Header '" + header + "' value: " + headerValue;
    }
}
