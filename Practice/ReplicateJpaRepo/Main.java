public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.printUsers("a"); // should match "Aasif" and "Alice"
    }
}
