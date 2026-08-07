package SESSION5_SOLIDPRINCIPLES.S_SINGLE_RESPONSIPILITY_PRINCIPLE;

public class UserRegistrationOrchestrator {
    private final UserValidator validator = new UserValidator();
    private final UserRepository repository =new UserRepository();
    private final NotificationService notificationService=new NotificationService();

    public void registerUser(String name, String email) {
        validator.validate(email);
        repository.save(name, email);
        notificationService.sendWelcomeEmail(email);
    }

}
