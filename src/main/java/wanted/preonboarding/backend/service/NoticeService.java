package wanted.preonboarding.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import wanted.preonboarding.backend.domain.Company;
import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.dto.NoticeResponse;
import wanted.preonboarding.backend.dto.NoticeViewResponse;
import wanted.preonboarding.backend.dto.UpdateNoticeRequeset;
import wanted.preonboarding.backend.repository.CompanyRepository;
import wanted.preonboarding.backend.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	CompanyRepository companyRepository;

	// 1. 채용공고 등록 서비스
	@Transactional
	public NoticeResponse save(AddNoticeRequest request) {
		Long companyId = request.getCompany_id();
		Company company = companyRepository.findById(companyId).get();
		Notice saveNotice = noticeRepository.save(request.toEntity(company));
		
		return new NoticeResponse(saveNotice);
	}

	// 2. 채용공고 수정 서비스
	@Transactional
	public NoticeResponse update(Long noticeId, UpdateNoticeRequeset request) {
		Notice updateNotice = noticeRepository.findById(noticeId).orElseThrow(RuntimeException::new);
		updateNotice.update(request.getPosition(), request.getCompensation(), request.getContent(), request.getSkill());
		
		return new NoticeResponse(updateNotice);
	}
	
	// 3. 채용공고 삭제 서비스
	public void delete(Long noticeId) {
		noticeRepository.deleteById(noticeId);
	}
	
    // 4-1. 채용공고 전체 조회 서비스
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }
    
    // 4-2. 채용공고 검색 서비스
    public List<Notice> searchNoticesByKeyword(String keyword) {
        return noticeRepository.findAll().stream()
                .filter(notice -> {
                    Company company = notice.getCompany();
                    return notice.getPosition().contains(keyword)
                            || company.getName().contains(keyword)
                            || company.getCountry().contains(keyword)
                            || company.getRegion().contains(keyword)
                            || notice.getSkill().contains(keyword);
                })
                .collect(Collectors.toList());
    }
    
    // 5. 채용 상세 페이지 서비스
    public NoticeViewResponse view(Long noticeId) {
    	Notice notice = noticeRepository.findById(noticeId).orElseThrow(RuntimeException::new);
    	
    	return new NoticeViewResponse(notice);
    }
}
