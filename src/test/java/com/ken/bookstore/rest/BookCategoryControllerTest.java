package com.ken.bookstore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.bookstore.bean.request.category.CreateBookCategoryReq;
import com.ken.bookstore.bean.request.category.QueryBookCategoryReq;
import com.ken.bookstore.bean.request.category.UpdateBookCategoryReq;
import com.ken.bookstore.bean.response.category.BookCategoryResp;
import com.ken.bookstore.service.BookCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(BookCategoryController.class)
public class BookCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookCategoryService bookCategoryService;

    @BeforeEach
    public void setup() {
        when(bookCategoryService.create(any()))
                .thenAnswer(i -> new BookCategoryResp());
        when(bookCategoryService.update(any()))
                .thenAnswer(i -> new BookCategoryResp());
        when(bookCategoryService.list(any()))
                .thenAnswer(i -> Collections.emptyList());
    }

    @ParameterizedTest
    @MethodSource("validCreateBookCategoryRequestProvider")
    public void validCreatedTest(CreateBookCategoryReq request) throws Exception {
        mockMvc.perform(post("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validCreateBookCategoryRequestProvider() {
        return Stream.of(
                Arguments.of(new CreateBookCategoryReq("test")),
                Arguments.of(new CreateBookCategoryReq("test1"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCreateBookCategoryRequestsProvider")
    public void invalidCreatTest(CreateBookCategoryReq request) throws Exception {
        mockMvc.perform(post("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidCreateBookCategoryRequestsProvider() {
        return Stream.of(
                Arguments.of(new CreateBookCategoryReq(getRandomString(256))),
                Arguments.of(new CreateBookCategoryReq(null))
        );
    }


    @ParameterizedTest
    @MethodSource("validUpdateBookCategoryRequestProvider")
    public void validUpdateTest(UpdateBookCategoryReq request) throws Exception {
        mockMvc.perform(put("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validUpdateBookCategoryRequestProvider() {
        return Stream.of(
                Arguments.of(new UpdateBookCategoryReq(1L, getRandomString(255))),
                Arguments.of(new UpdateBookCategoryReq(1L, "testtest"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUpdateBookCategoryRequestsProvider")
    public void invalidUpdateTest(UpdateBookCategoryReq request) throws Exception {
        mockMvc.perform(put("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidUpdateBookCategoryRequestsProvider() {
        return Stream.of(
                Arguments.of(new UpdateBookCategoryReq(1L, getRandomString(256))),
                Arguments.of(new UpdateBookCategoryReq(null, getRandomString(255))),
                Arguments.of(new UpdateBookCategoryReq(null, null)),
                Arguments.of(new UpdateBookCategoryReq(-1L, "testtest")),
                Arguments.of(new UpdateBookCategoryReq(0L, "testtest"))
        );
    }


    @ParameterizedTest
    @MethodSource("validQueryBookCategoryRequestProvider")
    public void validQueryTest(QueryBookCategoryReq request) throws Exception {
        mockMvc.perform(post("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> validQueryBookCategoryRequestProvider() {
        return Stream.of(
                Arguments.of(new QueryBookCategoryReq("testtest"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidQueryBookCategoryRequestsProvider")
    public void invalidQueryTest(QueryBookCategoryReq request) throws Exception {
        mockMvc.perform(post("/admin/book/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidQueryBookCategoryRequestsProvider() {
        return Stream.of(
                Arguments.of(new QueryBookCategoryReq(getRandomString(256))),
                Arguments.of(new QueryBookCategoryReq(null))
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
