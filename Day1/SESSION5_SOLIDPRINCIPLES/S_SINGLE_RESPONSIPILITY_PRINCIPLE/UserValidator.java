package SESSION5_SOLIDPRINCIPLES.S_SINGLE_RESPONSIPILITY_PRINCIPLE;

public class UserValidator {
    void validate(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}
