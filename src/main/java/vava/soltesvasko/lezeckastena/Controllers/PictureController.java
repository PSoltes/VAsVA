package vava.soltesvasko.lezeckastena.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vava.soltesvasko.lezeckastena.Configs.FileStorageService;
import vava.soltesvasko.lezeckastena.Data.Climber;
import vava.soltesvasko.lezeckastena.Data.ClimberRepository;

@RestController
public class PictureController {

    @Autowired
    private FileStorageService fs;
    @Autowired
    private ClimberRepository cRepo;

    @PostMapping(value="/picture/upload/climber/{id}", consumes={MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public String uploadClimberPicture(@RequestPart("file") MultipartFile mf, @PathVariable("id") long id)
    {

        try {
            String filename = fs.store(mf, id);
            Optional<Climber> cl = cRepo.findById(id);
            if(cl.isPresent())
            {
                List<String> images = cl.get().getMyImages();
                    images.add(filename);
                    cl.get().setMyImages(images);
                    cRepo.save(cl.get());
                return filename;
            }
            else
            {
                return "Climber not found";
            }

        }catch(Exception e)
        {
            return "Upload failed";
        }
    }
}