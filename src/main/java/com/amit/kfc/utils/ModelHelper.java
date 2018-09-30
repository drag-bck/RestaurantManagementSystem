package com.amit.kfc.utils;

import com.amit.kfc.model.BaseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ModelHelper {
	
	@SuppressWarnings("unchecked")
	public static <T extends BaseModel> ArrayList<T> resultToModelList(ResultSet resultSet, String className) {
		ArrayList<T> models = new ArrayList<>();
		try {
			Class<T> modelClass = (Class<T>) Class.forName(className);
			while (resultSet.next()) {
				T model = modelClass.newInstance();
				model.parse(resultSet);
				models.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}
	
	public static ResultSet executeQueryForResult(PreparedStatement statement) {
		try {
			return statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean executeQuery(PreparedStatement statement) {
		try {
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
