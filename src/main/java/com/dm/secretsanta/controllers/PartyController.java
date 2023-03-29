package com.dm.secretsanta.controllers;

import com.dm.secretsanta.dto.AddMembersToPartyDto;
import com.dm.secretsanta.dto.CreatePartyDto;
import com.dm.secretsanta.entities.Party;
import com.dm.secretsanta.services.PartyService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "party")
public class PartyController {

  @Autowired
  PartyService partyService;

  ModelMapper modelMapper = new ModelMapper();

  @GetMapping()
  @ResponseBody
  public List<Party> getParties() {
    return partyService.getParties();
  }

  @PostMapping()
  @ResponseBody
  public Party addParty(@RequestBody CreatePartyDto createPartyDto) {
    var party = modelMapper.map(createPartyDto, Party.class);
    return partyService.addParty(party);
  }

  @PostMapping("/addMembers")
  @ResponseBody
  public Party addMembersToParty(@RequestBody AddMembersToPartyDto addMembersToPartyDto) {
   return partyService.addMembersToParty(addMembersToPartyDto.getMemberEmails(),
       addMembersToPartyDto.getPartyName());
  }

  @PostMapping("/assignMembers")
  @ResponseBody
  public ResponseEntity<?> assignMembers(@RequestParam String partyName) {
    try{
      return ResponseEntity.ok(partyService.assignMembers(partyName));
    }
    catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

  }

}
