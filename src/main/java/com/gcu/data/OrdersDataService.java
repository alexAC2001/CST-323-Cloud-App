package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderModel>
{
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	/**
	 * Non-default constructor for constructor injection
	 */
	public OrdersDataService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * CRUD: finder to return all entities 
	 */
	@Override
	public List<OrderModel> findAll() 
	{
		String sql = "SELECT * FROM ORDERS";
		List<OrderModel> orders = new ArrayList<OrderModel>();
		try
		{
			// Execute SQL Query and loop over result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				orders.add(new OrderModel(srs.getLong("ID"), srs.getString("ORDER_NO"), srs.getString("PRODUCT_NAME"), srs.getFloat("PRICE"), srs.getInt("QUANTITY")));
			}
		}
		// This is bad.
		catch (Exception e)
		{
			// Always print it to a file or console, then throw your acception
			// Create a database exception and call it
			e.printStackTrace(); // End user should never see stack traces
		}
		return orders;
	}

	@Override
	public OrderModel findById(int id) {
		// Bad garbage
		return null;
	}

	/**
	 * CRUD: create an entity
	 */
	@Override
	public boolean create(OrderModel order) {
		String sql = "INSERT IN ORDERS(ORDER_NO, PRODUCT_NAME, PRICE QUANTITY) VALUES(?,?,?,?)";
		try
		{
			// Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity());
			
			// Return result of Insert
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * CRUD: update an entity
	 */
	@Override
	public boolean update(OrderModel t) {
		return false;
	}

	/**
	 * CRUD: delete an entity
	 */
	@Override
	public boolean delete(OrderModel t) {
		return false;
	}
	
	
}
