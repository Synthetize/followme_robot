package it.unicam.cs.followme.list.generators;

import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.FollowMeParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Instances of this class are used to generate shapes from an external source.
 * @apiNote This class uses the {@link FollowMeParser} to parse the shapes.
 */
public interface ShapesGenerator {
    /**
     * Generates a Map of shapes and coordinates from a file.
     * @param shapesFilePath the path of the file.
     * @return a list of shapes.
     */
    Map<Shape, Coordinate> generateShapes(String shapesFilePath);
}
