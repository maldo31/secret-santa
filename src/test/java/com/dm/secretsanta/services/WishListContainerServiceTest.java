package com.dm.secretsanta.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.dm.secretsanta.entities.Member;
import com.dm.secretsanta.entities.Wish;
import com.dm.secretsanta.entities.WishListContainer;
import com.dm.secretsanta.repositories.WishRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WishListContainerServiceTest {

  @Mock
  WishRepository wishRepository;

  @Mock
  MemberService memberService;

  @InjectMocks
  WishListContainerService wishListContainerService;

  TestData testData;

  @BeforeEach
  void prepareData() {
    var email = "example@ab.com";
    var wish1 = Wish.builder()
        .id(0)
        .price(new BigDecimal("10.00"))
        .desc("desc")
        .link("http://abc.com")
        .name("testName1")
        .isTaken(false).build();
    var wish2 = Wish.builder()
        .id(1)
        .price(new BigDecimal("20.00"))
        .desc("desc")
        .link("http://abc.com")
        .name("testName2")
        .isTaken(false).build();
    var wish3 = Wish.builder()
        .id(2)
        .price(new BigDecimal("30.00"))
        .desc("desc")
        .link("http://abc.com")
        .name("testName3")
        .isTaken(true).build();
    var wishList = new ArrayList<>(List.of(wish1, wish2, wish3));
    var wishListContainer = WishListContainer.builder().wishList(wishList).build();
    var mockMember = mock(Member.class);
    testData = new TestData(wishListContainer, email, mockMember);
  }

  @Test
  void getWishListContainer() {
  }

  @Test
  void addWishToList() {
  }

  @Test
  void getWishesInPriceRangeShouldReturnWishListInGivenPriceRange() {
    //Given
    given(memberService.getByEmail(testData.email())).willReturn(testData.member);
    given(testData.member.getWishListContainer()).willReturn(testData.wishListContainer());
    var lowerBoundary = new BigDecimal("10.00");
    var upperBoundary = new BigDecimal("20.00");

    //When
    var filteredWishList = wishListContainerService.getWishesInPriceRange(testData.email(),
        lowerBoundary, upperBoundary);

    //Then
    Assertions.assertTrue(
        (filteredWishList.stream().allMatch(wish -> wish.getPrice().compareTo(lowerBoundary) >= 0))
            && (filteredWishList.stream()
            .allMatch(wish -> wish.getPrice().compareTo(upperBoundary) <= 0)));


  }

  private record TestData(WishListContainer wishListContainer, String email, Member member) {

  }
}