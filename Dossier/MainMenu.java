import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class MainMenu extends JFrame implements ActionListener
{
	JButton viewInventory, addItem, checkIn, checkOut, priceAdjust, save, delete;

	public MainMenu(String title)
	{
		super(title);

		viewInventory = new JButton("View Inventory");
		addItem = new JButton("Add Item");
		checkIn = new JButton("Check-In");
		checkOut = new JButton("Check-Out");
		priceAdjust = new JButton("Price Adjust");
		delete = new JButton("Delete an Item");
		save = new JButton("Save All Changes");

		setBounds(150,300,850,100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the program exits when "x" is pressed
		setLayout(new FlowLayout());

		add(viewInventory);
		add(addItem);
		add(checkIn);
		add(checkOut);
		add(priceAdjust);
		add(delete);
		add(save);

		viewInventory.setActionCommand("viewInventory");
		addItem.setActionCommand("addItem");
	    checkIn.setActionCommand("checkIn");
	    checkOut.setActionCommand("checkOut");
	    priceAdjust.setActionCommand("priceAdjust");
	    delete.setActionCommand("delete");
	    save.setActionCommand("save");

	    viewInventory.addActionListener(this);
	    addItem.addActionListener(this);
	    checkIn.addActionListener(this);
	    checkOut.addActionListener(this);
	    priceAdjust.addActionListener(this);
	    delete.addActionListener(this);
	    save.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) //pulls up various windows based on user option
	{
		if(evt.getActionCommand().equals("viewInventory"))
		{
			ViewInventoryWindow viewInventory = new ViewInventoryWindow("View Inventory");
		}
		else if(evt.getActionCommand().equals("addItem"))
		{
			AddItemWindow checkInNew = new AddItemWindow("Check-In: Add an Item");
		}
		else if(evt.getActionCommand().equals("checkIn"))
		{
			CheckInWindow checkInExisting = new CheckInWindow("Check-In: Edit Existing Item");
		}
		else if(evt.getActionCommand().equals("checkOut"))
		{
			CheckOutWindow checkOut = new CheckOutWindow("Check-Out");
		}
		else if(evt.getActionCommand().equals("priceAdjust"))
		{
			PriceAdjustWindow priceAdjust = new PriceAdjustWindow("Price Adjust");
		}
		else if(evt.getActionCommand().equals("save"))
		{
			AccessInventory.writeCSV();
		}
		else if(evt.getActionCommand().equals("delete"))
		{
			DeleteItemWindow deleteItem = new DeleteItemWindow("Delete an Item");
		}
	}
}