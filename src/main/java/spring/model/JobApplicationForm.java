package spring.model;

import org.springframework.web.multipart.MultipartFile;

public record JobApplicationForm(
    String firstName,
    String lastName,
    String email,
    MultipartFile cv,
    String message
) {
}
