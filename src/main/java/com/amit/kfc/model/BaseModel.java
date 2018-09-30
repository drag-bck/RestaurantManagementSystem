package com.amit.kfc.model;

import java.sql.ResultSet;

public abstract class BaseModel {
	public abstract String getInitQuery();
	
	public abstract String getFetchQuery();
	
	public abstract void parse(ResultSet resultSet);
	
	public abstract String getWriteQuery();
	
	public abstract String getUpdateQuery();
}
