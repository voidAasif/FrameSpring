
//extends JpaRepository;
//now UserRepository able to use implementation class (JpaRepoImpl) of JpaRepository;
public interface UserRepository extends JpaRepository<User, Integer> {}
