package SESSION5_SOLIDPRINCIPLES.S_SINGLE_RESPONSIPILITY_PRINCIPLE;

public class SRPDemo {
    public static void main(String[] args) {
        System.out.println("=== SRP Violation example ===");
        BadUserService bad = new BadUserService();
        try {
            bad.register("Alice", "aliceexample.com"); // invalid email -> exception
        } catch (Exception e) {
            System.out.println("Bad service error: " + e.getMessage());
        }

        System.out.println("\n=== SRP Compliant example ===");
        UserValidator validator = new UserValidator();
        UserRepository repository = new UserRepository();
        NotificationService notificationService = new NotificationService();

        GoodUserService good = new GoodUserService(validator, repository, notificationService);
        good.register("Bob", "bob@example.com");
    }
}

// This class violates SRP: it handles validation, persistence and notification
class BadUserService {
    void register(String name, String email) {
        // validation
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        // persistence (rudimentary)
        System.out.println("INSERT INTO users VALUES (" + name + ", " + email + ")");

        // notification
        System.out.println("Sending welcome email to " + email);
    }
}

// This class follows SRP by delegating responsibilities to focused collaborators
class GoodUserService {
    private final UserValidator validator;
    private final UserRepository repository;
    private final NotificationService notificationService;

    GoodUserService(UserValidator validator, UserRepository repository, NotificationService notificationService) {
        this.validator = validator;
        this.repository = repository;
        this.notificationService = notificationService;
    }

    void register(String name, String email) {
        validator.validate(email);
        repository.save(name, email);
        notificationService.sendWelcomeEmail(email);
    }
}
