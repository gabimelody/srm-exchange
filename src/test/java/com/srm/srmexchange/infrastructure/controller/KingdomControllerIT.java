package com.srm.srmexchange.infrastructure.controller;

import com.srm.representation.KingdomRequestRepresentation;
import com.srm.representation.KingdomResponseRepresentation;
import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import com.srm.srmexchange.infrastructure.controller.util.IntegrationTestUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@DisplayName("KingdomController Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KingdomControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static KingdomResponseRepresentation kingdomResponseRepresentation1;
    private static KingdomResponseRepresentation kingdomResponseRepresentation2;

    @Test
    @Order(1)
    @DisplayName("When create kingdom in path /kingdoms should return created")
    void createKingdom_shouldReturnCreated() throws Exception {
        // Given
        KingdomRequestRepresentation kingdomRequestRepresentation1 = new KingdomRequestRepresentation("test");
        KingdomRequestRepresentation kingdomRequestRepresentation2 = new KingdomRequestRepresentation("test2");

        // Then
        kingdomResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                createKingdom(mockMvc, kingdomRequestRepresentation1)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(kingdomRequestRepresentation1.getName()))
                        .andReturn().getResponse().getContentAsString()
                , KingdomResponseRepresentation.class);

        kingdomResponseRepresentation2 = IntegrationTestUtil.getMapper().readValue(
                createKingdom(mockMvc, kingdomRequestRepresentation2)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id", not(kingdomResponseRepresentation1.getId())))
                        .andExpect(jsonPath("$.name").value(kingdomRequestRepresentation2.getName()))
                        .andReturn().getResponse().getContentAsString()
                , KingdomResponseRepresentation.class);
    }

    @Test
    @Order(2)
    @DisplayName("When find all kingdom in path /kingdoms should return ok")
    void whenFindAll_shouldReturnOk() throws Exception {
        // Then
        searchAllKingdom(mockMvc)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Order(3)
    @DisplayName("When find kingdom by id in path /kingdoms/{uuid} should return ok")
    void whenFindById_shouldReturnOk() throws Exception {
        // Then
        searchKingdomById(mockMvc, kingdomResponseRepresentation1.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kingdomResponseRepresentation1.getId() + ""))
                .andExpect(jsonPath("$.id", not(kingdomResponseRepresentation2.getId())));

        searchKingdomById(mockMvc, kingdomResponseRepresentation2.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kingdomResponseRepresentation2.getId() + ""))
                .andExpect(jsonPath("$.id", not(kingdomResponseRepresentation1.getId())));
    }

    @Test
    @Order(4)
    @DisplayName("When update kingdom in path /kingdoms should return ok")
    void updateKingdom_shouldReturnOk() throws Exception {
        // Given
        KingdomRequestRepresentation kingdomRequestRepresentation = new KingdomRequestRepresentation("testNovo");

        // Then
        kingdomResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                updateKingdom(mockMvc, kingdomResponseRepresentation1.getId(), kingdomRequestRepresentation)
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(kingdomRequestRepresentation.getName()))
                        .andReturn().getResponse().getContentAsString()
                , KingdomResponseRepresentation.class);
    }

    @Test
    @Order(5)
    @DisplayName("When delete kingdom by id in path /kingdoms/{uuid} should return no content")
    void whenDelete_shouldReturnNoContent() throws Exception {
        // Then
        deleteKingdomById(mockMvc, kingdomResponseRepresentation1.getId())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    @DisplayName("When find kingdom by id in path /kingdoms/{uuid} should return not found")
    void whenFindById_shouldReturnNotFound() throws Exception {
        // Then
        searchKingdomById(mockMvc, kingdomResponseRepresentation1.getId())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.GEN001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.GEN001.getDescription()));
    }

    public static ResultActions createKingdom(
            MockMvc mockMvc,
            KingdomRequestRepresentation kingdomRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/kingdoms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(kingdomRequestRepresentation)));
    }

    static ResultActions searchAllKingdom(MockMvc mockMvc) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/kingdoms")
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions searchKingdomById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/kingdoms/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions updateKingdom(
            MockMvc mockMvc,
            UUID uuid,
            KingdomRequestRepresentation kingdomRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put("/kingdoms/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(kingdomRequestRepresentation)));
    }

    static ResultActions deleteKingdomById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete("/kingdoms/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

}