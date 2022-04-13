package no.ntnu.iir.wargames.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.iir.wargames.models.Army;
import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.UnitType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FileUtil class.
 *
 * @author Henrik Norheim Nysæther
 * @version 06.04.2022
 */
public class FileUtil {
  private static final Logger logger = LogManager.getLogger(FileUtil.class);
  
  public FileUtil() {
    // TODO empty constructor ;)
  }

  /**
   * Loads an army from a csv file. If the specified file is empty,
   * then an empty army with the name of placeholder will be returned.
   *
   * @return Army stored in file
   */
  public Army loadArmyFromFile(String filePath) {
    // read file and parse it
    Army army = new Army("placeholder");

    try (FileReader fileReader = new FileReader(filePath);
         CSVReader csvReader = new CSVReader(fileReader)) {
      // Sets the required variables, and initiates the required classes.
      int linesRead = 0;

      for (String[] line : csvReader) {
        if (linesRead == 0) {
          // Sets the name of the army to the text in the first line of the file.
          army.setName(line[0]);
        } else {
          army.addUnitOfUnitType(UnitType.valueOf(line[0]), line[1], Integer.parseInt(line[2]));
        }
        // Keeps the count for the lines read. Required to set the name of the army.
        linesRead++;
      }
    } catch (IOException e) {
      // If the application encounters an error, it will log it to the logger.
      logger.debug(e.getMessage());
    }
    return army;
  }

  /**
   * Saves an Army instance to file.
   *
   * @param filePath - path to where to save the file
   * @param fileName - name of the file
   */
  public void saveArmyToFile(Army army, String filePath, String fileName) {
    // save file with filename in filepath
    String fileUrl = (filePath + "/" + fileName + ".csv");
    File file = new File(fileUrl);

    try (FileWriter outputFile = new FileWriter(file);
         CSVWriter writer = new CSVWriter(outputFile,
             ',',
             CSVWriter.NO_QUOTE_CHARACTER,
             CSVWriter.DEFAULT_ESCAPE_CHARACTER,
             CSVWriter.DEFAULT_LINE_END)
    ) {

      // Data to be written to file
      List<String[]> data = new ArrayList<>();

      // Set the first line to the name of the army
      data.add(new String[] {army.getName()});

      // Get all army units
      List<Unit> units = army.getAllUnits();

      // Add unit to data list
      for (Unit u : units) {
        data.add(new String[] {
            String.valueOf(army.getUnitType(u)),
            u.getName(),
            String.valueOf(u.getHealth())
        });
      }

      // Write data to file
      writer.writeAll(data);
    } catch (IOException e) {
      logger.debug(e.getMessage());
    }
  }
}
