package ru.zneik.mapapi.data;

import ru.zneik.mapapi.to.UserAuthTO;

public class UserTestData {
    public static final String USER_1_USERNAME = "user";

    public static final UserAuthTO USER_AUTH_1 = new UserAuthTO() {{
        setUsername(USER_1_USERNAME);
        setPassword("1233211");
    }};

    public static final UserAuthTO USER_AUTH_UNCORRECT = new UserAuthTO() {{
        setUsername("RandomUser");
        setPassword("RandomPassword");
    }};

}
