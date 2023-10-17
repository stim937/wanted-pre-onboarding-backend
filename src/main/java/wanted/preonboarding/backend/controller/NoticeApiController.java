package wanted.preonboarding.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.dto.NoticeResponse;
import wanted.preonboarding.backend.dto.UpdateNoticeRequeset;
import wanted.preonboarding.backend.service.NoticeService;

@RestController // JSON 형식으로 반환
@RequiredArgsConstructor
public class NoticeApiController {
	
	private final NoticeService noticeService;

	// 1.채용공고 등록 컨트롤러
	@PostMapping("/api/notice")
	public ResponseEntity<NoticeResponse> addNotice(@RequestBody AddNoticeRequest request) {
		NoticeResponse response = noticeService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
	}
	
	// 2. 채용공고 내용 수정
	@PutMapping("/api/notice/{noticeId}")
    public ResponseEntity<NoticeResponse> updateNotice(@PathVariable Long noticeId, @RequestBody UpdateNoticeRequeset request) {
		NoticeResponse response = noticeService.update(noticeId, request);
		return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
