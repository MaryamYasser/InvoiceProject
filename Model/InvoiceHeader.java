package Model;
import java.time.LocalDate;
import java.util.ArrayList;

public class InvoiceHeader {
    private int invoiceNum;
    private LocalDate invoiceDate;
    private String customerName;

    private ArrayList<InvoiceLine> invoiceLines;
    private float InvoiceTotal;

    public InvoiceHeader(int invoiceNum, LocalDate invoiceDate, String customerName) {
        this.setInvoiceNum(invoiceNum);
        this.setInvoiceDate(invoiceDate);
        this.setCustomerName(customerName);
        //this.setInvoiceTotal(invoiceTotal);
        this.invoiceLines = new ArrayList<>();
    }

    public String toString() {
        return  getInvoiceNum() + ", " + getInvoiceDate() + ", " + getCustomerName() + ", " + getInvoiceTotal();
    }

    public String toString_csv() {
        return  getInvoiceNum() + ", " + getInvoiceDate() + ", " + getCustomerName();
    }


    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getInvoiceTotal() {
        float total = 0;
        for (InvoiceLine invoiceLine: this.getInvoiceLines()
             ) {
            total += invoiceLine.getPrice() * invoiceLine.getCount();

        }

        return total;
    }

    public void setInvoiceTotal(float invoiceTotal) {


        InvoiceTotal = invoiceTotal;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}
