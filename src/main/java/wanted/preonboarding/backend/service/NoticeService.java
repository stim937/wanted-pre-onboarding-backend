package wanted.preonboarding.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wanted.preonboarding.backend.domain.Company;
import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.repository.CompanyRepository;
import wanted.preonboarding.backend.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	CompanyRepository companyRepository;

	// 1. 채용공고 등록 서비스
	public Notice save(AddNoticeRequest request) {
		Long companyId = request.getCompany_id();
		Company company = companyRepository.findById(companyId).get();
		
		return noticeRepository.save(request.toEntity(company));
	}

}
