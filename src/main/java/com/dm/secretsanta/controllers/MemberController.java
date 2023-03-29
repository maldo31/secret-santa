package com.dm.secretsanta.controllers;

import com.dm.secretsanta.dto.CreateMemberDto;
import com.dm.secretsanta.entities.Member;
import com.dm.secretsanta.services.MemberService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "member")
public class MemberController {

  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  MemberService memberService;

  @GetMapping()
  @ResponseBody
  public List<Member> getMember() {
    return memberService.getAllMembers();
  }

  @PostMapping()
  @ResponseBody
  public Member addMember(@RequestBody CreateMemberDto createMemberDto) {
    var member = modelMapper.map(createMemberDto, Member.class);
    return memberService.addMember(member);
  }

  @DeleteMapping()
  @ResponseBody
  public void deleteMember(@RequestParam String email) {
    memberService.deleteMemberByEmail(email);
  }

}
