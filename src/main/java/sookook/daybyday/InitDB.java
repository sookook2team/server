package sookook.daybyday;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Member;

@Component
@RequiredArgsConstructor
public class InitDB {
    // 관리자 아이디 생성 (서버 실행 시 항상 생기는 아이디)
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void init() {
            Member member = Member.createMember("admin@naver.com", "1234", "류건");
            em.persist(member);
        }
    }
}