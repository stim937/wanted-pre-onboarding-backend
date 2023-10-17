package wanted.preonboarding.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import wanted.preonboarding.backend.domain.Company;
import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.dto.NoticeResponse;
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
	
	

}
