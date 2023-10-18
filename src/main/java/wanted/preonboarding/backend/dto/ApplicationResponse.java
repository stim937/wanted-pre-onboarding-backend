package wanted.preonboarding.backend.dto;

import lombok.Getter;
import wanted.preonboarding.backend.domain.Application;

@Getter
public class ApplicationResponse {
	
	private Long notice_id;
	private String company_name;
	private String position;
	private int compensation;
    private String skill;
    private Long user_id;
    private String user_name;
    private String user_email;
    
    public ApplicationResponse(Application application) {
    	this.notice_id = application.getNotice().getId();
    	this.company_name = application.getNotice().getCompany().getName();
    	this.position = application.getNotice().getPosition();
    	this.compensation = application.getNotice().getCompensation();
    	this.skill = application.getNotice().getSkill();
    	this.user_id = application.getUser().getId();
    	this.user_name = application.getUser().getName();
    	this.user_email = application.getUser().getEmail();
    }
}
