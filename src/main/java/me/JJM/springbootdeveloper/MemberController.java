package me.JJM.springbootdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController<MemberService> {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.saveMember(member));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return memberService.getAllMembers;
    }
    @PostMapping("/member")
    public ResponseEntity<Member> createMember(@RequestBody Member member)
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.saveMember(member));
}