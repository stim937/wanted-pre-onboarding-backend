package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.preonboarding.backend.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
