package it.unicam.cs.followme.list.generators;

import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultShapesGenerator implements ShapesGenerator {
    FollowMeParser parser;

    public DefaultShapesGenerator(FollowMeParser parser) {
        this.parser = parser;
    }

    public Map<Shape, Coordinate> generateShapes(File shapesFile) {
        try {
            List<ShapeData> shapes = parser.parseEnvironment(shapesFile);
            return shapes.stream()
                    .map(shapeData -> {
                        Coordinate shapeCoordinate = new CartesianCoordinate(shapeData.args()[0], shapeData.args()[1]);
                        if (shapeData.shape().equals("RECTANGLE")) {
                            Shape rectangleShape = new RectangleShape(shapeData.args()[2], shapeData.args()[3], shapeData.label());
                            return Map.entry(rectangleShape, shapeCoordinate);
                        } else if (shapeData.shape().equals("CIRCLE")) {
                            Shape circleShape = new CircleShape(shapeData.args()[2], shapeData.label());
                            return Map.entry(circleShape, shapeCoordinate);
                        }
                        throw new RuntimeException("Shape type not supported");
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (FollowMeParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
