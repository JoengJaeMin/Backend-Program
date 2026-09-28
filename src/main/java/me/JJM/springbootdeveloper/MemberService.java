import lombok.RequiredArgsConstructor;
import me.JJM.springbootdeveloper.Member;
import me.JJM.springbootdeveloper.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MemberRepository;
    private final MemberRepository memberRepository;

    public Member saveMember (Member member) {
        return memberRepository.save(member);
    }
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
    public Member saveMember(Member member) {
        memberRepository.save(member);

    }
}