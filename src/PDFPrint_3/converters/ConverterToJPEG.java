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

        if (inputPDF.exists()) {
            PDDocument doc;
            try {
                doc = PDDocument.load(inputPDF);



                PDFRenderer renderer = new PDFRenderer(doc);
                String fileName = inputPDF.getName().replace(".pdf", "");

                File destinationFile = new File(destination +  File.separator + fileName);

                if (!destinationFile.exists()) {
                    destinationFile.mkdir();
                    System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
                }


                System.out.println("CONVERTER START.....");
                for (int i = 0; i < doc.getNumberOfPages(); i++) {
                    BufferedImage image = renderer.renderImageWithDPI(i, 300);
                    File fileTemp = new File(destinationFile ,   fileName + "_" + i + ".jpg"); // jpg or png
                    ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
                    images.add(fileTemp);
                }
                doc.close();

            System.out.println("CONVERTER STOPTED.....");
            System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(inputPDF.getName() + " FILE DOES NOT EXIST");
        }
    }
}
