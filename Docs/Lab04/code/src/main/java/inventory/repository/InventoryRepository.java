package inventory.repository;


import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.StringTokenizer;

public class InventoryRepository {

	private static String filename = "data/items.txt";
	private PartInventory partInventory;
	private ProductInventory productInventory;

	public InventoryRepository(){
		this.partInventory = new PartInventory();
		this.productInventory = new ProductInventory();
		try {
			readParts();
			readProducts();
		} catch(NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	private void readParts() throws NullPointerException {
		ClassLoader classLoader = InventoryRepository.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		ObservableList<Part> listP = FXCollections.observableArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while((line = br.readLine())!= null) {
				Part part = getPartFromString(line);
				if (part != null)
					listP.add(part);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		partInventory.setItems(listP);
	}

	private Part getPartFromString(String line){
		Part part = null;
		if (line == null|| line.equals("")) return null;
		StringTokenizer st = new StringTokenizer(line, ",");
		String type = st.nextToken();
		if (type.equals("I")) {
			int id = Integer.parseInt(st.nextToken());
			partInventory.setAutoId(id);
			String name= st.nextToken();
			double price = Double.parseDouble(st.nextToken());
			int inStock = Integer.parseInt(st.nextToken());
			int minStock = Integer.parseInt(st.nextToken());
			int maxStock = Integer.parseInt(st.nextToken());
			int idMachine = Integer.parseInt(st.nextToken());
			part = new InhousePart(id, name, price, inStock, minStock, maxStock, idMachine);
		}
		if (type.equals("O")) {
			int id = Integer.parseInt(st.nextToken());
			partInventory.setAutoId(id);
			String name= st.nextToken();
			double price = Double.parseDouble(st.nextToken());
			int inStock = Integer.parseInt(st.nextToken());
			int minStock = Integer.parseInt(st.nextToken());
			int maxStock = Integer.parseInt(st.nextToken());
			String company = st.nextToken();
			part = new OutsourcedPart(id, name, price, inStock, minStock, maxStock, company);
		}
		return part;
	}

	private void readProducts() throws NullPointerException {
		ClassLoader classLoader = InventoryRepository.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());

		ObservableList<Product> listP = FXCollections.observableArrayList();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = br.readLine()) != null){
				Product product = getProductFromString(line);
				if (product != null)
					listP.add(product);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        productInventory.setItems(listP);
	}

	private Product getProductFromString(String line){
		Product product = null;
		if (line == null|| line.equals("")) return null;
		StringTokenizer st = new StringTokenizer(line, ",");
		String type = st.nextToken();
		if (type.equals("P")) {
			int id = Integer.parseInt(st.nextToken());
			productInventory.setAutoId(id);
			String name = st.nextToken();
			double price = Double.parseDouble(st.nextToken());
			int inStock = Integer.parseInt(st.nextToken());
			int minStock = Integer.parseInt(st.nextToken());
			int maxStock = Integer.parseInt(st.nextToken());
			String partIDs = st.nextToken();

			StringTokenizer ids = new StringTokenizer(partIDs,":");
			ObservableList<Part> list = FXCollections.observableArrayList();
			while (ids.hasMoreTokens()) {
				String idP = ids.nextToken();
				Part part = partInventory.lookupItem(idP);
				if (part != null)
					list.add(part);
			}
			product = new Product(id, name, price, inStock, minStock, maxStock, list);
			product.setAssociatedParts(list);
		}
		return product;
	}

	private void writeAll() {
		ClassLoader classLoader = InventoryRepository.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());

		BufferedWriter bw = null;
		ObservableList<Part> parts = partInventory.getItems();
		ObservableList<Product> products = productInventory.getItems();

		try {
			bw = new BufferedWriter(new FileWriter(file));
			for (Part p:parts) {
				System.out.println(p.toString());
				bw.write(p.toString());
				bw.newLine();
			}

			for (Product pr:products) {
				String line = pr.toString() + ",";
				ObservableList<Part> list = pr.getAssociatedParts();
				int index = 0;
				while(index < list.size() - 1){
					line = line + list.get(index).getId() + ":";
					index++;
				}
				if (index == list.size() - 1)
					line = line + list.get(index).getId();
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addPart(Part part){
		partInventory.addItem(part);
		writeAll();
	}

	public void addProduct(Product product){
		productInventory.addItem(product);
		writeAll();
	}

	public int getAutoPartId(){
		return partInventory.getAutoId();
	}

	public int getAutoProductId(){
		return productInventory.getAutoId();
	}

	public ObservableList<Part> getAllParts(){
		return partInventory.getItems();
	}

	public ObservableList<Product> getAllProducts(){
		return productInventory.getItems();
	}

	public Part lookupPart (String search){
		return partInventory.lookupItem(search);
	}

	public Product lookupProduct (String search){
		return productInventory.lookupItem(search);
	}

	public void updatePart(int partIndex, Part part){
		partInventory.updateItem(partIndex, part);
		writeAll();
	}

	public int getNumberOfProducts() {
		return productInventory.size();
	}

	public void updateProduct(int productIndex, Product product){
		productInventory.updateItem(productIndex, product);
		writeAll();
	}

	public void deletePart(Part part){
		partInventory.removeItem(part);
		writeAll();
	}

	public void deleteProduct(Product product){
		productInventory.removeItem(product);
		writeAll();
	}

	public void deleteProductById(Product product){
		productInventory.removeById(product);
		writeAll();
	}

	//public Inventory getInventory(){		return inventory;	}

	// public void setInventory(Inventory inventory){		this.inventory = inventory;	}

}
