package test.task.systems1221.user.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.systems1221.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
