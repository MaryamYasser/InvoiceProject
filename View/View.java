package View;
import Controller.InvoiceController;

import javax.swing.*;
import java.awt.*;


public class View {
    public static void main(String[] args){
        InvoiceController controller = new InvoiceController();

        JFrame frame = new JFrame("Create JFrame Example");
        frame.setLocation(100, 100);
        frame.setSize(new Dimension(1024, 800));

        GridLayout layout = new GridLayout(1, 2);
        frame.setLayout(layout);


        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();


        String[] columnNames = { "No.", "Date", "Customer","Total" };
        Object[][] data2 = controller.getData();

        JTable jt=new JTable(data2,columnNames);

        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        panelLeft.add(sp);

        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelRight,BorderLayout.EAST);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);


    }
}
