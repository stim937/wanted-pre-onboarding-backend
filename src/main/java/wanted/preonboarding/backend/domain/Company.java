package wanted.preonboarding.backend.domain;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", updatable = false)
	private Long id; // 회사 아이디
    
    @Column(name = "company_name", nullable = false)
	private String name; // 이름
    
    @Column(name = "country", nullable = false)
	private String country; // 국가
    
    @Column(name = "region", nullable = false)
	private String region; // 지역
	
    @Builder //빌더 패턴 방식으로 객체 생성 가능
    public Company(String companyName, String country, String region) {
        this.name = companyName;
        this.country = country;
        this.region = region;
    }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="company", fetch = FetchType.LAZY)
    private Collection<Notice> notice;
}
