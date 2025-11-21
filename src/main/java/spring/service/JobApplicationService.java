package spring.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface JobApplicationService {

    void saveToPdf(MultipartFile file) throws IOException;
}
