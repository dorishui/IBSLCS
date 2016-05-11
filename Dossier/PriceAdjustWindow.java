import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class PriceAdjustWindow extends JFrame implements ActionListener{
	JLabel itemNumber, increaseBy, decreaseBy, percent, percent2;
	JTextField enterItemNumber, enterIncreaseBy, enterDecreaseBy;
	JButton increase, decrease;

	public PriceAdjustWindow(String title){

		super(title);

		itemNumber = new JLabel("Item Number:");
		increaseBy = new JLabel("Increase By:");
		decreaseBy = new JLabel("Decrease By:");
		percent = new JLabel("%");
		percent2 = new JLabel("%");
		enterItemNumber = new JTextField(4);
		enterIncreaseBy = new JTextField(7);
		enterDecreaseBy = new JTextField(7);
		increase = new JButton("Increment Price");
		decrease = new JButton("Decrement Price");

		setBounds(200,300,900,100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(itemNumber);
		add(enterItemNumber);
		add(increaseBy);
		add(enterIncreaseBy);
		add(percent);
		add(increase);
		add(decreaseBy);
		add(enterDecreaseBy);
		add(percent2);
		add(decrease);

	    enterItemNumber.setActionCommand("enterItemNumber");
	    enterIncreaseBy.setActionCommand("enterKeywords");
	    enterDecreaseBy.setActionCommand("search");
	    increase.setActionCommand("increase");
	    decrease.setActionCommand("decrease");

	    enterItemNumber.addActionListener(this);
	    enterIncreaseBy.addActionListener(this);
	    enterDecreaseBy.addActionListener(this);
	    increase.addActionListener(this);
	    decrease.addActionListener(this);
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

	private double getIncreaseBy(){ //returns the percentage to increment the price by
		double increaseBy = 0.0;
		try{
			increaseBy = Double.parseDouble(enterIncreaseBy.getText());

		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter percentage to increment by.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return increaseBy;
	}

	private double getDecreaseBy(){ //returns the percentage to decrement the price by
		double decreaseBy = 0.0;
		try{
			if(Double.parseDouble(enterDecreaseBy.getText())<100) //decreasing price by 100% will may cause program to crash
				decreaseBy = Double.parseDouble(enterDecreaseBy.getText());
			else
				JOptionPane.showMessageDialog(null, "You cannot decrease the price by 100 %.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter percentage to decrement by.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return decreaseBy;
	}

	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("increase")){
	  		int itemNumberJ = getItemNumber();

	  		if(AccessInventory.searchItem(itemNumberJ)!=-1){ //prevents from adjusting price of nonexisting item
				double increaseByJ = getIncreaseBy();

				Items reset = AccessInventory.getProduct(itemNumberJ);
				reset.increasePrice(increaseByJ);
				reset.getNewInvVal();
				int index = AccessInventory.searchItem(itemNumberJ);
				AccessInventory.products.set(index, reset);

				setVisible(false);
	  		}
	  		else
	  			JOptionPane.showMessageDialog(null, "An item with specified item number does not exist.", "Error Message", JOptionPane.ERROR_MESSAGE);

	  	}
	  	else if(evt.getActionCommand().equals("decrease")){
	  		int itemNumberJ = getItemNumber();

	  		if(AccessInventory.searchItem(itemNumberJ)!=-1){ //prevents from adjusting price of nonexisting item
				double decreaseByJ = getDecreaseBy();

				Items reset = AccessInventory.getProduct(itemNumberJ);
				reset.decreasePrice(decreaseByJ);
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