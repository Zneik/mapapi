package ru.zneik.mapapi.controller.all;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.UserTestData;
import ru.zneik.mapapi.to.TokenTO;
import ru.zneik.mapapi.util.TestUtil;
import ru.zneik.mapapi.util.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends BaseControllerTest {

    @Test
    void login() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AuthController.URL + "/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(UserTestData.USER_AUTH_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        TokenTO tokenTO = TestUtil.readFromJsonMvcResult(mvcResult, TokenTO.class);
        Assertions.assertNotNull(tokenTO);
        Assertions.assertTrue(StringUtils.isNotBlank(tokenTO.getToken()));
    }

    @Test
    public void incorrectLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AuthController.URL + "/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(UserTestData.USER_AUTH_UNCORRECT)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}