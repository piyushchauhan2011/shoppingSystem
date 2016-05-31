package org.piyush.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.piyush.models.Cart;
import org.piyush.models.CartItem;
import org.piyush.models.Product;
import org.piyush.models.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Repository
public class CartRepository  {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    protected JdbcTemplate jdbc;

	public Cart createCart(final Cart c) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement("insert into carts values()", Statement.RETURN_GENERATED_KEYS);
		        return statement;
		    }
		}, holder);
		long primaryKey = holder.getKey().longValue();
		c.setId(primaryKey);
		return c;
	}
	
	public List<Product> getCartProducts(long cartId) {
		List<Product> products = this.jdbc.query(
		        "select id, title, description, image_url, price from products where id in"
		        + "(select product_id from cart_items where cart_items.cart_id = ?)",
		        productMapper, cartId);
		return products;
	}
	
	public Cart getCartById(long id) {
		Cart c = this.jdbc.queryForObject(
				"select id from carts where id=?",
				cartMapper, id);
		List<CartItem> cartItems = this.jdbc.query(
				"select cart_items.id, quantity, products.id, title, description, image_url, price from cart_items, products where cart_items.cart_id=? and cart_items.product_id = products.id",
				cartItemMapper, id);
		c.setCartItems(cartItems);
		return c;
	}
	
	public CartItem getCartItemById(long id) {
		CartItem ci = this.jdbc.queryForObject(
				"select cart_items.id, quantity, products.id, title, description, image_url, price from cart_items, products where cart_items.id=? and cart_items.product_id = products.id",
				cartItemMapper, id);
		return ci;
	}
	
	public Cart insertProduct(Cart c, Product product) {
		CartItem ci = c.isPresent(product);
		if(ci != null) {
			// Update the quantity
			ci.increaseQuantity();
			this.jdbc.update(
					"update cart_items set quantity = ? where id = ?", ci.getQuantity(), ci.getId());
		} else {
			// Insert as a new cartItem
			GeneratedKeyHolder holder = new GeneratedKeyHolder();
			this.jdbc.update(new PreparedStatementCreator() {
			    @Override
			    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			        PreparedStatement statement = con.prepareStatement("insert into cart_items(product_id, cart_id, quantity) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			        statement.setLong(1, product.getId());
			        statement.setLong(2, c.getId());
			        statement.setInt(3, 1);
			        return statement;
			    }
			}, holder);
			long primaryKey = holder.getKey().longValue();
			ci = this.getCartItemById(primaryKey);
		}
		
		ci.setProduct(product);
		c.insertCartItem(ci);
		return c;
	}
	
	private static final RowMapper<Cart> cartMapper = new RowMapper<Cart>() {
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart c = new Cart();
            c.setId(rs.getInt("id"));
            return c;
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
    
	private static final RowMapper<Product> productMapper = new RowMapper<Product>() {
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setImageUrl(rs.getString("image_url"));
            p.setPrice(rs.getDouble("price"));
            return p;
        }
    };
}
