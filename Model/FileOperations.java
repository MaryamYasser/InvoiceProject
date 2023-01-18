package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;


public class FileOperations {

    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

    public void ReadItemsCSV(ArrayList<InvoiceHeader> invoiceHeaders) {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        File inFile = new File("src/InvoiceLine.csv");
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

            for (InvoiceHeader invoiceHeader : invoiceHeaders
            ) {
                if (invoiceLine.getId() == invoiceHeader.getInvoiceNum()) {
                    invoiceHeader.getInvoiceLines().add(invoiceLine);
                    break;
                }

            }


        }


    }

    public ArrayList<InvoiceHeader> ReadHeaderCSV() {

        //String[][] myArray;

        ArrayList<InvoiceHeader> invoiceHeaderItems = new ArrayList<>();
        File inFile = new File("src/InvoiceHeader.csv");
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

        for (InvoiceHeader invoiceHeader : invoiceHeaderItems
        ) {
            System.out.println("Item" + invoiceHeader + "ItemLines: " + invoiceHeader.getInvoiceLines().size());

        }


        return invoiceHeaderItems;
    }

    public void WriteCSV(ArrayList<InvoiceHeader> invoiceHeaders){
        try {
            FileWriter readcsv_Header = new FileWriter("src/newHeaders.csv");
            FileWriter readcsv_Line = new FileWriter("src/newLines.csv");

            ArrayList<InvoiceLine> newInvoiceLines = new ArrayList<>();
            for (InvoiceHeader invoiceHeader: invoiceHeaders
                 ) {
                readcsv_Header.append(invoiceHeader.toString_csv());
                newInvoiceLines.addAll(invoiceHeader.getInvoiceLines());


            }

            for (InvoiceLine invoiceLine: newInvoiceLines
            ) {
                readcsv_Line.append(invoiceLine.toString());
            }

            readcsv_Header.close();
            readcsv_Line.close();




        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}