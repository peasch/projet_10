import com.fasterxml.jackson.databind.ObjectMapper;
import com.peasch.controller.LibraryApplication;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.AuthBody;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "d", password = "test123")
public class BorrowingControllerTest {

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
    public void test_AddBorrowing() throws Exception {
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);
//        création d'un nouvel emprunt
        CopyWithALLDTO copy = new CopyWithALLDTO();
        UserDto user = new UserDto();
        copy.setId(20);
        user.setId(16);
        BorrowingWithAllDTO borrowing = new BorrowingWithAllDTO();
        borrowing.setCopy(copy);
        borrowing.setUser(user);
        String borrowingToString = mapper.writeValueAsString(borrowing);

//        employee connection
        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();



//        borrowing persistence
       MvcResult result= mockMvc.perform(post("/borrowings/add").header("Authorization", token)
                .content(borrowingToString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        borrowing = mapper.readValue(result.getResponse().getContentAsString(),BorrowingWithAllDTO.class);
        int borrowingId = borrowing.getId();
        borrowingToString = mapper.writeValueAsString(borrowing);

//        copy unavailability checking
        mockMvc.perform(get("/copies/isAvailable/20").header("Authorization", token))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"));

//        borrowing return
         mockMvc.perform(post("/borrowings/return/"+ borrowingId).header("Authorization", token))
                 .andExpect(status().isOk());

//         copy availability checking
        mockMvc.perform(get("/copies/isAvailable/20").header("Authorization", token))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));

//        deleting from DB
        mockMvc.perform(get("/borrowings/delete").header("Authorization", token)
                .content(borrowingToString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

        @Test
    public void test_extend_borrowing_when_expired_impossible() throws Exception{
            AuthBody body = new AuthBody();
            body.setUserName("pas");
            body.setPassword("test123");
            String jsonRequest = mapper.writeValueAsString(body);

//            Late user connection
            String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
//            Get borrowings

            mockMvc.perform(get("/borrowings").header("Authorization", token))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

//             get borrowing with id

            mockMvc.perform(get("/borrowings/2").header("Authorization", token))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

//            unextendablility borrowing for late checking
            mockMvc.perform(post("/borrowings/extend/2").header("Authorization", token))
                    .andExpect(status().isForbidden());

        }

    @Test
    public void test_extend_borrowing_possible() throws Exception{
        AuthBody body = new AuthBody();
        body.setUserName("d");
        body.setPassword("test123");
        String jsonRequest = mapper.writeValueAsString(body);
        CopyWithALLDTO copy = new CopyWithALLDTO();
        UserDto user = new UserDto();
        copy.setId(20);
        user.setId(12);
        BorrowingWithAllDTO borrowing = new BorrowingWithAllDTO();
        borrowing.setCopy(copy);
        borrowing.setUser(user);
        String borrowingToString = mapper.writeValueAsString(borrowing);

//        employee connection to add a borrowing
        String token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

         MvcResult result = mockMvc.perform(post("/borrowings/add").header("Authorization", token)
                .content(borrowingToString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        borrowing = mapper.readValue(result.getResponse().getContentAsString(),BorrowingWithAllDTO.class);
        int borrowingId = borrowing.getId();

//        User connection for borrowing extend

        body.setUserName("pas");
        body.setPassword("test123");
        String jsonRequest2 = mapper.writeValueAsString(body);
        token = "bearer " + mockMvc.perform(post("/api/auth/login").content(jsonRequest2)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

//      checking extend availability
        mockMvc.perform(post("/borrowings/extend/"+ borrowingId).header("Authorization", token))
                .andExpect(status().isOk());
        borrowingToString = mapper.writeValueAsString(borrowing);

//        deleting from db
        mockMvc.perform(get("/borrowings/delete").header("Authorization", token)
                .content(borrowingToString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
