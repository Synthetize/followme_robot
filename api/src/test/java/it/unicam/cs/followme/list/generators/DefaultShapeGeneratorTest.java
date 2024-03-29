package it.unicam.cs.followme.list.generators;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.parserHandler.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.utilities.FollowMeShapeChecker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultShapeGeneratorTest {


    FollowMeParserHandler handler = new ProgramParserHandler(null, null);
    FollowMeShapeChecker checker = FollowMeShapeChecker.DEFAULT_CHECKER;
    FollowMeParser parser = new FollowMeParser(handler, checker);
    ShapesGenerator shapesGenerator = new DefaultShapesGenerator(parser);

    @Test
    public void shouldGenerateNewShapesTakenFromExternalSource() {
        File shapesFile = new File("src/test/resources/shapes.txt");
        HashMap<Shape, Coordinate> shapes = (HashMap<Shape, Coordinate>) shapesGenerator.generateShapes(shapesFile);
        assertEquals(shapes.size(), 2);
        ArrayList<Shape> shapeList = new ArrayList<>(shapes.keySet());
        ArrayList<Coordinate> coordinateList = new ArrayList<>(shapes.values());

        Shape shape0 = shapeList.get(0);
        Coordinate coordinate0 = coordinateList.get(0);
        assertEquals("CIRCLE1_", shape0.getConditionLabel());
        assertInstanceOf(CircleShape.class, shape0);
        CircleShape circle = (CircleShape) shape0;
        assertEquals(coordinate0.getX(), 5);
        assertEquals(coordinate0.getY(), 4);
        assertEquals(circle.radius(), 3);

        Shape shape1 = shapeList.get(1);
        Coordinate coordinate1 = coordinateList.get(1);
        assertEquals("RECTANGLE1_", shape1.getConditionLabel());
        assertInstanceOf(RectangleShape.class, shape1);
        RectangleShape rectangle = (RectangleShape) shape1;
        assertEquals(coordinate1.getX(), 0);
        assertEquals(coordinate1.getY(), 0);
        assertEquals(rectangle.width(), 7);
        assertEquals(rectangle.height(), 4);
    }

    @Test
    public void shouldThrowRunTimeExceptionIfShapeTypeDoesNotExist() {
        File shapesFile = new File("src/test/resources/shape_type_not_exist.txt");
        assertThrows(RuntimeException.class, () -> shapesGenerator.generateShapes(shapesFile));
    }

}
