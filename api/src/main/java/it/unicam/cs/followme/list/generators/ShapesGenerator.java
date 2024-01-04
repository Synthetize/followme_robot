package it.unicam.cs.followme.list.generators;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.FollowMeParser;

import java.io.File;
import java.util.Map;


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
    Map<Shape, Coordinate> generateShapes(File shapesFile);
}
