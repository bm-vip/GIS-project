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
    void findById_shouldReturn404NotFound() throws Exception {
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
    void findAllSelect_shouldReturnPageableSelect2Models() throws Exception {
        String filter = objectMapper.writeValueAsString(new StationModel(){{setName("station A1");}});

        mockMvc.perform(get("/api/v1/station/findAllSelect")
                .param("model",filter)
                .param("page","0")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect((jsonPath("$.content", Matchers.hasSize(2))))
                .andExpect(jsonPath("$.content.[0].text").value("station A1"))
        ;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findClosest_passCorrectLatAndLonThenShouldReturnStationModelsOrderByDistance() throws Exception {
        mockMvc.perform(get("/api/v1/station/findClosest-by-haversine")
                        .param("latitude","35.700568")
                        .param("longitude","51.349122")
                        .param("maxDistance","1000")
                        .param("companyId","1")
                        .param("page","0")
                        .param("size","10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(9))
                .andExpect((jsonPath("$.content", Matchers.hasSize(9))))
                .andExpect(jsonPath("$.content.[0].name").value("station A1"))
                .andExpect(jsonPath("$.content.[1].name").value("station A2"))
                .andExpect(jsonPath("$.content.[2].name").value("station A3"))
                .andExpect(jsonPath("$.content.[3].name").value("station A4"))
                .andExpect(jsonPath("$.content.[4].name").value("station A6"))
                .andExpect(jsonPath("$.content.[5].name").value("station A9"))
                .andExpect(jsonPath("$.content.[6].name").value("station A10"))
                .andExpect(jsonPath("$.content.[7].name").value("station A5"))
                .andExpect(jsonPath("$.content.[8].name").value("station A8"))
        ;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    void findClosest_passIncorrectLatAndLonThenshouldReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/station/findClosest-by-haversine").param("latitude","-99,188").param("companyId","0"))
                .andExpect(status().isBadRequest())
        ;
    }
}
