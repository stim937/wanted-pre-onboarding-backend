package wanted.preonboarding.backend.dto;

import lombok.Getter;
import wanted.preonboarding.backend.domain.Notice;

@Getter
public class NoticeListResponse {
	private Long notice_id;
	private String company_name;
	private String company_country;
	private String company_region;
	private String position;
	private int compensation;
    private String skill;
    
    public NoticeListResponse(Notice notice) {
    	this.notice_id = notice.getId();
    	this.company_name = notice.getCompany().getName();
    	this.company_country = notice.getCompany().getCountry();
    	this.company_region = notice.getCompany().getRegion();
    	this.position = notice.getPosition();
    	this.compensation = notice.getCompensation();
    	this.skill = notice.getSkill();
    }
}