package com.ken.bookstore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.bookstore.bean.request.book.CreateBookDetailReq;
import com.ken.bookstore.bean.request.book.QueryBookDetailReq;
import com.ken.bookstore.bean.request.book.UpdateBookDetailReq;
import com.ken.bookstore.bean.response.book.BookDetailResp;
import com.ken.bookstore.service.BookDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ken
 */
@WebMvcTest(BookDetailController.class)
public class BookDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookDetailService bookDetailService;

    @BeforeEach
    public void setup() {
        when(bookDetailService.create(any()))
                .thenAnswer(i -> new BookDetailResp());
        when(bookDetailService.update(any()))
                .thenAnswer(i -> new BookDetailResp());
        when(bookDetailService.list(any()))
                .thenAnswer(i -> Collections.emptyList());
    }

    @ParameterizedTest
    @MethodSource("validCreateBookDetailRequestProvider")
    public void validCreatedTest(CreateBookDetailReq request) throws Exception {
        mockMvc.perform(post("/admin/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validCreateBookDetailRequestProvider() {
        return Stream.of(
                Arguments.of(new CreateBookDetailReq(1L, "test", "test", new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, getRandomString(255), "test", new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, getRandomString(255), "test", BigDecimal.ONE)),
                Arguments.of(new CreateBookDetailReq(1L, getRandomString(255), "test", new BigDecimal("11.11")))
        );
    }


    @ParameterizedTest
    @MethodSource("invalidCreateBookDetailRequestsProvider")
    public void invalidCreatTest(CreateBookDetailReq request) throws Exception {
        mockMvc.perform(post("/admin/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidCreateBookDetailRequestsProvider() {
        return Stream.of(
                Arguments.of(new CreateBookDetailReq(null, "test", "test", new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, null, "test", new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, "test", null, new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, "test", "test", null)),
                Arguments.of(new CreateBookDetailReq(1L, getRandomString(256), "test", new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(1L, "test", getRandomString(256), new BigDecimal("11"))),
                Arguments.of(new CreateBookDetailReq(null, null, null, null)),
                Arguments.of(new CreateBookDetailReq(1L, "test", "test", new BigDecimal("11.111"))),
                Arguments.of(new CreateBookDetailReq(1L, "test", "test", new BigDecimal("111111111.11")))
        );
    }


    @ParameterizedTest
    @MethodSource("validUpdateBookDetailRequestProvider")
    public void validUpdateTest(UpdateBookDetailReq request) throws Exception {
        mockMvc.perform(put("/admin/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validUpdateBookDetailRequestProvider() {
        return Stream.of(
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", "test", new BigDecimal("11.11"))),
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", getRandomString(255), new BigDecimal("11.11")))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUpdateBookDetailRequestsProvider")
    public void invalidUpdateTest(UpdateBookDetailReq request) throws Exception {
        mockMvc.perform(put("/admin/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidUpdateBookDetailRequestsProvider() {
        return Stream.of(
                Arguments.of(new UpdateBookDetailReq(1L, 1L, null, "test", new BigDecimal("11.11"))),
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", null, new BigDecimal("11.11"))),
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", "test", null)),
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", "test", new BigDecimal("11.111"))),
                Arguments.of(new UpdateBookDetailReq(1L, 1L, "test", getRandomString(256), new BigDecimal("11.11")))
        );
    }


    @ParameterizedTest
    @MethodSource("validQueryBookDetailRequestProvider")
    public void validQueryTest(QueryBookDetailReq request) throws Exception {
        mockMvc.perform(post("/public/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validQueryBookDetailRequestProvider() {
        return Stream.of(
                Arguments.of(new QueryBookDetailReq(1L, null, null)),
                Arguments.of(new QueryBookDetailReq(1L, "test", null)),
                Arguments.of(new QueryBookDetailReq(1L, "test", "test")),
                Arguments.of(new QueryBookDetailReq(null, "test", null)),
                Arguments.of(new QueryBookDetailReq(null, "test", "test")),
                Arguments.of(new QueryBookDetailReq(null, null, null))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidQueryBookDetailRequestsProvider")
    public void invalidQueryTest(QueryBookDetailReq request) throws Exception {
        mockMvc.perform(post("/public/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidQueryBookDetailRequestsProvider() {
        return Stream.of(
                Arguments.of(new QueryBookDetailReq(-1L, null, null)),
                Arguments.of(new QueryBookDetailReq(0L, "test", null)),
                Arguments.of(new QueryBookDetailReq(1L, getRandomString(256), null)),
                Arguments.of(new QueryBookDetailReq(null, null,  getRandomString(256)))
        );
    }


    private static String getRandomString(int length) {
        String DETAIL_CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DETAIL_CHAR_POOL.length());
            sb.append(DETAIL_CHAR_POOL.charAt(index));
        }
        return sb.toString();
    }
}
