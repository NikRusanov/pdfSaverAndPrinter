package PDFPrint_3.converters;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterToJPEG  implements ConverterPDF {
    private File inputPDF;
    private String destination;
    private  List<File> images;

    public ConverterToJPEG(String dest) {
        destination = dest;
    }

    public void setInputPDF(File inputPDF) {
        this.inputPDF = inputPDF;
    }

    public List<File> getImages() {
        return images;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void convert()  {
        images = new ArrayList<>();
        File destinationFile = new File(destination);

        if (!destinationFile.exists()) {
            destinationFile.mkdir();
            System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
        }
        if (inputPDF.exists()) {
            PDDocument doc;
            try {
                doc = PDDocument.load(inputPDF);

                PDFRenderer renderer = new PDFRenderer(doc);
                String fileName = inputPDF.getName().replace(".pdf", "");
                System.out.println("CONVERTER START.....");
                for (int i = 0; i < doc.getNumberOfPages(); i++) {
                    BufferedImage image = renderer.renderImageWithDPI(i, 300);
                    File fileTemp = new File(destination + fileName + "_" + i + ".jpg"); // jpg or png
                    ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
                    images.add(fileTemp);
                }
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("CONVERTER STOPTED.....");
            System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
        } else {
            System.err.println(inputPDF.getName() + " FILE DOES NOT EXIST");
        }
    }
}
