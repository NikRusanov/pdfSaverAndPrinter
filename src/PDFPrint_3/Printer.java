package PDFPrint_3;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.*;

public class Printer {

    public void printIMG(BufferedImage img) throws PrinterException {
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintService(ps);

        PageFormat pageFormat = job.defaultPage();
        double margin = 36;//12.5
        Paper paper = new Paper();
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()- margin * 2);
        pageFormat.setOrientation(PageFormat.PORTRAIT);//LANDSCAPE
        pageFormat.setPaper(paper);
        PageFormat validatePage = job.validatePage(pageFormat);

        job.setPrintable((g, pf, pageNumber) -> {
            if (pageNumber != 0)
            {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) g;
            g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
            double pageWidth = pf.getImageableWidth();
            double pageHeight = pf.getImageableHeight();
            double imageWidth = img.getWidth();
            double imageHeight = img.getHeight();
            double scaleX = pageWidth / imageWidth * 1.04;
            double scaleY = pageHeight / imageHeight * 1.04;
            double scaleFactor = Math.min(scaleX, scaleY);
            AffineTransform at = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
            g2.drawImage(img, at, null);

            return Printable.PAGE_EXISTS;
        }, validatePage);

        job.print();
    }
}

