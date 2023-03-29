package com.dm.secretsanta.services;

import com.dm.secretsanta.entities.Member;
import com.dm.secretsanta.entities.Party;
import com.dm.secretsanta.repositories.PartyRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

  @Autowired
  PartyRepository partyRepository;

  @Autowired
  MemberService memberService;

  public List<Party> getParties() {
    return partyRepository.findAll();
  }

  public Party addParty(Party party) {
    return partyRepository.save(party);
  }

  @Transactional
  public void deleteParty(String name) {
    partyRepository.deletePartyByName(name);
  }

  public Party getPartyByName(String name) {
    return partyRepository.findPartyByName(name);
  }

  @Transactional
  public Party addMembersToParty(List<String> memberEmails, String partyName) {
    var membersToAdd = memberService.getMemberListByEmails(memberEmails);
    var partyToEdit = getPartyByName(partyName);
    return addMembersToParty(membersToAdd, partyToEdit);
  }

  @Transactional
  private Party addMembersToParty(List<Member> members, Party party) {
    party.getUserSet().addAll(members);
    return party;
  }

  @Transactional
  public Party assignMembers(String partyName) {
    var partyToAssign = getPartyByName(partyName);
    var membersList = new ArrayList<>(partyToAssign.getUserSet());

    if (membersList.size() >= 2) {
      return getMappedMembers(partyToAssign, membersList);
    }
    throw new IllegalArgumentException("Party must have at least 2 members");
  }

  @Transactional
  private Party getMappedMembers(Party partyToAssign, ArrayList<Member> membersList) {
    var memberAssignedToMemberMap = partyToAssign.getAssignedUsers();
    shuffleMembersAndAssignToNextOne(membersList, memberAssignedToMemberMap);
    return partyToAssign;
  }

  @Transactional
  private void shuffleMembersAndAssignToNextOne(ArrayList<Member> membersList,
      Map<Member, Member> memberAssignedToMemberMap) {
    Collections.shuffle(membersList);

    int currentIndex;
    for (var member : membersList) {
      currentIndex = membersList.indexOf(member);
      if (currentIndex + 1 < membersList.size()) {
        memberAssignedToMemberMap.put(member, membersList.get(currentIndex + 1));
        continue;
      }
      memberAssignedToMemberMap.put(member, membersList.get(0));
      break;
    }
  }
}
