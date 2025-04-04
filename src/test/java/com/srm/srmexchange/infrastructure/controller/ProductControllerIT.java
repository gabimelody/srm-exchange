package com.srm.srmexchange.infrastructure.controller;

import com.srm.representation.*;
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

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@DisplayName("ProductController Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static ProductRequestRepresentation productRequestRepresentation1;
    private static ProductRequestRepresentation productRequestRepresentation2;

    private static ProductResponseRepresentation productResponseRepresentation1;
    private static ProductResponseRepresentation productResponseRepresentation2;

    @Test
    @Order(1)
    @DisplayName("When create product in path /products should return created")
    void createProduct_shouldReturnCreated() throws Exception {
        // Given
        UUID idKingdom = getIdKingdom();
        UUID idCoinBase = getIdCoinBase();

        productRequestRepresentation1 = new ProductRequestRepresentation(
                "test", BigDecimal.ONE, idKingdom, idCoinBase);

        productRequestRepresentation2 = new ProductRequestRepresentation(
                "test2", BigDecimal.ONE, idKingdom, idCoinBase);

        // Then
        productResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                createProduct(mockMvc, productRequestRepresentation1)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(productRequestRepresentation1.getName()))
                        .andReturn().getResponse().getContentAsString()
                , ProductResponseRepresentation.class);

        productResponseRepresentation2 = IntegrationTestUtil.getMapper().readValue(
                createProduct(mockMvc, productRequestRepresentation2)
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id", not(productResponseRepresentation1.getId())))
                        .andExpect(jsonPath("$.name").value(productRequestRepresentation2.getName()))
                        .andReturn().getResponse().getContentAsString()
                , ProductResponseRepresentation.class);
    }

    @Test
    @Order(2)
    @DisplayName("When find all product in path /products should return ok")
    void whenFindAll_shouldReturnOk() throws Exception {
        // Then
        searchAllProduct(mockMvc)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Order(3)
    @DisplayName("When find product by id in path /products/{uuid} should return ok")
    void whenFindById_shouldReturnOk() throws Exception {
        // Then
        searchProductById(mockMvc, productResponseRepresentation1.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productResponseRepresentation1.getId() + ""))
                .andExpect(jsonPath("$.id", not(productResponseRepresentation2.getId())));

        searchProductById(mockMvc, productResponseRepresentation2.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productResponseRepresentation2.getId() + ""))
                .andExpect(jsonPath("$.id", not(productResponseRepresentation1.getId())));
    }

    @Test
    @Order(4)
    @DisplayName("When update product in path /products should return ok")
    void updateProduct_shouldReturnOk() throws Exception {
        // Given
        productRequestRepresentation1.setName("testNovo");

        // Then
        productResponseRepresentation1 = IntegrationTestUtil.getMapper().readValue(
                updateProduct(mockMvc, productResponseRepresentation1.getId(), productRequestRepresentation1)
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value(productRequestRepresentation1.getName()))
                        .andReturn().getResponse().getContentAsString()
                , ProductResponseRepresentation.class);
    }

    @Test
    @Order(5)
    @DisplayName("When delete product by id in path /products/{uuid} should return no content")
    void whenDelete_shouldReturnNoContent() throws Exception {
        // Then
        deleteProductById(mockMvc, productResponseRepresentation1.getId())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    @DisplayName("When find product by id in path /products/{uuid} should return not found")
    void whenFindById_shouldReturnNotFound() throws Exception {
        // Then
        searchProductById(mockMvc, productResponseRepresentation1.getId())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCodeEnum.GEN001.name()))
                .andExpect(jsonPath("$.description").value(ErrorCodeEnum.GEN001.getDescription()));
    }

    UUID getIdKingdom() throws Exception {
        return IntegrationTestUtil.getMapper().readValue(
                KingdomControllerIT.createKingdom(mockMvc, new KingdomRequestRepresentation("test"))
                        .andReturn().getResponse().getContentAsString()
                , KingdomResponseRepresentation.class).getId();
    }

    UUID getIdCoinBase() throws Exception {
        return IntegrationTestUtil.getMapper().readValue(
                CoinControllerIT.createCoin(mockMvc, new CoinRequestRepresentation("test"))
                        .andReturn().getResponse().getContentAsString()
                , CoinResponseRepresentation.class).getId();
    }

    static ResultActions createProduct(
            MockMvc mockMvc,
            ProductRequestRepresentation productRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(productRequestRepresentation)));
    }

    static ResultActions searchAllProduct(MockMvc mockMvc) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/products")
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions searchProductById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get("/products/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

    static ResultActions updateProduct(
            MockMvc mockMvc,
            UUID uuid,
            ProductRequestRepresentation productRequestRepresentation
    ) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put("/products/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(productRequestRepresentation)));
    }

    static ResultActions deleteProductById(MockMvc mockMvc, UUID uuid) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete("/products/" + uuid)
                .contentType(MediaType.APPLICATION_JSON));
    }

}