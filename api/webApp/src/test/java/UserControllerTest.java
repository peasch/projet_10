import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peasch.controller.LibraryApplication;
import com.peasch.model.dto.User.AuthBody;
import com.peasch.model.dto.User.UserWithRoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "d", password = "test123")
public class UserControllerTest {
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
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoggedInGetUsers() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);

        mockMvc.perform(post("/api/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void testbadCredentials() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test12");
        String jsonRequest = mapper.writeValueAsString(body);

        MvcResult result = mockMvc.perform(post("/api/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getErrorMessage());
    }

    @Test
    public void test_registering() throws Exception {
        UserWithRoleDTO user = new UserWithRoleDTO();
        user.setEmail("tralala@lala.fr");
        user.setFirstName("Jean");
        user.setName("Bon");
        user.setUserName("jambon");
        user.setPassword("a");
        AuthBody body = new AuthBody();
        body.setUserName("pas");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);
        String userToString = mapper.writeValueAsString(user);
        mockMvc.perform(post("/api/auth/register").content(userToString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        mockMvc.perform(get("/user/delete").header("Authorization", token).content(userToString).contentType(MediaType.APPLICATION_JSON));
    }


}
