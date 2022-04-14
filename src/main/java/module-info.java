module wargames {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.opencsv;
  requires org.apache.logging.log4j;
  requires com.google.common;

  opens no.ntnu.iir.wargames to javafx.fxml;
  exports no.ntnu.iir.wargames;
  exports no.ntnu.iir.wargames.util;
  opens no.ntnu.iir.wargames.util to javafx.fxml;
  exports no.ntnu.iir.wargames.gui;
  opens no.ntnu.iir.wargames.gui to javafx.fxml;
  exports no.ntnu.iir.wargames.models;
  opens no.ntnu.iir.wargames.models to javafx.fxml;
}
