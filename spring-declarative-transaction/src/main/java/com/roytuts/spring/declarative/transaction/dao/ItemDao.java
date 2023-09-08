package com.roytuts.spring.declarative.transaction.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.declarative.transaction.model.Item;
import com.roytuts.spring.declarative.transaction.row.mapper.ItemRowMapper;

@Repository
public class ItemDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Item> getItems() {
		String sql = "SELECT * FROM item";
		List<Item> items = new ArrayList<>();
		items = jdbcTemplate.query(sql, new ItemRowMapper());
		return items;
	}

	public void addItem(Item item) {
		String sql = "INSERT INTO item(item_id, item_name, item_desc, item_price) VALUES (?,?, ?, ?);";
		jdbcTemplate.update(sql,
				new Object[] { item.getItemId(), item.getItemName(), item.getItemDesc(), item.getItemPrice() });
	}

	public void updateItem(Item item) {
		String sql = "UPDATE item SET item_name=?, item_desc=?, item_price=? WHERE item_id=?;";
		jdbcTemplate.update(sql,
				new Object[] { item.getItemName(), item.getItemDesc(), item.getItemPrice(), item.getItemId() });
	}

	public void deleteItem(Item item) throws Exception {
		String sql = "DELETE FROM item WHERE item_id=?;";
		jdbcTemplate.update(sql, new Object[] { item.getItemId() });
		throw new Exception();
	}

}
