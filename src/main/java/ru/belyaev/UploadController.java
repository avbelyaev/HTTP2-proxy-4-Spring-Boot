package ru.belyaev;

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

    @GetMapping()
    String home() {
        return "Hello Spring!";
    }

    @PostMapping()
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();

                return "You have successfully uploaded " + name + "!";

            } catch (Exception e) {
                return "You have failed to upload " + name + " => " + e.getMessage();
            }

        } else {
            return "You have failed to upload " + name + " because the file was empty.";
        }
    }
}
