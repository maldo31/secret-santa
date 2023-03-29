package com.dm.secretsanta.services;

import com.dm.secretsanta.entities.Member;
import com.dm.secretsanta.repositories.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  @Autowired
  MemberRepository memberRepository;

  public Member addMember(Member member) {
    return memberRepository.save(member);
  }

  public Member getByEmail(String email) {
    return memberRepository.findByEmailIgnoreCase(email);
  }


  public List<Member> getAllMembers() {
    return memberRepository.findAll();
  }

  @Transactional
  public void deleteMemberByEmail(String email) {
    memberRepository.deleteMemberByEmailIgnoreCase(email);
  }

  List<Member> getMemberListByEmails(List<String> emailList) {
    List<Member> memberList = new ArrayList<>();
    for (var email : emailList) {
      memberList.add(getByEmail(email));
    }
    return memberList;
  }

}
