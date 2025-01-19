package com.netcracker.library.controller;

import com.netcracker.library.persistence.entity.Author;
import com.netcracker.library.persistence.entity.Book;
import com.netcracker.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testCreateBook() throws Exception {

        Book mockBook = new Book(1L, "Mock Book", new Author(1L, "Mock Author", null));
        when(bookService.createBook(any(Book.class))).thenReturn(mockBook);

        String jsonRequest = """
            {
                "title": "Mock Book",
                "author": {
                    "id": 1
                }
            }
            """;

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Mock Book"))
                .andExpect(jsonPath("$.author.id").value(1));
    }
}
