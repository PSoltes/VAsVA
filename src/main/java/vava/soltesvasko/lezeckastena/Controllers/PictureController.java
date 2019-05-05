package vava.soltesvasko.lezeckastena.Controllers;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PictureController {

    @GetMapping(value = "/picture/{pictureName}",
            produces = MediaType.IMAGE_JPEG_VALUE)

    public ResponseEntity<byte[]> getImage(@PathVariable String pictureName) throws IOException {

        var imgFile = new ClassPathResource(pictureName);

        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}