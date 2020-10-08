package ru.zneik.mapapi.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.MapTestData;
import ru.zneik.mapapi.data.PointsGroupTestData;
import ru.zneik.mapapi.data.UserTestData;
import ru.zneik.mapapi.model.Map;
import ru.zneik.mapapi.util.TestUtil;
import ru.zneik.mapapi.util.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMapControllerTest extends BaseControllerTest {

    @Test
    void create() throws Exception {
        Map newMap = MapTestData.getNew();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AdminMapController.URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(newMap)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        Map createdMapResult = TestUtil.readFromJsonMvcResult(mvcResult, Map.class);
        newMap.setUuid(createdMapResult.getUuid());
        MapTestData.MATCHER_WITH_IGNORE_FIELDS.assertMatch(createdMapResult, newMap);
    }

    @Test
    void update() throws Exception {
        Map updatedMap = MapTestData.getUpdated();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(AdminMapController.URL
                + "/" + updatedMap.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(updatedMap)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map updatedMapResult = TestUtil.readFromJsonMvcResult(mvcResult, Map.class);
        MapTestData.MATCHER_WITH_IGNORE_FIELDS.assertMatch(updatedMap, updatedMapResult);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(AdminMapController.URL
                + "/" + MapTestData.MAP_1.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void addGroupToMap() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AdminMapController.URL +
                "/" + MapTestData.MAP_1.getUuid() + "/groups/" + PointsGroupTestData.POINTS_GROUP_2.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteGroupFromMap() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(AdminMapController.URL +
                "/" + MapTestData.MAP_1.getUuid() + "/groups/" + PointsGroupTestData.POINTS_GROUP_1.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}