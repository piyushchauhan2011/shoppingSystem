package org.piyush.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.piyush.models.Product;
import org.piyush.models.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Repository
public class ProductRepository {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected JdbcTemplate jdbc;

	@Autowired
	protected NamedParameterJdbcTemplate jdbcNamed;

	public List<Product> getAllProducts() {
		List<Product> products = this.jdbc.query("select id, title, description, image_url, price from products",
				productMapper);

		List<Long> productIds = products.stream().map((Product p) -> p.getId()).collect(Collectors.toList());

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("productIds", productIds);

		List<Warehouse> warehouses = this.jdbcNamed.query("select id, product_id, location, quantity from warehouses"
				+ " where warehouses.product_id in (:productIds)", parameters, warehouseMapper);

		Map<Long, List<Warehouse>> warehousesGroupedByProductId = warehouses.stream().collect(Collectors
				.groupingBy(w -> w.getProductId(), Collectors.mapping((Warehouse w) -> w, Collectors.toList())));

		for (Product product : products) {
			product.setWarehouses(warehousesGroupedByProductId.get(product.getId()));
		}

		return products;
	}

	public Product getProductById(long id) {
		Product p = this.jdbc.queryForObject("select id, title, description, image_url, price from products where id=?",
				productMapper, id);
		List<Warehouse> warehouses = this.jdbc.query(
				"select id, product_id, location, quantity from warehouses where product_id=?", warehouseMapper, id);
		p.setWarehouses(warehouses);
		return p;
	}

	public Product insertProduct(final Product p) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(
						"insert into products(title, description, image_url, price)" + " values(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, p.getTitle());
				statement.setString(2, p.getDescription());
				statement.setString(3, p.getImageUrl());
				statement.setDouble(4, p.getPrice());
				return statement;
			}
		}, holder);
		long primaryKey = holder.getKey().longValue();
		Product product = this.getProductById(primaryKey);
		return product;
	}

	public Product updateProduct(long id, final Product p) {
		this.jdbc.update("update products set price = ? where id = ?", p.getPrice(), id);
		Product product = this.getProductById(id);
		return product;
	}

	public long deleteProduct(long id) {
		return this.jdbc.update("delete from products where id = ?", id);
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
