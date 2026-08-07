package SESSION5_SOLIDPRINCIPLES.S_SINGLE_RESPONSIPILITY_PRINCIPLE;

// VIOLATION: one class doing validation, persistence, AND notification.
// A change to email wording, a change to DB schema, and a change to
// validation rules ALL require modifying this one class.
class UserService {
    void registerUser(String name, String email) {
        if (email == null || !email.contains("@")) {          // validation concern
            throw new IllegalArgumentException("Invalid email");
        }
        System.out.println("INSERT INTO users VALUES (" + name + ", " + email + ")"); // persistence concern
        System.out.println("Sending welcome email to " + email); // notification concern
    }
}
