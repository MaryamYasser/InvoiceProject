package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileOperations {

    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

    public void ReadItemsCSV(ArrayList<InvoiceHeader> invoiceHeaders) {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        File inFile = new File("C:\\Users\\myaali.LPALXY0878\\Downloads\\sales-invoice-generator\\Sales Invoice Generator\\InvoiceLine.csv");
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();
            tokens = str.split(",");


            int id = Integer.parseInt(tokens[0]);
            String type = tokens[1];
            float price = Float.parseFloat(tokens[2]);
            int quantity = Integer.parseInt(tokens[3]);

            InvoiceLine invoiceLine = new InvoiceLine(id, type, price, quantity);
            invoiceLines.add(invoiceLine);

            for (InvoiceHeader invoiceHeader: invoiceHeaders
            ) {
                if (invoiceLine.getId() == invoiceHeader.getInvoiceNum()){
                    invoiceHeader.getInvoiceLines().add(invoiceLine);
                    break;
                }

            }





        }



    }

    public ArrayList<InvoiceHeader> ReadHeaderCSV() {

        //String[][] myArray;

        ArrayList<InvoiceHeader> invoiceHeaderItems = new ArrayList<>();
        File inFile = new File("C:\\Users\\myaali.LPALXY0878\\Downloads\\sales-invoice-generator\\Sales Invoice Generator\\InvoiceHeader.csv");
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str;
        String[] tokens;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();
            tokens = str.split(",");


            int id = Integer.parseInt(tokens[0]);
            LocalDate date = LocalDate.parse(tokens[1], df);
            String invoiceOwner = tokens[2];





            InvoiceHeader invoiceHeader = new InvoiceHeader(id, date, invoiceOwner);



            invoiceHeaderItems.add(invoiceHeader);



        }

        //
        ReadItemsCSV(invoiceHeaderItems);

        for (InvoiceHeader invoiceHeader: invoiceHeaderItems
             ) {
            System.out.println("Item" + invoiceHeader + "ItemLines: " + invoiceHeader.getInvoiceLines().size());

        }


        return invoiceHeaderItems;
    }
}
