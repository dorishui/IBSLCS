import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class CheckOutWindow extends JFrame implements ActionListener{
	JLabel itemNumber, quantitySold;
	JTextField enterItemNumber, enterQuantitySold;
	JButton ok;

	public CheckOutWindow(String title){
		super(title);

		itemNumber = new JLabel("Item Number:");
		quantitySold =new JLabel("Quantity Sold:");
		enterItemNumber = new JTextField(10);
		enterQuantitySold = new JTextField(10);
		ok = new JButton("OK");

		setBounds(400,300,250,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(itemNumber);
		add(enterItemNumber);
		add(quantitySold);
		add(enterQuantitySold);
		add(ok);

	    enterItemNumber.setActionCommand("enterItemNumber");
	    enterQuantitySold.setActionCommand("enterQuantitySold");
	    ok.setActionCommand("ok");

	    enterItemNumber.addActionListener(this);
	    enterQuantitySold.addActionListener(this);
	    ok.addActionListener(this);
	}

	private int getItemNumber(){ //returns item number from GUI text field
		int itemNumber = 0;
		try{
			itemNumber = Integer.parseInt(enterItemNumber.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter the item number.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return itemNumber;
	}

	private int getQuantitySold(){ //returns quantity sold from GUI text field
		int quantitySold = 0;
		try{
			quantitySold = Integer.parseInt(enterQuantitySold.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter quantity sold.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return quantitySold;
	}

	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("ok")){
			int itemNumberJ = getItemNumber();
			if(AccessInventory.searchItem(itemNumberJ)!=-1){ //searchItem() is used to prevent checking out a nonexisting item
				int quantitySoldJ = getQuantitySold();

				Items reset = AccessInventory.getProduct(itemNumberJ);
				reset.checkOut(quantitySoldJ);
				reset.getNewInvVal();
				int index = AccessInventory.searchItem(itemNumberJ);
				AccessInventory.products.set(index, reset);

				setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(null, "An item with specified item number does not exist.", "Error Message", JOptionPane.ERROR_MESSAGE);
	  	}
	}
}