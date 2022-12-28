package Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.FileOperations;
import Model.InvoiceLine;
import Model.InvoiceHeader;

public class InvoiceController {
    ArrayList<InvoiceHeader> HeaderData = new ArrayList<>();

    //CONSTRUCTOR INITIALIZES CONTROLLER WITH HEADER DATA FROM FILE
    public InvoiceController(){
        FileOperations controller = new FileOperations();
        this.HeaderData = controller.ReadHeaderCSV();

    }

    

    //getData transforms data to objects front end can read

   public Object[][] getData(){

       ArrayList<String[]> stringOverviewInvoices = new ArrayList<>();
       DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");


       for (InvoiceHeader invoiceHeader: HeaderData
            ) {
           stringOverviewInvoices.add(new String[]{Integer.toString(invoiceHeader.getInvoiceNum()),invoiceHeader.getInvoiceDate().format(df)
                   ,invoiceHeader.getCustomerName(), Float.toString(invoiceHeader.getInvoiceTotal())});
       }
       Object [][] myArray = new String[stringOverviewInvoices.size()][];
       stringOverviewInvoices.toArray(myArray);

       return myArray;


   }


   //getInvoiceLineData returns arraylist of InvoiceLines of specific header data Invoice
    public Object[][] getInvoiceLineData(int invoiceNum){

        ArrayList<String[]> stringInvoices = new ArrayList<>();
        InvoiceHeader chosenHeader = HeaderData.get(0);
        for (InvoiceHeader header: HeaderData
             ) {
            if (header.getInvoiceNum() == invoiceNum){
                 chosenHeader = header;
                break;
            }


        }


        for (InvoiceLine invoiceLine: chosenHeader.getInvoiceLines()
        ) {
            stringInvoices.add(new String[]{Integer.toString(invoiceLine.getId()),invoiceLine.getName()
                    , String.valueOf(invoiceLine.getPrice()), String.valueOf(invoiceLine.getCount()), Float.toString(chosenHeader.getInvoiceTotal())});
        }
        Object [][] myArray = new String[stringInvoices.size()][];
        stringInvoices.toArray(myArray);

        return myArray;


    }


    public void DeleteInvoice(int id){
        for (InvoiceHeader header : HeaderData
        ) {
            if (header.getInvoiceNum() == id) {
               HeaderData.remove(header);
                break;
            }

        }

    }

    public void CreateInvoice(int id, String type, float price, int quantity) {
        InvoiceLine invoiceLine = new InvoiceLine(id, type, price, quantity);


        for (InvoiceHeader header : HeaderData
        ) {
            if (header.getInvoiceNum() == id) {
                header.getInvoiceLines().add(invoiceLine);
                break;
            }

        }

    }



}
