package L03.CNPM.Music.services.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface ICloudinaryService {
    Map<String, Object> uploadImage(MultipartFile file) throws Exception;
    void deleteImage(String publicId) throws Exception;
    File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception;
}
