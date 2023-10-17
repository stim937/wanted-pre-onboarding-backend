package wanted.preonboarding.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩증가
    @Column(name = "notice_id", updatable = false)
	private Long id;
    
    @Column(name = "position", nullable = false)
	private String position; // 채용포지션
    
    @Column(name = "compensation", nullable = false)
	private int compensation; // 채용보상금
    
    @Column(name = "content", nullable = false)
	private String content; // 채용내용
	
    @Column(name = "skill", nullable = false)
    private String skill; // 사용기술
    
    @ManyToOne(optional=false)
    @JoinColumn(name="company_id")
    private Company company; // 공고등록 회사
    
	@Builder
    public Notice(Company company, String position, int compensation, String content, String skill) {		
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
		this.company = company;
	}
	
	//수정
	public void update(String position, int compensation, String content, String skill) {
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
    }
}
