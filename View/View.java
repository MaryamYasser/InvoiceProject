package View;
import Controller.InvoiceController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class View {
    public static void main(String[] args){
        InvoiceController controller = new InvoiceController();

        JFrame frame = new JFrame("Create JFrame Example");
        frame.setLocation(100, 100);
        frame.setSize(new Dimension(1024, 800));
        GridLayout layout = new GridLayout(1, 2);
        frame.setLayout(layout);

        // LEFT PANEL
        JPanel panelLeft = new JPanel();

        String[] columnNames = { "No.", "Date", "Customer","Total" };
        Object[][] data2 = controller.getData();
        JTable jt=new JTable(data2,columnNames);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        panelLeft.add(sp);

        JButton CreateButton = new JButton("Create Invoice");
        panelLeft.add(CreateButton);

        JButton DeleteButton = new JButton("Delete Invoice");
        panelLeft.add(DeleteButton);



        frame.add(panelLeft, BorderLayout.WEST);

        // RIGHT PANEL

        JPanel panelRight = new JPanel();
        GridLayout layout_Right = new GridLayout(2,1);
        panelRight.setLayout(layout_Right);

        JPanel panelRightNorth = new JPanel();
        GridLayout layout_RightNorth = new GridLayout(4, 2);
        panelRightNorth.setLayout(layout_RightNorth);




        JLabel invoiceNum = new JLabel("Invoice Num");
        JTextField field_InvoiceNum = new JTextField(20);
        field_InvoiceNum.setEditable(false);

        JLabel invoiceDate = new JLabel("Invoice Date");
        JTextField field_InvoiceDate = new JTextField(20);

        JLabel invoiceCustomerName = new JLabel("Invoice Customer Name");
        JTextField field_InvoiceCustomerName = new JTextField(20);

        JLabel invoiceTotal = new JLabel("Invoice Total");
        JTextField field_InvoiceTotal = new JTextField(20);
        field_InvoiceTotal.setEditable(false);

        panelRightNorth.add(invoiceNum);
        panelRightNorth.add(field_InvoiceNum);

        panelRightNorth.add(invoiceDate);
        panelRightNorth.add(field_InvoiceDate);

        panelRightNorth.add(invoiceCustomerName);
        panelRightNorth.add(field_InvoiceCustomerName);

        panelRightNorth.add(invoiceTotal);
        panelRightNorth.add(field_InvoiceTotal);

        JPanel panelRightSouth = new JPanel();
        String[] columnNames_R = { "No.", "Item Name", "Item Price","Count","Item Total" };

        DefaultTableModel tableModel = new DefaultTableModel(controller.getInvoiceLineData(0),columnNames_R);

        JTable jt_south=new JTable();
        jt_south.setModel(tableModel);
        jt_south.setBounds(30,40,400,300);

        JScrollPane sp_right=new JScrollPane(jt_south);
        //panelLeft.add(sp);
        panelRightSouth.add(sp_right);







        panelRight.add(panelRightNorth,BorderLayout.NORTH);
        panelRight.add(panelRightSouth,BorderLayout.SOUTH);
        frame.add(panelRight,BorderLayout.EAST);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        jt.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                String selected_InvoiceNum = (String) jt.getValueAt(jt.getSelectedRow() , 0);
                String selected_InvoiceDate = (String) jt.getValueAt(jt.getSelectedRow() , 1);
                String selected_InvoiceName = (String) jt.getValueAt(jt.getSelectedRow() , 2);
                String selected_InvoiceTotal = (String) jt.getValueAt(jt.getSelectedRow() , 3);

                System.out.println(selected_InvoiceNum);
                field_InvoiceNum.setText(selected_InvoiceNum);
                field_InvoiceDate.setText(selected_InvoiceDate);
                field_InvoiceCustomerName.setText(selected_InvoiceName);
                field_InvoiceTotal.setText(selected_InvoiceTotal);

                DefaultTableModel tableModel = new DefaultTableModel(controller.getInvoiceLineData(Integer.parseInt(selected_InvoiceNum)),columnNames_R);

                jt_south.setModel(tableModel);

                DeleteButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //your actions
                        controller.DeleteInvoice(Integer.parseInt(selected_InvoiceNum));
                        DefaultTableModel tableModel_ = new DefaultTableModel(controller.getData(),columnNames);

                        jt.setModel(tableModel_);

                    }
                });

                CreateButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //your actions
                        //controller.DeleteInvoice(Integer.parseInt(selected_InvoiceNum));
                       // DefaultTableModel tableModel_ = new DefaultTableModel(controller.getData(),columnNames);

                       // jt.setModel(tableModel_);

                    }
                });

            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

    }


}
