package org.piyush.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.piyush.models.Cart;
import org.piyush.models.CartItem;
import org.piyush.models.Order;
import org.piyush.models.Product;
import org.piyush.models.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	
	public List<Order> getAllOrders() {
		List<Order> orders = this.jdbc.query("select id, user_id, cart_id, status from orders",
				orderMapper);
		return orders;
	}
	
	public Order getOrderById(long id) {
		Order o = this.jdbc.queryForObject("select id, user_id, cart_id, status from orders where id=?",
				orderMapper, id);
		
		Cart c = o.getCart();
		
		List<CartItem> cartItems = this.jdbc.query(
				"select cart_items.id, quantity, products.id, title, description, image_url, price from cart_items, products where cart_items.cart_id=? and cart_items.product_id = products.id",
				cartItemMapper, c.getId());
		
		List<Long> productIds = cartItems.stream().map((CartItem ci) -> ci.getProduct().getId()).collect(Collectors.toList());
		productIds.add(-1L);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("productIds", productIds);

		List<Warehouse> warehouses = this.jdbcNamed.query("select id, product_id, location, quantity from warehouses"
				+ " where warehouses.product_id in (:productIds)", parameters, warehouseMapper);

		Map<Long, List<Warehouse>> warehousesGroupedByProductId = warehouses.stream().collect(Collectors
				.groupingBy(w -> w.getProductId(), Collectors.mapping((Warehouse w) -> w, Collectors.toList())));

		for (CartItem cartItem : cartItems) {
			Product product = cartItem.getProduct();
			product.setWarehouses(warehousesGroupedByProductId.get(product.getId()));
		}
		
		c.setCartItems(cartItems);
		
		o.setCart(c);
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
			Cart c = new Cart();
			o.setId(rs.getInt("id"));
			o.setUserId(rs.getInt("user_id"));
			c.setId(rs.getInt("cart_id"));
			o.setCart(c);
			o.setStatus(rs.getString("status"));
			return o;
		}
	};

	private static final RowMapper<CartItem> cartItemMapper = new RowMapper<CartItem>() {
		public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			CartItem ci = new CartItem();
			ci.setId(rs.getInt("cart_items.id"));
			ci.setQuantity(rs.getInt("quantity"));
			Product p = new Product();
			p.setId(rs.getInt("products.id"));
			p.setTitle(rs.getString("title"));
			p.setDescription(rs.getString("description"));
			p.setImageUrl(rs.getString("image_url"));
			p.setPrice(rs.getDouble("price"));
			ci.setProduct(p);
			return ci;
		}
	};

	private static final RowMapper<Warehouse> warehouseMapper = new RowMapper<Warehouse>() {
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			Warehouse w = new Warehouse();
			w.setId(rs.getInt("id"));
			w.setProductId(rs.getInt("product_id"));
			w.setLocation(rs.getString("location"));
			w.setQuantity(rs.getInt("quantity"));
			return w;
		}
	};
}
