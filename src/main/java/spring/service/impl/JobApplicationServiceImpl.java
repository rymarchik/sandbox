package spring.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spring.service.JobApplicationService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Override
    public void saveToPdf(MultipartFile file) throws IOException {
        Path outputPath = Path.of("CV", file.getOriginalFilename());
        Files.createDirectories(outputPath.getParent());
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
