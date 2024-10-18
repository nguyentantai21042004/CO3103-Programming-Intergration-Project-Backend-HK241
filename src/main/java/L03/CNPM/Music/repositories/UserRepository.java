package L03.CNPM.Music.repositories;

import L03.CNPM.Music.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm người dùng dựa trên email
    Optional<User> findByEmail(String email);

    // Tìm người dùng dựa trên username
    Optional<User> findByUsername(String username);

    // Kiểm tra xem email đã tồn tại hay chưa
    boolean existsByEmail(String email);

    // Kiểm tra xem username đã tồn tại hay chưa
    boolean existsByUsername(String username);
}
