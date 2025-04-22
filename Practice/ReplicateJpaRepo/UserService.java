import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository;

    //use as an @Autowired
    public UserService() {
        //set implementation of JpaRepository which has all methods of JpaRepository;
        //now userRepository also have same methods;
        //UserRepository interface extends JpaRepository interface, so we allow to inject implementation of JpaRepository in UserRepository;
        this.userRepository = new JpaRepoImpl();
    }

    public ArrayList<User> searchUsers(String keyword) {
        return userRepository.findAll(keyword);
    }

    public void printUsers(String keyword) {
        ArrayList<User> users = searchUsers(keyword);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
