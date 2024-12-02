package ba.sum.fpmoz.elearningplatform.repository;

import ba.sum.fpmoz.elearningplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);
}
