package se.dzm.electricvehiclechargingstationmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(roles="ADMIN")
    @Test
    @Order(1)
    @Commit
    void save_shouldSaveStationModelToDatabase() throws Exception {
        StationModel model = new StationModel() {{
            setName("test station");
            setLatitude(59.409801);
            setLongitude(17.827791);
            setCompany(new CompanyModel(){{setId(3L);}});
        }};
        String json = objectMapper.writeValueAsString(model);

        mockMvc.perform(post("/api/v1/station/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(18))
                .andExpect(jsonPath("$.company.id").value(3))
        ;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    @Order(2)
    void deleteById_shouldDeleteByIdFromDatabase() throws Exception {
        mockMvc.perform(delete("/api/v1/station/deleteById/{id}",18L))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void save_shouldThrowBadRequestError() throws Exception {
        mockMvc.perform(post("/api/v1/station/save").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findById_shouldReturnStationModel() throws Exception {
        mockMvc.perform(get("/api/v1/station/findById/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("station A1"));
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findById_shouldThrowNotFoundError() throws Exception {
        mockMvc.perform(get("/api/v1/station/findById/{id}", 44L))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findAll_shouldReturnPageableStationModels() throws Exception {
        String filter = objectMapper.writeValueAsString(new StationModel(){{setName("station");}});

        mockMvc.perform(get("/api/v1/station/findAll").param("model",filter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(17))
                .andExpect((jsonPath("$.content", Matchers.hasSize(10))))
        ;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void countAll_shouldReturnTotalNumberOfCompanies() throws Exception {
        String filter = objectMapper.writeValueAsString(new StationModel(){{setName("station");}});

        mockMvc.perform(get("/api/v1/station/countAll").param("model",filter))
                .andExpect(status().isOk())
                .andExpect(content().string("17"));
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findAllByLocation_shouldReturnStationModelsOrderByDistance() throws Exception {
        mockMvc.perform(get("/api/v1/station/findAllByLocation/{latitude}/{longitude}",35.294952,53.715041).param("companyId","1"))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.*", Matchers.hasSize(10))))
                .andExpect(jsonPath("$.[0].distance").value(2.42))
                .andExpect(jsonPath("$.[1].distance").value(3.17))
                .andExpect(jsonPath("$.[2].distance").value(3.54))
                .andExpect(jsonPath("$.[3].distance").value(3.74))
                .andExpect(jsonPath("$.[4].distance").value(5.23))
                .andExpect(jsonPath("$.[5].distance").value(7.71))
                .andExpect(jsonPath("$.[6].distance").value(8.57))
                .andExpect(jsonPath("$.[7].distance").value(10.13))
                .andExpect(jsonPath("$.[8].distance").value(12.08))
                .andExpect(jsonPath("$.[9].distance").value(15.41))
        ;
    }
}
