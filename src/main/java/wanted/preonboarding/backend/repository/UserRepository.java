package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.preonboarding.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
