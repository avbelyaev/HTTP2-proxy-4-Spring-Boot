package ru.belyaev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author avbelyaev
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static Logger log = LoggerFactory.getLogger(UploadController.class);

    @GetMapping()
    String home() {
        log.info("Request to hello spring has been received");
        return "Hello Spring!";
    }

    @PostMapping()
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        log.info("Attempting to receive file " + file);
        if (!file.isEmpty()) {
            try {
                File outputFile = new File(name);
                file.transferTo(outputFile);

                return "You have successfully uploaded " + name + "!";

            } catch (Exception e) {
                return "You have failed to upload " + name + " => " + e.getMessage();
            }

        } else {
            return "You have failed to upload " + name + " because the file was empty.";
        }
    }
}
