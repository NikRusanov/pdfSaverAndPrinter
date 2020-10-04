
package PDFPrint_3;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import PDFPrint_3.converters.ConverterToJPEG;


public class Main {

    public static void main(String[] args) {
        try {
            App app = new App();
        } catch (IOException e) {
            System.err.println("IO EXCEPTION");
            e.printStackTrace();
        } catch (PrinterException e) {
            System.err.println("PRINTER EXCEPTION");
            e.printStackTrace();
        }
    }
}
