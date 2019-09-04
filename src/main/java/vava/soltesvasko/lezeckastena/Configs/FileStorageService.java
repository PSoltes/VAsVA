package vava.soltesvasko.lezeckastena.Configs;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileStorageService{

    private final Path path;

    @Autowired
    public FileStorageService()
    {
        this.path = Paths.get("C:\\Users\\PavolSoltes\\Desktop\\VAVA_backend\\images").toAbsolutePath().normalize(); //path ku obrázkom
    }
    public String store(MultipartFile file, long id) throws Exception
    {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try
        {
            if(filename.contains(".."))
            {
                throw new Exception("invalid filename");
            }
            Path target = path.resolve("images_" + id);

                new File(target.toString()).mkdirs();
            target = target.resolve(filename); //pridame k pathu filename
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        }catch (IOException e)
        {
            throw new Exception("Could not upload file");
        }
    }

//    public Resource loadAsResource(String filename) {
//        try {
//            Path file = this.path.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            }
//
//        }
//        catch (MalformedURLException e) {
//        }
//
//        return null;
//    }

}
