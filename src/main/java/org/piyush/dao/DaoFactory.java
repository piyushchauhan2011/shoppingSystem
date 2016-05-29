package org.piyush.dao;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.piyush.models.*;

public class DaoFactory {

	private static DaoFactory df;
	private ProductDao pDao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	String classname = this.getClass().getName();
	private DaoFactory(){
	}
	
	public static DaoFactory getInstance(){
		if (df == null)
			df = new DaoFactory();
		return df;
	}
	
	public ProductDao getProductDao(){
		if (pDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/shoppingSystem.properties"));		
				String className = properties.getProperty("dao.ProductDaoName");
				if (className!=null){
					pDao = (ProductDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					pDao = new ProductDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				pDao =  new ProductDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return pDao;
			}
		}
		return pDao;
	}	
	
	public static void main(String[] argv){
		DaoFactory df = DaoFactory.getInstance();
		ProductDao pDao = df.getProductDao();
		Product newBook = new Product("The Children Act","Ian MacEwan's new novel", null, 17.64);
		pDao.addProduct(newBook);
		
		log.info(pDao.getAllProducts());
		
		Product p = pDao.getProductById(3);
		
		log.info(p);
		
		newBook.setPrice(29.95);
		pDao.updateProduct(newBook);
		log.info(p);
	}
}

