package ru.zneik.mapapi.controller.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.PointsGroupTestData;
import ru.zneik.mapapi.data.PointsTestData;
import ru.zneik.mapapi.data.UserTestData;
import ru.zneik.mapapi.model.Point;
import ru.zneik.mapapi.util.TestUtil;
import ru.zneik.mapapi.util.json.JsonUtil;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminPointsControllerTest extends BaseControllerTest {

    @Test
    void addToGroup() throws Exception {
        List<Point> newPoints = PointsTestData.getNewPoints();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AdminPointsController.URL +
                "/groups/" + PointsGroupTestData.POINTS_GROUP_2.getUuid() + "/points")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(newPoints)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        List<Point> createdPoints = TestUtil.readListFromJsonMvcResult(mvcResult, Point.class);
        Assertions.assertEquals(newPoints.size(), createdPoints.size());
        for (int i = 0; i < newPoints.size(); i++) {
            newPoints.get(i).setUuid(createdPoints.get(i).getUuid());
        }
        PointsTestData.MATCHER_WITH_IGNORING_FIELDS.assertMatch(createdPoints, newPoints);
    }

    @Test
    void update() throws Exception {
        Point update = PointsTestData.getUpdated();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(AdminPointsController.URL +
                "/points/" + update.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(update)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Point updated = TestUtil.readFromJsonMvcResult(mvcResult, Point.class);
        PointsTestData.MATCHER_WITH_IGNORING_FIELDS
                .assertMatch(updated, update);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(AdminPointsController.URL +
                "/points/" + PointsTestData.POINT_1.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}