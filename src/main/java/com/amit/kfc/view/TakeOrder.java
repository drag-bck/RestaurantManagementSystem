package com.amit.kfc.view;

import com.amit.kfc.controller.Models;
import com.amit.kfc.controller.OrderController;
import com.amit.kfc.model.Category;
import com.amit.kfc.model.Item;
import com.amit.kfc.model.OrderItem;
import com.amit.kfc.model.Seller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.util.stream.Collectors;

public class TakeOrder extends JFrame {
	public TakeOrder() {
		Models.getInstance().init();
		String[] columns = {"Item", "Price", "Quantity", "Amount"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columns);
		
		orderController = new OrderController();
		orderItemsModel = new Vector<>();
		sellers = new Vector<>(Models.getInstance().getSellers());
		categories = new Vector<>(Models.getInstance().getCategories());
		initComponents();
	}
	
	private void initComponents() {
		
		itemCat = new JComboBox<>(categories);
		jLabel1 = new JLabel();
		jScrollPane1 = new JScrollPane();
		itemList = new JList<>();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		customerName = new JTextField();
		phoneNo = new JTextField();
		jLabel4 = new JLabel();
		price = new JLabel();
		jLabel5 = new JLabel();
		quantity = new JTextField();
		seller = new JComboBox<>(sellers);
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jPanel1 = new JPanel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		totalCost = new JLabel();
		placeOrder = new JButton();
		jScrollPane3 = new JScrollPane();
		jTable1 = new JTable();
		addItem = new JButton();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(1280, 720));
		setMinimumSize(new java.awt.Dimension(1280, 720));
		setPreferredSize(new java.awt.Dimension(1280, 720));
		
		itemCat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		itemCat.addItemListener(this::categoryChanged);
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("TAKE ORDER");
		
		itemList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane1.setViewportView(itemList);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("Customer Name");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setText("Phone No. ");
		
		customerName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		phoneNo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel4.setText("Price");
		
		price.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel5.setText("Quantity");
		
		quantity.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		seller.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel6.setText("Item Category");
		
		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel7.setText("Items");
		
		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel8.setText("ORDER");
		
		jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel9.setText("Total");
		
		totalCost.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		placeOrder.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		placeOrder.setText("Place Order");
		
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jTable1.setModel(tableModel);
		jScrollPane3.setViewportView(jTable1);
		
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addContainerGap(568, Short.MAX_VALUE)
												.addComponent(jLabel8))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(305, 305, 305)
												.addComponent(jLabel9)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(totalCost, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 72, Short.MAX_VALUE)))
								.addContainerGap(14, Short.MAX_VALUE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(22, 22, 22)
												.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(241, 241, 241)
												.addComponent(placeOrder)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel8)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(jLabel9)
										.addComponent(totalCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(placeOrder)
								.addContainerGap(60, Short.MAX_VALUE))
		);
		
		addItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		addItem.setText("Add Item");
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGap(0, 0, Short.MAX_VALUE)
												.addComponent(jLabel4)
												.addGap(27, 27, 27)
												.addComponent(price, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
												.addGap(62, 62, 62)
												.addComponent(jLabel5)
												.addGap(18, 18, 18)
												.addComponent(quantity, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
												.addGap(41, 41, 41))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addGap(53, 53, 53)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel1)
																		.addGroup(layout.createSequentialGroup()
																				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																						.addComponent(jLabel2)
																						.addComponent(jLabel3))
																				.addGap(18, 18, 18)
																				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																						.addComponent(customerName, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
																						.addComponent(phoneNo, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
																				.addGap(18, 18, 18)
																				.addComponent(seller, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))))
														.addGroup(layout.createSequentialGroup()
																.addGap(71, 71, 71)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel6)
																				.addGap(178, 178, 178)
																				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																						.addComponent(jLabel7)
																						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)))))
														.addGroup(layout.createSequentialGroup()
																.addGap(221, 221, 221)
																.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel1)
												.addGap(26, 26, 26)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(customerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(seller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(29, 29, 29)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
														.addComponent(phoneNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(jLabel7))
												.addGap(3, 3, 3)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(jLabel5)
																		.addComponent(quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, Short.MAX_VALUE)))
								.addContainerGap())
		);
		
		placeOrder.addActionListener(this::placeOrder);
		addItem.addActionListener(this::addItem);
		itemList.addListSelectionListener(this::itemChanged);
		
		pack();
		
		if (!categories.isEmpty()) {
			ItemEvent event = new ItemEvent(itemCat, 0, categories.get(0), ItemEvent.SELECTED);
			categoryChanged(event);
		}
		
	}
	
	private void itemChanged(ListSelectionEvent e) {
		Item item = itemList.getSelectedValue();
		price.setText(Float.toString(item.getCost()));
		quantity.setText("0");
	}
	
	private void placeOrder(ActionEvent event) {
		double amount = orderItemsModel.stream().collect(Collectors.summarizingDouble(OrderItem::getAmount)).getSum();
		try {
			int sellerId = ((Seller) seller.getSelectedItem()).getSellerId();
			orderController.addOrder(customerName.getText(), phoneNo.getText(), sellerId, amount);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void categoryChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Category category = (Category) event.getItem();
			
			Vector<Item> items = Models.getInstance()
					.getItems().stream()
					.filter((item) -> item.getCatId() == category.getCatId())
					.collect(Collectors.toCollection(Vector::new));
			
			itemModel = new DefaultListModel<>();
			for (Item item : items) {
				itemModel.addElement(item);
			}
			itemList.setModel(itemModel);
			if (!items.isEmpty()) {
				itemList.setSelectedIndex(0);
			}
		}
	}
	
	private void addItem(ActionEvent event) {
		try {
			Item item = itemList.getSelectedValue();
			int qty;
			try {
				qty = Integer.parseInt(quantity.getText());
				if (qty <= 0) throw new Exception();
			} catch (Exception e) {
				throw new Exception("Invalid Quantity");
			}
			OrderItem orderItem = new OrderItem(item, qty);
			orderItemsModel.add(orderItem);
			tableModel.addRow(orderItem.getStringArray());
			double cost = orderItemsModel.stream().collect(Collectors.summarizingDouble(OrderItem::getAmount)).getSum();
			totalCost.setText(Double.toString(cost));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private JButton addItem;
	private JTextField customerName;
	private JComboBox<Category> itemCat;
	private JList<Item> itemList;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane3;
	private JTable jTable1;
	private JTextField phoneNo;
	private JButton placeOrder;
	private JLabel price;
	private JTextField quantity;
	private JComboBox<Seller> seller;
	private JLabel totalCost;
	
	private Vector<Seller> sellers;
	private Vector<Category> categories;
	private DefaultListModel<Item> itemModel;
	private Vector<OrderItem> orderItemsModel;
	private OrderController orderController;
	private DefaultTableModel tableModel;
}
