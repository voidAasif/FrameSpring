import java.util.ArrayList;
import java.util.stream.Collectors;

//this is the implementation class of JpaRepository;

//here we directly use UserRepository but spring boot achieve this in a different way;
//spring boot use Proxy classes or interfaces;
//eg, internal spring boot -> 
//JpaRepoImpl implements Proxy
//and Proxy extends UserRepository which is created by us;
//final flow JpaRepoImpl <- Proxy <- UserRepository;
public class JpaRepoImpl implements UserRepository {
    private ArrayList<User> users = new ArrayList<>();

    public JpaRepoImpl() {
        // Simulate some dummy data
        users.add(new User(1, "Aasif", true));
        users.add(new User(2, "John", false));
        users.add(new User(3, "Alice", true));
    }

    @Override
    public ArrayList<User> findAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) return users;

        return users.stream()
                .filter(u -> u.getUserName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
