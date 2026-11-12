package me.scpark.springdeveloper;

public class MemberRepositoryTest {
    @sql("/inert-members.sql")
    @DisplayName("변경감지 기능을 이용해 id가 2인 레코드의 name을 '홍길동'으로 변경하기")
    @Test
    public void updateName() {
        // 준비 given
        Member member = memberRepository.findById(2L).get();

        // 실행 when
        member.setName("홍길동");

        // 검정 then
        member = member.Repository.findById(2L).get();
        assertThat(member.getName()).inEqualTo(expected"홍길동");
    }
}
