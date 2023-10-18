package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.preonboarding.backend.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
