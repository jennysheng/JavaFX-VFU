/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9.simulator;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jenny_2
 */
public class WriteToFile extends Thread {

    Random randomGenerator = new Random();
    File selectedFile = null;

    public WriteToFile(File selectedFile) {
        this.selectedFile = selectedFile;

    }

    @Override
    public void run() {
       

          
            while (true) {

                try {
                    // append to end of file
                    FileWriter fw = new FileWriter(selectedFile.getAbsoluteFile(), true);
                    BufferedWriter writer = new BufferedWriter(fw);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss:S");
                    Date date = new Date();
                     writer.append(dateFormat.format(date)+"\t");
                    for (int idx = 1; idx <= 8; ++idx) {
                        double randomNbr = randomGenerator.nextDouble();                      
                        writer.append((char) randomNbr + "\t");
                    }
                  
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

