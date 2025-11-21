package spring.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import spring.model.JobApplicationForm;
import spring.service.JobApplicationService;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping(path = "/{jobId}/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> apply(
        @PathVariable String jobId,
        @ModelAttribute JobApplicationForm form
    ) throws IOException {
        MultipartFile cv = form.cv();

        if (!MediaType.APPLICATION_PDF_VALUE.equals(cv.getContentType())) {
            return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body("CV must be a PDF file");
        }

        System.out.println("Job: " + jobId);
        System.out.println("First name: " + form.firstName());
        System.out.println("Last name: " + form.lastName());
        System.out.println("Email: " + form.email());
        System.out.println("Message: " + form.message());
        System.out.println("CV Name: " + cv.getOriginalFilename());
        System.out.println("CV Size: " + cv.getSize());

        jobApplicationService.saveToPdf(cv);

        return ResponseEntity.ok("Application received");
    }
}
