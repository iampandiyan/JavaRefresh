package SESSION5_SOLIDPRINCIPLES.S_SINGLE_RESPONSIPILITY_PRINCIPLE;

public class UserRepository {
    void save(String name, String email) {
        System.out.println("INSERT INTO users VALUES (" + name + ", " + email + ")");
    }

}
