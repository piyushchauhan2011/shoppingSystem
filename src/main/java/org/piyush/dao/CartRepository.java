package org.piyush.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.piyush.models.Cart;
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
