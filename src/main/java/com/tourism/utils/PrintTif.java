/*
package com.cda.utils;


import javax.imageio.ImageReader;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;

*/
/**
 * Created by ppm on 2017/9/15.
 *//*

public class PrintTif {
    private static  BufferedImage[] bi=null;
    private static  int  pages=0;
    private static File tffFile=null;
    public static   void init(){
        try {
            ImageReader tiffReader;
            ImageInputStream inputStream;
            inputStream=new FileImageInputStream(tffFile);
            IIORegistry iioreg=IIORegistry.getDefaultInstance();
            iioreg.registerApplicationClasspathSpis();
            ImageReaderSpi irs=new TIFFImageReaderSpi();
            tiffReader=irs.createReaderInstance();
            tiffReader.setInput(inputStream);
            pages=tiffReader.getNumImages(true);
            if(pages>0){
                bi=new BufferedImage[pages];
                for(int i=0;i<pages;i++){
                    bi[i]=tiffReader.read(i,null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void print() throws PrinterAbortException{
        PrinterJob job=PrinterJob.getPrinterJob();
        PageFormat format=job.defaultPage();
        double inch=72;
        Paper paper=format.getPaper();
        paper.setImageableArea(inch,inch,paper.getWidth()-2*inch,paper.getHeight()-2*inch);
        format.setPaper(paper);
        MyPrintable p=new MyPrintable();
        job.setPrintable(p, format);
        if(job.printDialog()){
            job.printDialog();
        }

    }
    public  static  void fxPrintTiff(String url) throws PrinterAbortException{
        tffFile=new File(url);
         init();
         print();
    }
    private static class MyPrintable implements Printable {
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if(pageIndex==pages){
                return NO_SUCH_PAGE;
            }
           graphics.translate((int)pageFormat.getImageableX(),(int)pageFormat.getImageableY());
            graphics.drawImage(bi[pageIndex],0,0,450,650,null);
            return  PAGE_EXISTS;
        }

    }


}
*/
