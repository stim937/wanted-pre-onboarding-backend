package wanted.preonboarding.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.dto.NoticeListResponse;
import wanted.preonboarding.backend.dto.NoticeResponse;
import wanted.preonboarding.backend.dto.NoticeViewResponse;
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
	
	// 3. 채용공고 삭제
	@DeleteMapping("/api/notice/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) {
		noticeService.delete(noticeId);
		return ResponseEntity.status(HttpStatus.OK).body(noticeId + " 번 채용공고가 삭제되었습니다.");
    }
	
	// 4-1. 채용공고 전체 조회
	// 4-2. 검색기능 추가
    @GetMapping("/api/notices")
    public ResponseEntity<List<NoticeListResponse>> findAllNotices(@RequestParam(value = "search", required = false) String keyword){
    	List<Notice> notices;
    	
    	// 서치 키워드가 있으면 검색조회, 없으면 전체조회
    	if (keyword != null && !keyword.isEmpty()) {
    	    notices = noticeService.searchNoticesByKeyword(keyword);
    	} else {
    	    notices = noticeService.findAll();
    	}

    	List<NoticeListResponse> responseList = notices.stream()
    	        .map(NoticeListResponse::new)
    	        .collect(Collectors.toList());
    	
        return ResponseEntity.ok().body((responseList));
    }
    
    // 5. 채용 상세 페이지 조회(해당 회사가 올린 채용공고 추가 포함)
	@GetMapping("/api/notice/{noticeId}")
    public ResponseEntity<NoticeViewResponse> viewNotice(@PathVariable Long noticeId){
		NoticeViewResponse noticeViewResponse = noticeService.view(noticeId);
  
        return ResponseEntity.ok().body((noticeViewResponse));
    }
    
    
}
