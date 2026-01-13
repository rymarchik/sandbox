package core.exercise.leovegas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
class Payment {
    @Id
    private String id;

    private String senderId;

    private String receiverId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private LocalDateTime timestamp;

    public Payment(String senderId, String receiverId, BigDecimal amount, LocalDateTime timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
