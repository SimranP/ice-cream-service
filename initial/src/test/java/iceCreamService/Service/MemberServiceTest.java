package iceCreamService.Service;


import iceCreamService.Domain.Member;
import iceCreamService.Exception.MemberNotFoundException;
import iceCreamService.Repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void shouldAddMember() {
        Member member = new Member("20976", "Ishu", "");
        memberService.addMember(member);
        verify(memberRepository,times(1)).save(member);
    }

    @Test
    public void shouldAlotMemberATeam() throws MemberNotFoundException {
        Member member = new Member("20976", "Ishu", "");
        when(memberRepository.findById("20976")).thenReturn(java.util.Optional.ofNullable(member));
        memberService.alotTeam("20976","1234");
        verify(memberRepository,times(1)).findById("20976");
        verify(memberRepository,times(1)).save(member);
    }

    @Test
    public void shouldCallMemberRepository() {
        memberService.isValidMemberId("20976");
        verify(memberRepository,times(1)).existsById("20976");
    }

    @Test
    public void shouldReturnTrueIfMemberBelongsToTheTeam() {
        Member member = new Member("20976", "Ishu", "1234");
        when(memberRepository.findById("20976")).thenReturn(java.util.Optional.ofNullable(member));
        assertEquals(memberService.isTeamIDAndMemberIdMatch("20976","1234"),true);
    }

    @Test
    public void shouldReturnFalseIfMemberDoesNotBelongsToTheTeam() {
        assertEquals(memberService.isTeamIDAndMemberIdMatch("20976","1234"),false);
    }
}

