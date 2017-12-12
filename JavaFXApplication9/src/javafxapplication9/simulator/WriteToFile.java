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
import java.io.PrintWriter;
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
    FileWriter fw;
    BufferedWriter writer;
    PrintWriter out;

    public WriteToFile(File selectedFile) {
        this.selectedFile = selectedFile;

    }
    int lineNbr = 0;

    @Override
    public void run() {
        synchronized (this) {
            try {
                // append to end of file
                fw = new FileWriter(selectedFile.getPath(), true);
                writer = new BufferedWriter(fw);
                out = new PrintWriter(writer);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss:S");
                Date date = new Date();
                int i = 0;
                while (true) {
                    out.append(dateFormat.format(date) + "\t");

                    for (int idx = 1; idx <= 8; ++idx) {
                        double randomNbr = randomGenerator.nextDouble();
                        out.append(String.valueOf(randomNbr) + "\t");
                        lineNbr++;                       
                        wait();         
                    }
                    out.append("\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();

            } catch (InterruptedException ex) {
                Logger.getLogger(WriteToFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }
        }
    }
}
