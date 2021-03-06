
package PDFPrint_3;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import PDFPrint_3.converters.ConverterToJPEG;
import PDFPrint_3.exceptions.EmptyArgumentsException;


public class Main {

    public static void main(String[] args) {
        try {
            new App();
        } catch (PrinterException e) {
            System.err.println("Printer error");
        } catch (EmptyArgumentsException e) {
            System.err.println(e.getMessage());
        }
    }
}
