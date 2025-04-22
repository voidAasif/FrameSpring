import java.util.ArrayList;

//Jpa repository has the template of all methods which is implemented in JpaRepoImpl;
public interface JpaRepository<T, U> {
    public ArrayList<T> findAll(String keyword);
}
