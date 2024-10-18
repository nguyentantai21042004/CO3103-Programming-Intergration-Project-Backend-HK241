package L03.CNPM.Music.repositories;

import L03.CNPM.Music.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT o FROM User o WHERE o.isActive = true AND (:keyword IS NULL OR :keyword = '' OR " +
            "o.username LIKE %:keyword% " +  // Tìm theo username
            "OR o.email LIKE %:keyword% " +   // Tìm theo email
            "OR o.country LIKE %:keyword% " + // Tìm theo quốc gia
            "OR o.profileImage LIKE %:keyword%) ")
    Page<User> findAll(@Param("keyword") String keyword, Pageable pageable);

    // Tìm người dùng dựa trên email
    Optional<User> findByEmail(String email);

    // Tìm người dùng dựa trên username
    Optional<User> findByUsername(String username);

    // Kiểm tra xem email đã tồn tại hay chưa
    boolean existsByEmail(String email);

    // Kiểm tra xem username đã tồn tại hay chưa
    boolean existsByUsername(String username);
}
