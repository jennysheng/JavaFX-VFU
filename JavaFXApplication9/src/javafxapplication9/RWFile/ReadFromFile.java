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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxapplication9.DataLogger;
import javafxapplication9.simulator.WriteToFile;

/**
 * 1. Reader will run after Writer because of read semaphore. 2. Writer will
 * stop writing when the write semaphore has reached 0. 3. Reader will stop
 * reading when the read semaphore has reached 0.
 *
 * @author Jenny_2
 */
public class ReadFromFile extends Thread {

   ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();
    int lineNbr = 0;

    File selectedFile = null;

    public ReadFromFile(File selectedFile) {
        this.selectedFile = selectedFile;

    }

    @Override
    public void run() {

        if (selectedFile != null) {

            //reader priority------------------------------------------------------------------------------------------------------
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(selectedFile.getPath())))) {
                String line = reader.readLine();

                while (line != null) {

                    line = reader.readLine() + "\t";
                    if (line != null) {
                        System.out.println("line:" + lineNbr + line+":");

                        String[] parts = line.split("\t");
                     if(channelsdata.size()<9){
                            channelsdata.add(new DataLogger(parts[0],
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5]),
                                    Double.parseDouble(parts[6]),
                                    Double.parseDouble(parts[7]),
                                    Double.parseDouble(parts[8])));
                            lineNbr++;
                     }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }

   

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }
}
