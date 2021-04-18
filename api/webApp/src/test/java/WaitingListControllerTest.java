import com.fasterxml.jackson.databind.ObjectMapper;
import com.peasch.controller.LibraryApplication;
import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.User.AuthBody;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class WaitingListControllerTest {
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
    public void test_getWaitingList() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        BookDto book = new BookDto();
        book.setId(1);
        String jsonRequest = mapper.writeValueAsString(body);

        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        MvcResult result = mockMvc.perform(get("/user/username/d").header("Authorization", token)).andExpect(status().isOk()).andReturn();
        UserDto user = mapper.readValue(result.getResponse().getContentAsString(), UserDto.class);
         mockMvc.perform(get("/waitList/add/" +book.getId()).header("Authorization", token)).andExpect(status().isForbidden());

        MvcResult waitListResult  = mockMvc.perform(get("/waitList/getWaitList/" +book.getId()).header("Authorization", token)).andExpect(status().isOk()).andReturn();
        WaitListWithAllDto waitList = mapper.readValue(waitListResult.getResponse().getContentAsString(),WaitListWithAllDto.class);

        mockMvc.perform(get("/waitList/showList/" +book.getId()).header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/waitList/user").header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/waitList/exist/"+book.getId()).header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/waitList/waitListed/"+book.getId()).header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/waitList/checkWaitList/"+book.getId()).header("Authorization", token)).andExpect(status().isOk());
    }

    @Test
    public void test_add_and_delete_WaitList() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("pas");
        body.setPassword("test123");
        BookDto book = new BookDto();
        book.setId(5);
        String jsonRequest = mapper.writeValueAsString(body);

        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/waitList/isWaitListable/"+book.getId()).header("Authorization", token)).andExpect(status().isOk());
        mockMvc.perform(get("/waitList/add/" +book.getId()).header("Authorization", token)).andExpect(status().isOk());
        System.out.println( mockMvc.perform(get("/waitList/showList/" +book.getId()).header("Authorization", token)).andExpect(status().isOk()));

        MvcResult waitListResult  = mockMvc.perform(get("/waitList/getWaitList/" +book.getId()).header("Authorization", token)).andExpect(status().isOk()).andReturn();
        WaitListWithAllDto waitList = mapper.readValue(waitListResult.getResponse().getContentAsString(),WaitListWithAllDto.class);

        mockMvc.perform(get("/waitList/delete/" +waitList.getId()).header("Authorization", token)).andExpect(status().isOk());
    }
}
