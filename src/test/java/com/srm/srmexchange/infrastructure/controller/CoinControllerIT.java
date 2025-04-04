package com.srm.srmexchange.infrastructure.controller;

import com.srm.representation.CoinRequestRepresentation;
import com.srm.representation.CoinResponseRepresentation;
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
@DisplayName("CoinController Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoinControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static CoinResponseRepresentation coinResponseRepresentation1;
    private static CoinResponseRepresentation coinResponseRepresentation2;

    @Test
    @Order(1)
    @DisplayName("When create coin in path /coins should return created")
    void createCoin_shouldReturnCreated() throws Exception {
        // Given
        CoinRequestRepresentation coinRequestRepresentation1 = new CoinRequestRepresentation("test");
        CoinRequestRepresentation coinRequestRepresentation2 = new CoinRequestRepresentation("test2");

        // Then
        coinResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                createCoin(mockMvc, coinRequestRepresentation1)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(coinRequestRepresentation1.getName()))
                        .andReturn().getResponse().getContentAsString()
                , CoinResponseRepresentation.class);

        coinResponseRepresentation2 = IntegrationTestUtil.getMapper().readValue(
                createCoin(mockMvc, coinRequestRepresentation2)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id", not(coinResponseRepresentation1.getId())))
                        .andExpect(jsonPath("$.name").value(coinRequestRepresentation2.getName()))
                        .andReturn().getResponse().getContentAsString()
                , CoinResponseRepresentation.class);
    }

    @Test
    @Order(2)
    @DisplayName("When find all coin in path /coins should return ok")
    void whenFindAll_shouldReturnOk() throws Exception {
        // Then
        searchAllCoin(mockMvc)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Order(3)
    @DisplayName("When find coin by id in path /coins/{uuid} should return ok")
    void whenFindById_shouldReturnOk() throws Exception {
        // Then
        searchCoinById(mockMvc, coinResponseRepresentation1.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(coinResponseRepresentation1.getId() + ""))
                .andExpect(jsonPath("$.id", not(coinResponseRepresentation2.getId())));

        searchCoinById(mockMvc, coinResponseRepresentation2.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(coinResponseRepresentation2.getId() + ""))
                .andExpect(jsonPath("$.id", not(coinResponseRepresentation1.getId())));
    }

    @Test
    @Order(4)
    @DisplayName("When update coin in path /coins should return ok")
    void updateCoin_shouldReturnOk() throws Exception {
        // Given
        CoinRequestRepresentation coinRequestRepresentation = new CoinRequestRepresentation("testNovo");

        // Then
        coinResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                updateCoin(mockMvc, coinResponseRepresentation1.getId(), coinRequestRepresentation)
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(coinRequestRepresentation.getName()))
                        .andReturn().getResponse().getContentAsString()
                , CoinResponseRepresentation.class);
    }

    @Test
    @Order(5)
    @DisplayName("When delete coin by id in path /coins/{uuid} should return no content")
    void whenDelete_shouldReturnNoContent() throws Exception {
        // Then
        deleteCoinById(mockMvc, coinResponseRepresentation1.getId())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    @DisplayName("When find coin by id in path /coins/{uuid} should return not found")
    void whenFindById_shouldReturnNotFound() throws Exception {
        // Then
        searchCoinById(mockMvc, coinResponseRepresentation1.getId())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.GEN001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.GEN001.getDescription()));
    }

    public static ResultActions createCoin(
            MockMvc mockMvc,
            CoinRequestRepresentation coinRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/coins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(coinRequestRepresentation)));
    }

    static ResultActions searchAllCoin(MockMvc mockMvc) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/coins")
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions searchCoinById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/coins/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions updateCoin(
            MockMvc mockMvc,
            UUID uuid,
            CoinRequestRepresentation coinRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put("/coins/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(coinRequestRepresentation)));
    }

    static ResultActions deleteCoinById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete("/coins/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

}