/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9.RWFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.log;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxapplication9.DataLogger;
import javafxapplication9.simulator.WriteToFile;

/**
 *
 *
 * @author Jenny_2
 */
public class ReadFromFile {

    ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();

    int lineNbr = 0;
    int i = 0;
    File selectedFile = null;

    public ReadFromFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public ObservableList<DataLogger> read() throws ParseException {

        StringBuilder filecontent = new StringBuilder();

        if (selectedFile != null) {

            try (BufferedReader reader = new BufferedReader(new FileReader(new File(selectedFile.getPath())))) {

                String line = reader.readLine();
                int j = 0;

                while ((line) != null) {
                    line = reader.readLine();
                    if(line!=null){
                    filecontent.append(line + "\t").trimToSize();
                    j++;
                }
                }
                String content = filecontent.toString();
                String[] parts = content.split("\t");

               
                int i = 0;

                while (i < ((j - 1) * 9)) {

                    channelsdata.add(new DataLogger(parts[i],
                            Double.parseDouble(parts[i + 1]),
                            Double.parseDouble(parts[i + 2]),
                            Double.parseDouble(parts[i + 3]),
                            Double.parseDouble(parts[i + 4]),
                            Double.parseDouble(parts[i + 5]),
                            Double.parseDouble(parts[i + 6]),
                            Double.parseDouble(parts[i + 7]),
                            Double.parseDouble(parts[i + 8])));
                    System.out.println(parts[i]);
                    System.out.println("j"+j);
                    i += 9;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }

    public void setChannelsdata(ObservableList<DataLogger> channelsdata) {
        this.channelsdata = channelsdata;
    }

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }
}
