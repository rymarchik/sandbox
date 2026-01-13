package core.exercise.leovegas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment transfer(String senderId, String receiverId, BigDecimal amount) {
        // 1. Валидация входных данных
        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Same account transfer"); // хорошо бы создать кастомный эксепшен
        }

        // 2. Блокировка аккаунтов в строго определенном порядке для избежания Deadlock
        String firstId = senderId.compareTo(receiverId) < 0 ? senderId : receiverId;
        String secondId = senderId.compareTo(receiverId) < 0 ? receiverId : senderId;

        // В переводе участвуют два человека. Если мы заблокируем только отправителя, то в этот же момент кто-то другой может
        // начать переводить деньги получателю и изменить его баланс одновременно с нами. Мы получим кашу в данных получателя.
        accountRepository.findByIdForUpdate(firstId);
        accountRepository.findByIdForUpdate(secondId);

        Account sender = accountRepository.findById(senderId)
            .orElseThrow(() -> new RuntimeException("Sender not found")); // хорошо бы создать кастомный эксепшен
        Account receiver = accountRepository.findById(receiverId)
            .orElseThrow(() -> new RuntimeException("Receiver not found")); // хорошо бы создать кастомный эксепшен

        // 3. Бизнес-логика
        sender.debit(amount);
        receiver.credit(amount);

        // 4. Сохранение (JPA сделает dirty checking, но явный save безопаснее)
        accountRepository.save(sender);
        accountRepository.save(receiver);

        Payment payment = new Payment(senderId, receiverId, amount, LocalDateTime.now());
        return paymentRepository.save(payment);
        // Если здесь упадет, @Transactional откатит списания
    }
}
