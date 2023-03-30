package com.dm.secretsanta.services;

import com.dm.secretsanta.entities.Wish;
import com.dm.secretsanta.entities.WishListContainer;
import com.dm.secretsanta.repositories.WishRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListContainerService {

  @Autowired
  MemberService memberService;

  @Autowired
  WishRepository wishRepository;

  public WishListContainer getWishListContainer(String email) {
    return memberService.getByEmail(email).getWishListContainer();
  }

  @Transactional
  public boolean addWishToList(WishListContainer wishListContainer, List<Wish> wishes) {
    return wishListContainer.getWishList().addAll(wishes);
  }
  
  public List<Wish> getWishesInPriceRange(String email, BigDecimal lowerLimit, BigDecimal higherLimit){
    var wishes = getWishListContainer(email).getWishList();

    return wishes.stream()
        .filter(wish -> wish.getPrice().compareTo(lowerLimit) >= 0)
        .filter(wish -> wish.getPrice().compareTo(higherLimit) <= 0).toList();
  }

}
