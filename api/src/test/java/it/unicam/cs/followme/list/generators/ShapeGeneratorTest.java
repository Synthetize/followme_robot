package it.unicam.cs.followme.list.generators;

import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.list.parser.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.utilities.FollowMeShapeChecker;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeGeneratorTest {

    FollowMeParserHandler handler = new ProgramParserHandler();
    FollowMeShapeChecker checker = FollowMeShapeChecker.DEFAULT_CHECKER;
    FollowMeParser parser = new FollowMeParser(handler, checker);
    ShapesGenerator shapesGenerator = new DefaultShapesGenerator(parser);

    @Test
    public void shouldGenerateNewShapesTakenFromExternalSource() {
        HashMap<Shape, Coordinate> shapes = (HashMap<Shape, Coordinate>) shapesGenerator.generateShapes("src/test/resources/shapes.txt");
        assertEquals(shapes.size(), 2);
        ArrayList<Shape> shapeList = new ArrayList<>(shapes.keySet());
        ArrayList<Coordinate> coordinateList = new ArrayList<>(shapes.values());

        Shape shape0 = shapeList.get(0);
        Coordinate coordinate0 = coordinateList.get(0);
        assertEquals("CIRCLE1_", shape0.getConditionLabel());
        assertTrue(shape0 instanceof CircleShape);
        CircleShape circle = (CircleShape) shape0;
        assertEquals(coordinate0.getX(), 5);
        assertEquals(coordinate0.getY(), 4);
        assertEquals(circle.radius(), 3);

        Shape shape1 = shapeList.get(1);
        Coordinate coordinate1 = coordinateList.get(1);
        assertEquals("RECTANGLE1_", shape1.getConditionLabel());
        assertTrue(shape1 instanceof RectangleShape);
        RectangleShape rectangle = (RectangleShape) shape1;
        assertEquals(coordinate1.getX(), 0);
        assertEquals(coordinate1.getY(), 0);
        assertEquals(rectangle.width(), 7);
        assertEquals(rectangle.height(), 4);
    }

    @Test
    public void shouldThrowRunTimeExceptionIfShapeTypeDoesNotExist() {
        assertThrows(RuntimeException.class, () -> shapesGenerator.generateShapes("src/test/resources/non_exixting_shape.txt"));
    }

    @Test
    public void shouldThrowRunTimeExceptionIfShapeFileDoesNotExist() {
        assertThrows(RuntimeException.class, () -> shapesGenerator.generateShapes("src/test/resources/thisfilenotexist.txt"));
    }
}
