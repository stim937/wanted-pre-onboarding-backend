package wanted.preonboarding.backend.dto;

import lombok.Getter;
import wanted.preonboarding.backend.domain.Notice;

@Getter
public class NoticeResponse {
	
	private String position;
	private int compensation;
    private String content;
    private String skill;
    
    public NoticeResponse(Notice notice) {
    	this.position = notice.getPosition();
    	this.compensation = notice.getCompensation();
    	this.content = notice.getContent();
    	this.skill = notice.getSkill();
    }
}
