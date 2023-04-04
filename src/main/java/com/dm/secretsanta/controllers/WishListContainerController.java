package com.dm.secretsanta.controllers;

import com.dm.secretsanta.dto.AddWishDto;
import com.dm.secretsanta.dto.GetWishesInPriceRangeRequest;
import com.dm.secretsanta.dto.WishesInPriceRangeResponse;
import com.dm.secretsanta.entities.Wish;
import com.dm.secretsanta.entities.WishListContainer;
import com.dm.secretsanta.services.WishListContainerService;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "wishListContainer")
public class WishListContainerController {

  @Autowired
  WishListContainerService wishListContainerService;

  ModelMapper modelMapper = new ModelMapper();


  @GetMapping()
  @ResponseBody
  public WishListContainer getWishList(@RequestParam String memberEmail) {
    return wishListContainerService.getWishListContainer(memberEmail);
  }

  @PostMapping("addWish")
  @ResponseBody
  public ResponseEntity<?> addWish(@RequestBody String memberEmail, List<AddWishDto> addWishDtos) {

    var wishListContainer = wishListContainerService.getWishListContainer(memberEmail);
    if (wishListContainer != null) {
      List<Wish> wishes = addWishDtos
          .stream()
          .map(addWishDto -> modelMapper.map(addWishDto, Wish.class)).toList();
      wishListContainerService.addWishToList(wishListContainer, wishes);
      return ResponseEntity.ok().body(wishListContainer.getWishList());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't find provided wish list");
  }

  @GetMapping()
  @ResponseBody
  public Map<String, List<Wish>> getWishesInPriceRange(
      @RequestBody GetWishesInPriceRangeRequest getWishesInPriceRangeDto) {
    var members = getWishesInPriceRangeDto.getMemberEmails();
    var lowerLimit = getWishesInPriceRangeDto.getLowerLimit();
    var upperLimit = getWishesInPriceRangeDto.getUpperLimit();

    var wishesMap = new WishesInPriceRangeResponse().getWishesForUser();

    wishesMap.putAll(
        wishListContainerService.getWishesInPriceRange(members, lowerLimit, upperLimit));
    return wishesMap;
  }

}
