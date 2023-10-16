package wanted.preonboarding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.preonboarding.backend.domain.Company;
import wanted.preonboarding.backend.domain.Notice;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddNoticeRequest {
	
	private Long company_id;
	private String position;
	private int compensation;
    private String content;
    private String skill;
    
    public Notice toEntity(Company company) {
        return Notice.builder()
                .company(company)
                .position(position)
                .compensation(compensation)
                .content(content)
                .skill(skill)
                .build();
    }

}
