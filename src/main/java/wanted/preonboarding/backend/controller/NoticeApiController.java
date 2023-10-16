package wanted.preonboarding.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.service.NoticeService;

@RestController // JSON 형식으로 반환
public class NoticeApiController {
	
	@Autowired
	NoticeService noticeService;

	// 1.채용공고 등록 컨트롤러
	@PostMapping("/api/notice")
	public ResponseEntity<Notice> addNotice(@RequestBody AddNoticeRequest request) {
		Notice saveNotice = noticeService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveNotice);
	}

}
