import com.fasterxml.jackson.databind.ObjectMapper;
import com.peasch.controller.LibraryApplication;
import com.peasch.model.dto.User.AuthBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
public class RandomControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void miscelaneousTest () throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("pas");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);

//            Late user connection
        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/categories/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/categories").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/authors/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/authors").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/copies/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/copies").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/copies/book/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/copies/quantities/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/copies/quantities/available/1").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/libraries").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/libraries/1").header("Authorization", token)).andExpect(status().isOk());
    }
}
