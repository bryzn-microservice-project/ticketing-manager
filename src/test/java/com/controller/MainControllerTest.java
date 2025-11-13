package com.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[MAIN_CONTROLLER] Test Endpoints")
    public void testTicket(TestInfo testInfo) throws Exception {
        System.out.println("\n-----------Running: " + testInfo.getDisplayName() + "-----------");
        
        // POST TICKET (GENERATE AND TEST THE RECYCLE)
        String prefix = "806";
        // recycle ticket IDs after 9999
        for(int i = 1; i < 10100; i++)
        {
            int ticket = i % 9999;
            mockMvc.perform(post("/api/v1/ticket"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("%s%04d", prefix, ticket)));
        }
    }
}
