package me.JJM.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRespository memberRespository;
    public List<Member> getAllMembers() {
        return memberRespository.findAll();
    }
}