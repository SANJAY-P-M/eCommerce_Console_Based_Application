package roles;

public class UserFactory {
    private static UserFactory instance = new UserFactory();

    private UserFactory() {
    }

    public static UserFactory getFactoryInstance() {
        return instance;
    }

    public User getInstance(User user, String role) {
        switch (role) {
            case "admin":
                return new Admin(user);
            case "customer":
                return new Customer(user);
            case "employee":
                return new Employee(user);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
