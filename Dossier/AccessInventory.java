import java.io.*;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;

class AccessInventory{
	public static ArrayList<Items> products = new ArrayList();

	public static void readCSV(){ //called by Dossier.java to read in inventoryDatabase.csv
		Scanner input = null;
		File inventory = new File("inventoryDatabase.csv");
		try{
			input = new Scanner(inventory);
		}
		catch(FileNotFoundException e){
			System.out.println("File does not exist.");
		}
		while(input.hasNextLine()){
			StringTokenizer st = new StringTokenizer(input.nextLine(), "@~"); //splits string by delimiter @~

			while(st.hasMoreTokens()){
				String itemNumber = st.nextToken();
				String ppu = st.nextToken();
				String quantity	= st.nextToken();
				String invVal = st.nextToken();
				String category	= st.nextToken();
				String productSpecs	= st.nextToken();
				String supplier	= st.nextToken();

				int itemNum = Integer.parseInt(itemNumber);
				double pricePerUnit = Double.parseDouble(ppu);
				int numProducts = Integer.parseInt(quantity);
				double inventoryVal = Double.parseDouble(invVal);

				addItem(itemNum, pricePerUnit, numProducts, inventoryVal, category, productSpecs, supplier);
			}
		}
	}

	public static void addItem(int itemNumber, double ppu, int quantity, double invVal, String category,
		String productSpecs, String supplier){ //adds the new product to product ArrayList
		Items newProduct = new Items(itemNumber, ppu, quantity, invVal, category, productSpecs, supplier);

		products.add(newProduct);

		itemNumSort(); //re-sorts inventory by ascending item number after adding an item
	}

	//the following getItemArray methods return sorted ArrayList

	public static ArrayList<Items> getItemArray(){return products;} //returns products ArrayList (Ascending Item Number)

	public static ArrayList<Items> getItemArrayCategoryAsc(){return categorySortAscending();}

	public static ArrayList<Items> getItemArraySupplierAsc(){return supplierSortAscending();}

	public static ArrayList<Items> getItemArrayQuantityAsc(){return quantitySortAscending();}

	public static ArrayList<Items> getItemArrayInvValAsc(){return invValSortAscending();}

	public static ArrayList<Items> getItemArrayPpuAsc(){return ppuSortAscending();}

	public static ArrayList<Items> getItemArrayCategoryDes(){return categorySortDescending();}

	public static ArrayList<Items> getItemArraySupplierDes(){return supplierSortDescending();}

	public static ArrayList<Items> getItemArrayQuantityDes(){return quantitySortDescending();}

	public static ArrayList<Items> getItemArrayInvValDes(){return invValSortDescending();}

	public static ArrayList<Items> getItemArrayPpuDes(){return ppuSortDescending();}

	public static ArrayList<Items> getItemArrayItemNumDes(){return itemNumberSortDescending();}

	public static ArrayList<Items> categorySortAscending(){ //sorts ArrayList products by Category and returns ArrayList named sorted

		ArrayList<Items> sorted = new ArrayList();
		sorted.addAll(products);
		int n;
		boolean stop = true;
		String temp;

		while(stop){
			stop = false;
			for(n=0;n<sorted.size()-1;n++){
				if ((sorted.get(n).category.compareToIgnoreCase(sorted.get(n+1).category)>0)){
						Collections.swap(sorted,n,n+1);
						stop = true;
					}
			}
		}
		return sorted;
	}

		public static ArrayList<Items> categorySortDescending(){ //sorts ArrayList products by Category and returns ArrayList named sorted

		ArrayList<Items> sorted = new ArrayList();
		sorted.addAll(products);
		int n;
		boolean stop = true;
		String temp;

		while(stop){
			stop = false;
			for(n=0;n<sorted.size()-1;n++){
				if ((sorted.get(n).category.compareToIgnoreCase(sorted.get(n+1).category)<0)){
						Collections.swap(sorted,n,n+1);
						stop = true;
					}
			}
		}
		return sorted;
	}

	public static ArrayList<Items> supplierSortAscending(){ //sorts ArrayList products by Supplier and returns ArrayList named sorted

		ArrayList<Items> sorted = new ArrayList();
		sorted.addAll(products);
		int n;
		boolean stop = true;
		String temp;

		while(stop){
			stop = false;
			for(n=0;n<sorted.size()-1;n++){
				if ((sorted.get(n).supplier.compareToIgnoreCase(sorted.get(n+1).supplier)>0)){
						Collections.swap(sorted,n,n+1);
						stop = true;
					}
			}
		}
		return sorted;
	}

	public static ArrayList<Items> supplierSortDescending(){ //sorts ArrayList products by Supplier and returns ArrayList named sorted

		ArrayList<Items> sorted = new ArrayList();
		sorted.addAll(products);
		int n;
		boolean stop = true;
		String temp;

		while(stop){
			stop = false;
			for(n=0;n<sorted.size()-1;n++){
				if ((sorted.get(n).supplier.compareToIgnoreCase(sorted.get(n+1).supplier)<0)){
						Collections.swap(sorted,n,n+1);
						stop = true;
					}
			}
		}
		return sorted;
	}

