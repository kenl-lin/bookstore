package com.ken.bookstore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.bookstore.bean.request.cart.AddCartItemReq;
import com.ken.bookstore.bean.request.cart.ReduceCartItemQtyReq;
import com.ken.bookstore.bean.request.cart.RemoveCartItemReq;
import com.ken.bookstore.bean.response.cart.BookCartItemTotalResp;
import com.ken.bookstore.service.BookCartService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ken
 */
@WebMvcTest(BookCartItemController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookCartItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookCartService bookCartService;

    @BeforeEach
    public void setup() {
        when(bookCartService.addCartItem(any()))
                .thenAnswer(i -> Boolean.TRUE);
        when(bookCartService.removeCartItem(any()))
                .thenAnswer(i -> Boolean.TRUE);
        when(bookCartService.reduceCartItem(any()))
                .thenAnswer(i -> Boolean.TRUE);
        when(bookCartService.list(any()))
                .thenAnswer(i -> Collections.emptyList());
        when(bookCartService.itemsDetail(any()))
                .thenAnswer(i -> new BookCartItemTotalResp());
    }


    @ParameterizedTest
    @MethodSource("validAddCartItemTestRequestProvider")
    @Order(0)
    public void validAddCartItemTest(AddCartItemReq request) throws Exception {
        mockMvc.perform(post("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validAddCartItemTestRequestProvider() {
        return Stream.of(
                Arguments.of(new AddCartItemReq(1L, 1L, 1L))
        );
    }


    @ParameterizedTest
    @MethodSource("invalidAddCartItemRequestsProvider")
    @Order(1)
    public void invalidAddCartItemTest(AddCartItemReq request) throws Exception {
        mockMvc.perform(post("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidAddCartItemRequestsProvider() {
        return Stream.of(
                Arguments.of(new AddCartItemReq(1L, -1L, -1L)),
                Arguments.of(new AddCartItemReq(1L, -1L, 1L)),
                Arguments.of(new AddCartItemReq(1L, 1L, null)),
                Arguments.of(new AddCartItemReq(1L, null, null))
        );
    }


    @ParameterizedTest
    @MethodSource("validRemoveCartItemTestRequestProvider")
    @Order(2)
    public void validRemoveCartItemTest(RemoveCartItemReq request) throws Exception {
        mockMvc.perform(delete("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validRemoveCartItemTestRequestProvider() {
        return Stream.of(
                Arguments.of(new RemoveCartItemReq(1L, 1L))
        );
    }


    @ParameterizedTest
    @MethodSource("invalidRemoveCartItemRequestsProvider")
    public void invalidRemoveCartItemTest(RemoveCartItemReq request) throws Exception {
        mockMvc.perform(delete("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidRemoveCartItemRequestsProvider() {
        return Stream.of(
                Arguments.of(new RemoveCartItemReq(1L, 0L)),
                Arguments.of(new RemoveCartItemReq(1L, -1L)),
                Arguments.of(new RemoveCartItemReq(null, -1L)),
                Arguments.of(new RemoveCartItemReq(null, 0L)),
                Arguments.of(new RemoveCartItemReq(null, null))
        );
    }


    @ParameterizedTest
    @MethodSource("validReduceCartItemTestRequestProvider")
    @Order(2)
    public void validReduceCartItemTest(ReduceCartItemQtyReq request) throws Exception {
        mockMvc.perform(put("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validReduceCartItemTestRequestProvider() {
        return Stream.of(
                Arguments.of(new ReduceCartItemQtyReq(1L, 1L, 1L))
        );
    }


    @ParameterizedTest
    @MethodSource("invalidReduceCartItemRequestsProvider")
    public void invalidReduceCartItemTest(ReduceCartItemQtyReq request) throws Exception {
        mockMvc.perform(put("/public/cart/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidReduceCartItemRequestsProvider() {
        return Stream.of(
                Arguments.of(new ReduceCartItemQtyReq(1L, 0L, null)),
                Arguments.of(new ReduceCartItemQtyReq(1L, -1L, null)),
                Arguments.of(new ReduceCartItemQtyReq(null, -1L, null)),
                Arguments.of(new ReduceCartItemQtyReq(null, -1L, -1L)),
                Arguments.of(new ReduceCartItemQtyReq(1L, -1L, -1L))
        );
    }


    @Test
    public void validListItemsTest() throws Exception {
        mockMvc.perform(get("/public/cart/items")
                        .param("userId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void inValidListItemsTest() throws Exception {
        mockMvc.perform(get("/public/cart/items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validItemsDetailTest() throws Exception {
        mockMvc.perform(get("/public/cart/detail")
                        .param("userId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void inValidItemsDetailTest() throws Exception {
        mockMvc.perform(get("/public/cart/detail")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
