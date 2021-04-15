import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peasch.controller.LibraryApplication;
import com.peasch.model.dto.User.AuthBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "d", password = "test123")
public class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @BeforeEach
    public void setup() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void test_GetBook() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);

        String token = "bearer " + mockMvc.perform(post("/api/auth/login")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/books").header("Authorization", token))
                .andExpect(status().isOk());

    }

    @Test
    public void test_GetBook_ById() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);

        String token = "bearer " + mockMvc.perform(post("/api/auth/login")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/books/2").header("Authorization", token))
                .andExpect(status().isOk());

    }

    @Test
    public void test_GetBook_ById_DoesNotExist() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);

        String token = "bearer " + mockMvc.perform(post("/api/auth/login")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/books/2000").header("Authorization", token)).andExpect(status().isNotFound());

    }
}
