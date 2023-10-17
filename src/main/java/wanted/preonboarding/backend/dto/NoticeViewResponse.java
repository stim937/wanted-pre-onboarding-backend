package wanted.preonboarding.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import wanted.preonboarding.backend.domain.Notice;

@Getter
public class NoticeViewResponse {
	private Long notice_id;
	private String company_name;
	private String company_country;
	private String company_region;
	private String position;
	private int compensation;
    private String skill;
    private String content;
    private List<Long> other_notice_id;
    
    public NoticeViewResponse(Notice notice) {
    	this.notice_id = notice.getId();
    	this.company_name = notice.getCompany().getName();
    	this.company_country = notice.getCompany().getCountry();
    	this.company_region = notice.getCompany().getRegion();
    	this.position = notice.getPosition();
    	this.compensation = notice.getCompensation();
    	this.skill = notice.getSkill();
    	this.content = notice.getContent();   	
        this.other_notice_id = notice.getCompany().getNotice().stream()
                .map(Notice::getId)
                .collect(Collectors.toList());
    }
}
