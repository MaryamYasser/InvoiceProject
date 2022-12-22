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
    List <InvoiceLine> invoiceLines = new ArrayList<>();
    Map<Integer, Float> totalMap = new HashMap<Integer,Float>();

    ArrayList<String[]> stringInvoices = new ArrayList<>();
    


   public Object[][] getData(){

       ArrayList<String[]> stringOverviewInvoices = new ArrayList<>();
       DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       FileOperations controller = new FileOperations();
       ArrayList<InvoiceHeader> HeaderData = controller.ReadHeaderCSV();


       for (InvoiceHeader invoiceHeader: HeaderData
            ) {
           stringOverviewInvoices.add(new String[]{Integer.toString(invoiceHeader.getInvoiceNum()),invoiceHeader.getInvoiceDate().format(df)
                   ,invoiceHeader.getCustomerName(), Float.toString(invoiceHeader.getInvoiceTotal())});
       }
       Object [][] myArray = new String[stringOverviewInvoices.size()][];
       stringOverviewInvoices.toArray(myArray);

       return myArray;


   }


    public void DeleteInvoice(int id){
        InvoiceLine removedItem = null; //maybe null pointer exception
        for (InvoiceLine item: invoiceLines
             ) {
            if (item.getId() == id){
                 removedItem = item;
                break;
            }
        }
        invoiceLines.remove(removedItem);
        //remove price from total
    }

    public void CreateInvoice(int id, String type, float price, int quantity){
        InvoiceLine invoiceLine = new InvoiceLine(id, type, price, quantity);
        invoiceLines.add(invoiceLine);


        totalMap.put(invoiceLine.getId(), totalMap.containsKey(invoiceLine.getId()) ? totalMap.get(invoiceLine.getId())
                + (invoiceLine.getPrice() * invoiceLine.getCount()) : (invoiceLine.getPrice() * invoiceLine.getCount()));

    }



}
