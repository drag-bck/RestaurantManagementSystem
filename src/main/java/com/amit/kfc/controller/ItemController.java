package com.amit.kfc.controller;

import com.amit.kfc.model.Item;
import com.amit.kfc.utils.ModelHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ItemController {
    public ArrayList<Item> getItems() { return Models.getInstance().getItems(); }

    public void addItem(int category, String name, float cost) throws Exception {
        if (name == null)
            throw new Exception("Item Name cannot be Empty");
        name = name.trim();
        if (name.isEmpty())
            throw new Exception("Item Name cannot be Empty!");

        for (Item item : getItems()) {
            if (item.getName().equals(name)) {
                throw new Exception("Item Name already exists!");
            }
        }

        if (cost<=0)
            throw new Exception("Invalid Price");


        int id = getItems().isEmpty() ? 1 : getItems().get(getItems().size() - 1).getItemId();

        Item item = new Item();
        item.setItemId(id);
        item.setName(name);
        item.setCatId(category);
        item.setCost(cost);

        PreparedStatement query = item.getWriteQuery(Database.getConnection());
        if(!ModelHelper.executeQuery(query))
            throw new Exception("Item creation Failed!");

        getItems().add(item);
    }
}
