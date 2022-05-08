package by.bsuir.softcompony;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ContextLoadsTests {

    private static final String COMPANY_NAME = "SoftLine";
    private static final String EMAIL = "Почта";
    private static final String PASSWORD = "Пароль";
    private static final String VACANCIES = "Вакансии";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoadsHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(COMPANY_NAME)));
    }

    @Test
    public void contextLoadsLogin() throws Exception {
        this.mockMvc.perform(get("/sign-in"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(EMAIL)))
                .andExpect(content().string(containsString(PASSWORD)));
    }


    @Test
    public void contextLoadsVacancies() throws Exception {
        this.mockMvc.perform(get("/vacancies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(VACANCIES)));
    }
}