	public static void itemNumSort(){ //sorts ArrayList products by Item Number in ascending order

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).itemNumber>products.get(k+1).itemNumber){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static void itemNumSortDescending(){ //sorts ArrayList products by Item Number in descending order

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).itemNumber<products.get(k+1).itemNumber){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static ArrayList<Items> itemNumberSortDescending(){ //returns ArrayList named sorted after itemNumSortDescending()

		ArrayList<Items> sorted = new ArrayList();
		itemNumSortDescending();
		sorted.addAll(products);
		return sorted;
	}

	public static ArrayList<Items> quantitySortAscending(){ //returns ArrayList named sorted after numProductsSortAscending()

		ArrayList<Items> sorted = new ArrayList();
		numProductsSortAscending();
		sorted.addAll(products);
		return sorted;
	}

	public static ArrayList<Items> quantitySortDescending(){ //returns ArrayList named sorted after numProductsSortDescending()

		ArrayList<Items> sorted = new ArrayList();
		numProductsSortDescending();
		sorted.addAll(products);
		return sorted;
	}

	public static void numProductsSortAscending(){ //sorts ArrayList products by Quantity

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).quantity>products.get(k+1).quantity){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static void numProductsSortDescending(){ //sorts ArrayList products by Quantity

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).quantity<products.get(k+1).quantity){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static ArrayList<Items> ppuSortAscending(){ //returns ArrayList named sorted after pricePerUnitSortAscending()

		ArrayList<Items> sorted = new ArrayList();
		pricePerUnitSortAscending();
		sorted.addAll(products);
		return sorted;
	}

	public static ArrayList<Items> ppuSortDescending(){ //returns ArrayList named sorted after pricePerUnitSortDescending()

		ArrayList<Items> sorted = new ArrayList();
		pricePerUnitSortDescending();
		sorted.addAll(products);
		return sorted;
	}

	public static void pricePerUnitSortAscending(){ //sorts ArrayList products by Price Per Unit

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).ppu>products.get(k+1).ppu){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static void pricePerUnitSortDescending(){ //sorts ArrayList products by Price Per Unit

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).ppu<products.get(k+1).ppu){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static ArrayList<Items> invValSortAscending(){ //returns ArrayList named sorted after inventoryValSortAscending()

		ArrayList<Items> sorted = new ArrayList();
		inventoryValSortAscending();
		sorted.addAll(products);
		return sorted;
	}

	public static ArrayList<Items> invValSortDescending(){ //returns ArrayList named sorted after inventoryValSortDescending()

		ArrayList<Items> sorted = new ArrayList();
		inventoryValSortDescending();
		sorted.addAll(products);
		return sorted;
	}

	public static void inventoryValSortAscending(){ //sorts ArrayList products by Total Inventory Value

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).invVal>products.get(k+1).invVal){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	public static void inventoryValSortDescending(){ //sorts ArrayList products by Total Inventory Value

		int k;
		boolean sorted = false;
		while(!sorted){
			sorted = true;
			for(k=0;k<products.size()-1;k++)
				if(products.get(k).invVal<products.get(k+1).invVal){
					swap(k, k+1);
					sorted = false;
				}
		}
	}

	private static void swap(int x, int y){ //swap method utilized by the various sort methods

		Items temp = products.get(x);
		products.set(x, products.get(y));
		products.set(y,temp);
	}

	public static Items getProduct(int searchNum){ //returns the object which has the item number equal to searchNum

		int hi = products.size()-1;
		int lo =0;
		int mid = 0;
		Items search = new Items();
		boolean found = false;
		while(!found&&lo<=hi){
			mid = (hi + lo)/2;
			search = new Items(products.get(mid));
			if(search.itemNumber == searchNum)
				found = true;
			else{
				if(search.itemNumber < searchNum)
					lo = mid + 1;
				else
					hi = mid-1;
			}
		}
		if(found)
			return search;
		else
			return null;
	}

	public static int searchItem(int pdtNum){ //returns the index of the object which has the item number equal to pdtNum

		int index = -1;
		boolean found = false;
		for(int k = 0; k<products.size()&&!found;k++){
			Items search = products.get(k);
			if(search.getItemNumber() == pdtNum)
				index = k;
		}
		return index;
	}

	public static void deleteItem(int pdtNum) //deletes an item with the specified item number, pdtNum
	{
		boolean found = false;
		int n;
		for(n=0;n<products.size()&&!found;n++)
		{
			Items delete = products.get(n+1);
			if(delete.itemNumber==pdtNum)
				found=true;
		}
		if(found)
			products.remove(n);
	}

	public static void writeCSV(){ //Called by MainMenu to write to inventoryDatabase.csv when "Save All Changes" is pressed

		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("inventoryDatabase.csv"));
			for(int n=0;n<products.size();n++){
				Items save = new Items(products.get(n));
				out.write(save.itemNumber + "@~");
				out.write(save.ppu + "@~");
				out.write(save.quantity + "@~");
				out.write(save.invVal + "@~");
				out.write(save.category + "@~");
				out.write(save.productSpecs + "@~");
				out.write(save.supplier + "@~");
				out.newLine();
			}
			out.close();
		}
		catch(Exception e){
			System.out.println("Error while saving.");
		}
    }
}