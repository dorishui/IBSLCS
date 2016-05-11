public class Items{
	int itemNumber;
	double ppu;
	int quantity;
	double invVal;
	String category;
	String productSpecs;
	String supplier;

	public Items(int itemNumber, double ppu, int quantity, double invVal, String category,
				String productSpecs, String supplier){ //constructor
		this.itemNumber = itemNumber;
		this.ppu = ppu;
		this.quantity = quantity;
		this.invVal = invVal;
		this.category = category;
		this.productSpecs = productSpecs;
		this.supplier = supplier;
	}

	public Items(Items temp){ //copy constructor
		this.itemNumber = temp.itemNumber;
		this.ppu = temp.ppu;
		this.quantity = temp.quantity;
		this.invVal = temp.invVal;
		this.category = temp.category;
		this.productSpecs = temp.productSpecs;
		this.supplier = temp.supplier;
	}

	public Items(){ //default constructor
	}

	public void checkInExisting(int quantityAdded){ //Called by CheckInWindow to increment item quantity by integer value
		quantity = quantity + quantityAdded;
	}

	public void checkOut(int quantitySold){ //Called by CheckOutWindow to decrement item quantity by integer value
		quantity = quantity-quantitySold;
	}

	public void getNewInvVal(){ //Called after check-in, check-out, increase price, and decrease price to update inventory value
		invVal = ppu*quantity;
	}

	public void increasePrice(double increaseBy){ //Called by PriceAdjustWindow to increment price by specified percentage
		ppu = ppu * (1+(increaseBy/100));
	}

	public void decreasePrice(double decreaseBy){ //Called by PriceAdjustWindow to decrement price by specified percentage
		ppu = ppu - (ppu*(decreaseBy/100));
	}

	public int getItemNumber(){ //called by searchItem in AccessInventory class
		return itemNumber;
	}


}