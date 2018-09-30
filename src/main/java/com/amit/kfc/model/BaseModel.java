package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//TODO:Prepared Statement
public abstract class BaseModel {
	public abstract String getInitQuery();
	
	public abstract String getFetchQuery();
	
	public abstract void parse(ResultSet resultSet);
	
	public abstract PreparedStatement getWriteQuery(Connection connection) throws Exception;
	
	public abstract PreparedStatement getUpdateQuery() throws Exception;
}
