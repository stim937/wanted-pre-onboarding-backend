package wanted.preonboarding.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateNoticeRequeset {
	
	private String position;
	private Integer compensation;
    private String content;
    private String skill;
}
