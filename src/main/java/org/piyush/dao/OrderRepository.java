package org.piyush.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.piyush.models.Cart;
import org.piyush.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected JdbcTemplate jdbc;

	@Autowired
	protected NamedParameterJdbcTemplate jdbcNamed;

	public Order getOrderById(long id) {
		Order o = this.jdbc.queryForObject("select id, user_id, cart_id, status from orders where id=?",
				orderMapper, id);
		return o;
	}
	
	public Order insertOrder(Cart c, String status, String deliveryAddress) {
		Order o;
		if(c.isValid()) {
			GeneratedKeyHolder holder = new GeneratedKeyHolder();
			this.jdbc.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement statement = con.prepareStatement(
							"insert into orders(cart_id, status, delivery_address) " + "values(?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
					statement.setLong(1, c.getId());
					statement.setString(2, status);
					statement.setString(3, deliveryAddress);
					return statement;
				}
			}, holder);
			long primaryKey = holder.getKey().longValue();
			o = this.getOrderById(primaryKey);
		} else {
			o = null;
		}
		return o;
	}

	private static final RowMapper<Order> orderMapper = new RowMapper<Order>() {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order o = new Order();
			o.setId(rs.getInt("id"));
			o.setUserId(rs.getInt("user_id"));
			o.setCartId(rs.getInt("cart_id"));
			o.setStatus(rs.getString("status"));
			return o;
		}
	};
}
