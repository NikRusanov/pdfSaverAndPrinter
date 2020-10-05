package PDFPrint_3;


import PDFPrint_3.converters.ConverterToJPEG;
import PDFPrint_3.exceptions.EmptyArgumentsException;

import javax.imageio.ImageIO;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    App() throws PrinterException, EmptyArgumentsException {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your destination folder where save image \n");
            String destination = sc.nextLine();
            System.out.print("Enter your selected pdf files name with source folder \n");
            String sourcePathWithFileName = sc.nextLine();
            if (sourcePathWithFileName.isEmpty()) {
                throw new EmptyArgumentsException("Empty arguments");
            }
            String[] files = sourcePathWithFileName.split(",");
            ConverterToJPEG converter = new ConverterToJPEG(destination);
            for (String filename : files) {
                File tmpPath = new File(filename);
                converter.setInputPDF(tmpPath);
                converter.convert();
            }
        Printer printer = new Printer();
        for(var image : converter.getImages()) {
            try {
                printer.printIMG(ImageIO.read(image));
            } catch (PrinterException e) {
              System.err.println("printer not found. Try to set default printer in your system");
              System.exit(1);
            } catch (IOException ex) {
                System.err.println(image.getAbsolutePath() + " (No such file or directory)");
            }
        }
    }
}
