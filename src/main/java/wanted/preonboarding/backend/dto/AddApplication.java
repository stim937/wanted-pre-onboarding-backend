package wanted.preonboarding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.preonboarding.backend.domain.Application;
import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddApplication {

	private Long user_id;
	private Long notice_id;
	
    public Application toEntity(User user, Notice notice) {
    	return Application.builder()
        		.user(user)
        		.notice(notice)
                .build();
    }
}
