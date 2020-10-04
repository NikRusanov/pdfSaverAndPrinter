package PDFPrint_3;


import PDFPrint_3.converters.ConverterToJPEG;

import javax.imageio.ImageIO;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    App() throws IOException, PrinterException {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your destination folder where save image \n");
            String destination = sc.nextLine();
            System.out.print("Enter your selected pdf files name with source folder \n");
            String sourcePathWithFileName = sc.nextLine();
            if (sourcePathWithFileName.isEmpty()) {
                System.exit(1);
            }
            String[] files = sourcePathWithFileName.split(",");
            File pdf = new File(sourcePathWithFileName);
            System.out.print("FILE:>> "+ pdf);
            ConverterToJPEG converter = new ConverterToJPEG(destination);
            for (String filename : files) {
                converter.setInputPDF(new File(filename));
                converter.convert();
            }
        Printer printer = new Printer();
        for(var image : converter.getImages()) {
            printer.printIMG(ImageIO.read(image));
        }
    }
}
