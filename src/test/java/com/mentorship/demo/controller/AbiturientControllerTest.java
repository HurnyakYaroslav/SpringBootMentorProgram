package com.mentorship.demo.controller;

import com.google.gson.Gson;
import com.mentorship.demo.dto.AbiturientDto;
import com.mentorship.demo.dto.DetailedAbiturientDto;
import com.mentorship.demo.service.AbiturientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AbiturientController.class)
public class AbiturientControllerTest {

    @Mock
    private AbiturientService abiturientService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        abiturientService = mock(AbiturientService.class);
        when(abiturientService.getAll()).thenReturn(Collections.singletonList(DetailedAbiturientDto.builder()
                .id(1)
                .firstName("Yaroslav")
                .lastName("Hurniak")
                .password("4Bsfrefij31FRFVEw#RFREDRG%")
                .phoneNumber("+380654828828")
                .build()));

        this.mockMvc = MockMvcBuilders.standaloneSetup(new AbiturientController(abiturientService))
                .setControllerAdvice()
                .build();
    }

    @After
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void getAll_shouldReturnOk() throws Exception {
        final ResultActions result = mockMvc.perform(
                get("/api/abiturients/")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andExpect(status().isOk());
    }

    @Test
    public void CreateAbiturient_shouldReturnCreated() throws Exception {
        Gson gson = new Gson();
        AbiturientDto request = AbiturientDto.builder()
                .firstName("Yaroslav")
                .lastName("Hurniak")
                .password("4Bsfrefij31FRFVEw#RFREDRG%")
                .phoneNumber("+380654828828")
                .build();
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/abiturients/")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(request)));
        result.andExpect(status().isCreated());
    }

    @Test
    public void forwardUrl_invalidUrl_shouldReturnNotFound() throws Exception {
        final ResultActions result = mockMvc.perform(
                get("/invalidUrl")
                        .accept(MimeTypeUtils.TEXT_HTML_VALUE));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void forwardUrl_invalidAccept_shouldReturnOk() throws Exception {
        final ResultActions result = mockMvc.perform(
                get("/api/abiturients/")
                        .accept(MimeTypeUtils.TEXT_HTML_VALUE));
        result.andExpect(status().isNotAcceptable());
    }
}