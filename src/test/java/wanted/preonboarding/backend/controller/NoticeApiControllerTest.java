package wanted.preonboarding.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import wanted.preonboarding.backend.domain.Notice;
import wanted.preonboarding.backend.dto.AddNoticeRequest;
import wanted.preonboarding.backend.repository.NoticeRepository;

@SpringBootTest
@AutoConfigureMockMvc // MockMVC
class NoticeApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper; // 직렬화(자바객체->JSON), 역직렬화 제공 클래스

	@Autowired
	private WebApplicationContext context;

	@Autowired
	NoticeRepository noticeRepository;

    @BeforeEach //모든 테스트전 디비 초기화
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        noticeRepository.deleteAll();
    }
    
    
	//1. 채용공고 등록 테스트
    @DisplayName("채용공고 등록 성공")
    @Test
    public void addNotice() throws Exception {

        //given
        final String url = "/api/notice";
        final Long company_id = 1L;
        final String position = "백엔드 주니어 개발자";
        final int compensation = 1000000;
        final String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..";
        final String skill = "Python";
    
        final AddNoticeRequest userRequest = new AddNoticeRequest(company_id, position, compensation, content, skill);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Notice> notices = noticeRepository.findAll();

        assertThat(notices.size()).isEqualTo(1);
        assertThat(notices.get(0).getCompany().getId()).isEqualTo(company_id);
        assertThat(notices.get(0).getPosition()).isEqualTo(position);
        assertThat(notices.get(0).getCompensation()).isEqualTo(compensation);
        assertThat(notices.get(0).getContent()).isEqualTo(content);
        assertThat(notices.get(0).getSkill()).isEqualTo(skill);
    }

}
