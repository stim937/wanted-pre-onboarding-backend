package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.preonboarding.backend.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
