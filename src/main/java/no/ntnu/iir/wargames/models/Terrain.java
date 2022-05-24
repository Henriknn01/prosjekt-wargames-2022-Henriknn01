package no.ntnu.iir.wargames.models;

import no.ntnu.iir.wargames.util.OpenSimplexNoise;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terrain {
  private int octaves;
  private double roughness;
  private double scale;
  private OpenSimplexNoise openSimplexNoise;
  private long seed;
  private final int pixelScale = 1;
  private double[][] terrainArray;

  private static final Logger logger = LogManager.getLogger(Terrain.class);

  Random random = new Random();

  /**
   * Terrain constructor
   *
   * @param octaves Number of Layers combined together to get a natural looking surface
   * @param roughness Increasing the of the range between -1 and 1, causing higher values eg. more rough terrain
   * @param scale Overall scaling of the terrain
   */
  public Terrain(int octaves, double roughness, double scale) {
    this.octaves = octaves;
    this.roughness = roughness;
    this.scale = scale;
    this.openSimplexNoise = new OpenSimplexNoise(random.nextInt(Integer.MAX_VALUE));
  }

  /**
   * Terrain constructor
   *
   * @param seed Seed used to generate the terrain
   * @param octaves Number of Layers combined together to get a natural looking surface
   * @param roughness Increasing the of the range between -1 and 1, causing higher values eg. more rough terrain
   * @param scale Overall scaling of the terrain
   */
  public Terrain(long seed, int octaves, double roughness, double scale) {
    this.octaves = octaves;
    this.roughness = roughness;
    this.scale = scale;
    this.seed = seed;
    this.openSimplexNoise = new OpenSimplexNoise(seed);
  }

  /**
   * Gets the type of terrain on specified x, y position.
   *
   * @param terrain Terrain
   * @param x x position
   * @param y y position
   * @return TerrainType of specified position
   */
  // TODO: Add error handeling if position is outside the map
  public TerrainType getTerrainType(double[][] terrain, int x, int y) {
    TerrainType terrainType = null; // sets the default terrain type to be returned
    if (terrain[x][y] <= 0) {
      terrainType = TerrainType.PLAINS;
    } else if (terrain[x][y] > 0 && terrain[x][y] <= 0.6f) {
      terrainType = TerrainType.FOREST;
    } else if (terrain[x][y] > 0.6f) {
      terrainType = TerrainType.HILL;
    }
    return terrainType;
  }

  /**
   * Gets the type of terrain on specified x, y position using stored terrain array.
   *
   * @param x x position
   * @param y y position
   * @return TerrainType of specified position
   */
  // TODO: Add error handeling if position is outside the map
  public TerrainType getTerrainType(int x, int y) {
    TerrainType terrainType = null; // sets the default terrain type to be returned
    try {
      if (terrainArray[x][y] <= 0) {
        terrainType = TerrainType.PLAINS;
      } else if (terrainArray[x][y] > 0 && terrainArray[x][y] <= 0.6f) {
        terrainType = TerrainType.FOREST;
      } else if (terrainArray[x][y] > 0.6f) {
        terrainType = TerrainType.HILL;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      logger.error(e);
    }
    return terrainType;
  }

  /**
   * Gets the 2d terrain array.
   *
   * @return 2D array with values representing the height of the terrain.
   */
  public double[][] getTerrainArray() {
    return terrainArray;
  }

  /**
   * Creates a terrain with specified height and width
   *
   * @param width width of terrain
   * @param height height of terrain
   * @return 2D array with values representing the height of the terrain.
   */
  public double[][] createWorld(int width, int height) {
    this.terrainArray = generateOctavedSimplexNoise(width, height);
    return terrainArray;
  }

  /**
   * Generates the simplex noise used for the terrain.
   *
   * @param width width of the terrain
   * @param height height of the terrain
   * @return
   */
  private double[][] generateOctavedSimplexNoise(int width, int height) {
    double[][] totalNoise = new double[width][height];
    double layerFrequency = this.scale;
    double layerWeight = 1;
    double weightSum = 0;

    for (int octave = 0; octave < this.octaves; octave++) {
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          totalNoise[x][y] += openSimplexNoise.eval(x * layerFrequency, y * layerFrequency) * layerWeight;
        }
      }

      layerFrequency *= 2;
      weightSum += layerWeight;
      layerWeight *= this.roughness;

    }
    return totalNoise;
  }

  /**
   * Creates a 2D PNG Image from a two dimensional array.
   *
   * @param array
   */
  public void visualize(double[][] array) {
    createImage(array, "generatedMap");
  }

  /**
   * Creates an amount of 2D PNG Images from a two dimensional array.
   *
   * @param array
   */
  public void visualize(double[][] array, int amount) {
    for (int i = 0; i < amount; i++) {
      createImage(array, "generatedMap" + i);
    }
  }

  /**
   * Creates an amount of 2D PNG Images from a two dimensional array.
   *
   * @param array
   */
  public void visualize(double[][] array, String filename) {
    createImage(array, filename);
  }

  /**
   * Private Method to create a Buffered Image and save the result in a file.
   *
   * @param array
   * @param filename
   */
  private void createImage(double[][] array, String filename) {
    int imageHeight = array.length * this.pixelScale;
    int imageWidth = array[0].length * this.pixelScale;

    // Constructs a BufferedImage of one of the predefined image types.
    BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    // Create a graphics which can be used to draw into the buffered image
    Graphics2D graphics2D = bufferedImage.createGraphics();
    Color darkgreen = new Color(34, 139, 34);

    // fill all the image with white
    graphics2D.setColor(Color.white);
    graphics2D.fillRect(0, 0, imageWidth, imageHeight);

    for (int x = 0; x < array.length; x++) {
      for (int y = 0; y < array[x].length; y++) {

        //Defining coloring rules for each value

        if (getTerrainType(array, x, y) == TerrainType.PLAINS) {
          graphics2D.setColor(Color.GREEN);
          graphics2D.fillRect(y * this.pixelScale, x * this.pixelScale, this.pixelScale, this.pixelScale);
        } else if (getTerrainType(array, x, y) == TerrainType.FOREST) {
          graphics2D.setColor(darkgreen);
          graphics2D.fillRect(y * this.pixelScale, x * this.pixelScale, this.pixelScale, this.pixelScale);
        } else if (getTerrainType(array, x, y) == TerrainType.HILL) {
          graphics2D.setColor(Color.GRAY);
          graphics2D.fillRect(y * this.pixelScale, x * this.pixelScale, this.pixelScale, this.pixelScale);
        }
      }
    }
    // Disposes of this graphics context and releases any system resources
    // that it is using.
    graphics2D.dispose();
    // Save as PNG
    File file = new File("src/main/resources/" + filename + ".png");
    try {
      ImageIO.write(bufferedImage, "png", file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
