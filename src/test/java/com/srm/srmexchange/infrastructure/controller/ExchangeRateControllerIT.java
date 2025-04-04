package com.srm.srmexchange.infrastructure.controller;

import com.srm.representation.CoinRequestRepresentation;
import com.srm.representation.CoinResponseRepresentation;
import com.srm.representation.ExchangeRateRequestRepresentation;
import com.srm.representation.ExchangeRateResponseRepresentation;
import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import com.srm.srmexchange.infrastructure.controller.util.IntegrationTestUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@DisplayName("ExchangeRateController Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeRateControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static ExchangeRateRequestRepresentation exchangeRateRequestRepresentation1;
    private static ExchangeRateRequestRepresentation exchangeRateRequestRepresentation2;

    private static ExchangeRateResponseRepresentation exchangeRateResponseRepresentation1;
    private static ExchangeRateResponseRepresentation exchangeRateResponseRepresentation2;

    @Test
    @Order(1)
    @DisplayName("When create exchange rate in path /exchange-rates should return created")
    void createExchangeRate_shouldReturnCreated() throws Exception {
        // Given
        UUID idCoinTo1 = getIdCoin();
        UUID idCoinTo2 = getIdCoin();
        UUID idCoinFrom = getIdCoin();

        exchangeRateRequestRepresentation1 = new ExchangeRateRequestRepresentation(
                idCoinTo1, idCoinFrom, BigDecimal.ONE);

        exchangeRateRequestRepresentation2 = new ExchangeRateRequestRepresentation(
                idCoinTo2, idCoinFrom, BigDecimal.ONE);

        // Then
        exchangeRateResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                createExchangeRate(mockMvc, exchangeRateRequestRepresentation1)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.coinTo.id").value(idCoinTo1.toString()))
                        .andExpect(jsonPath("$.coinFrom.id").value(idCoinFrom.toString()))
                        .andReturn().getResponse().getContentAsString()
                , ExchangeRateResponseRepresentation.class);

        exchangeRateResponseRepresentation2 = IntegrationTestUtil.getMapper().readValue(
                createExchangeRate(mockMvc, exchangeRateRequestRepresentation2)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.coinTo.id").value(idCoinTo2.toString()))
                        .andExpect(jsonPath("$.coinFrom.id").value(idCoinFrom.toString()))
                        .andReturn().getResponse().getContentAsString()
                , ExchangeRateResponseRepresentation.class);
    }

    @Test
    @Order(2)
    @DisplayName("When create exchange rate in path /exchange-rates should return conflict")
    void createExchangeRate_shouldReturnConflict() throws Exception {
        // Then
        createExchangeRate(mockMvc, exchangeRateRequestRepresentation1)
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.EXC001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.EXC001.getDescription()));
    }

    @Test
    @Order(3)
    @DisplayName("When create exchange rate in path /exchange-rates should return bad request")
    void createExchangeRate_shouldReturnBadRequest() throws Exception {
        // Given
        UUID idCoin = getIdCoin();
        ExchangeRateRequestRepresentation exchangeRateRequestRepresentation = new ExchangeRateRequestRepresentation(
                idCoin, idCoin, BigDecimal.ONE);

        // Then
        createExchangeRate(mockMvc, exchangeRateRequestRepresentation)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.EXC002.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.EXC002.getDescription()));
    }

    @Test
    @Order(4)
    @DisplayName("When find all exchange rate in path /exchange-rates should return ok")
    void whenFindAll_shouldReturnOk() throws Exception {
        // Then
        searchAllExchangeRate(mockMvc)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Order(5)
    @DisplayName("When find exchange rate by id in path /exchange-rates/{uuid} should return ok")
    void whenFindById_shouldReturnOk() throws Exception {
        // Then
        searchExchangeRateById(mockMvc, exchangeRateResponseRepresentation1.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exchangeRateResponseRepresentation1.getId() + ""))
                .andExpect(jsonPath("$.id", not(exchangeRateResponseRepresentation2.getId())));

        searchExchangeRateById(mockMvc, exchangeRateResponseRepresentation2.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exchangeRateResponseRepresentation2.getId() + ""))
                .andExpect(jsonPath("$.id", not(exchangeRateResponseRepresentation1.getId())));
    }

    @Test
    @Order(6)
    @DisplayName("When update exchange rate in path /exchange-rates should return ok")
    void updateExchangeRate_shouldReturnOk() throws Exception {
        // Given
        exchangeRateRequestRepresentation1.setVlRate(BigDecimal.TEN);

        // Then
        exchangeRateResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                updateExchangeRate(mockMvc, exchangeRateResponseRepresentation1.getId(), exchangeRateRequestRepresentation1)
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.vlRate").value(BigDecimal.TEN))
                        .andReturn().getResponse().getContentAsString()
                , ExchangeRateResponseRepresentation.class);
    }

    @Test
    @Order(7)
    @DisplayName("When create exchange rate in path /exchange-rates should return conflict")
    void updateExchangeRate_shouldReturnConflict() throws Exception {
        // Then
        updateExchangeRate(mockMvc, exchangeRateResponseRepresentation1.getId(), exchangeRateRequestRepresentation2)
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.EXC001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.EXC001.getDescription()));
    }

    @Test
    @Order(8)
    @DisplayName("When create exchange rate in path /exchange-rates should return bad request")
    void updateExchangeRate_shouldReturnBadRequest() throws Exception {
        // Given
        UUID idCoin = getIdCoin();
        ExchangeRateRequestRepresentation exchangeRateRequestRepresentation = new ExchangeRateRequestRepresentation(
                idCoin, idCoin, BigDecimal.ONE);

        // Then
        updateExchangeRate(mockMvc, exchangeRateResponseRepresentation1.getId(), exchangeRateRequestRepresentation)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.EXC002.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.EXC002.getDescription()));
    }

    @Test
    @Order(9)
    @DisplayName("When delete exchange rate by id in path /exchange-rates/{uuid} should return no content")
    void whenDelete_shouldReturnNoContent() throws Exception {
        // Then
        deleteExchangeRateById(mockMvc, exchangeRateResponseRepresentation1.getId())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(10)
    @DisplayName("When find exchange rate by id in path /exchange-rates/{uuid} should return not found")
    void whenFindById_shouldReturnNotFound() throws Exception {
        // Then
        searchExchangeRateById(mockMvc, exchangeRateResponseRepresentation1.getId())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.GEN001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.GEN001.getDescription()));
    }

    UUID getIdCoin() throws Exception {
        return IntegrationTestUtil.getMapper().readValue(
                CoinControllerIT.createCoin(mockMvc, new CoinRequestRepresentation("test"))
                        .andReturn().getResponse().getContentAsString()
                , CoinResponseRepresentation.class).getId();
    }

    static ResultActions createExchangeRate(
            MockMvc mockMvc,
            ExchangeRateRequestRepresentation exchangeRateRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/exchange-rates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(exchangeRateRequestRepresentation)));
    }

    static ResultActions searchAllExchangeRate(MockMvc mockMvc) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/exchange-rates")
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions searchExchangeRateById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/exchange-rates/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions updateExchangeRate(
            MockMvc mockMvc,
            UUID uuid,
            ExchangeRateRequestRepresentation exchangeRateRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put("/exchange-rates/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(exchangeRateRequestRepresentation)));
    }

    static ResultActions deleteExchangeRateById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete("/exchange-rates/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

}