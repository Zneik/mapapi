package ru.zneik.mapapi.data;

import org.springframework.beans.BeanUtils;
import ru.zneik.mapapi.model.Point;
import ru.zneik.mapapi.util.TestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PointsTestData {
    public static final TestMatcher<Point> MATCHER_WITH_IGNORING_FIELDS =
            TestMatcher.usingFieldsWithIgnoringAssertions(Point.class, "pointGroup");

    public static final Point POINT_1 = new Point() {{
        setUuid(UUID.fromString("adac3f47-0b5c-47ae-9e91-51735fcc8a2d"));
        setText("Просто точка");
        setX("1");
        setY("1");
    }};

    public static final Point POINT_2 = new Point() {{
        setUuid(UUID.fromString("8f29eda8-797b-4ac8-ae46-59b3ef91f45e"));
        setText("Просто точка 55");
        setX("55");
        setY("55");
    }};

    public static List<Point> getNewPoints() {
        List<Point> points = new ArrayList<>();
        Point point1 = new Point();
        point1.setText("Text 1");
        point1.setX("31");
        point1.setX("35");
        Point point2 = new Point();
        point2.setText("Text 2");
        point2.setX("51");
        point2.setX("55");
        points.add(point1);
        points.add(point2);
        return points;
    }

    public static Point getUpdated() {
        Point point = new Point();
        BeanUtils.copyProperties(POINT_1, point);
        point.setText("Updated text");
        point.setX("123");
        point.setY("123");
        return point;
    }
}
