package ua.goit.dev6.images;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Controller
public class ImageController {
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Load the image from the classpath
            Resource resource = new ClassPathResource("/static/images/" + imageName);
            // Set the cache expiration time to 1 day
            CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.DAYS);
            return ResponseEntity.ok().cacheControl(cacheControl).body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
