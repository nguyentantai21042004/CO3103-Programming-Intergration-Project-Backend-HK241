package L03.CNPM.Music.services.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService implements ICloudinaryService{
    private final Cloudinary cloudinary; // Injecting the Cloudinary bean

    @Override
    public Map<String, Object> uploadImage(MultipartFile multipartFile) throws Exception {
        // Convert MultipartFile to File
        File file = convertMultipartFileToFile(multipartFile);

        // Upload the image
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        // Create a new map to store only the needed values: secure_url and public_id
        Map<String, Object> filteredResult = Map.of(
                "secure_url", uploadResult.get("secure_url"),
                "public_id", uploadResult.get("public_id")
        );

        return filteredResult;
    }

    @Override
    public void deleteImage(String publicId) throws Exception {
        // Use Cloudinary's destroy method to delete the image
        Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        deleteResult.get("result");
    }

    @Override
    public File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File tempFile = File.createTempFile("temp", multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}
