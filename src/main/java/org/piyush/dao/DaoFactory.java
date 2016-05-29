package org.piyush.dao;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.piyush.models.*;

public class DaoFactory {

	private static DaoFactory df;
	private ProductDao pDao = null;
	private CartItemDao ciDao = null;
	private CartDao cDao = null;
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
	
	public CartItemDao getCartItemDao(){
		if (ciDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/shoppingSystem.properties"));		
				String className = properties.getProperty("dao.CartItemDaoName");
				if (className!=null){
					ciDao = (CartItemDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get CartItemInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					ciDao = new CartItemDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				ciDao =  new CartItemDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return ciDao;
			}
		}
		return ciDao;
	}
	
	public CartDao getCartDao(){
		if (cDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/shoppingSystem.properties"));		
				String className = properties.getProperty("dao.CartDaoName");
				if (className!=null){
					cDao = (CartDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					cDao = new CartDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				cDao =  new CartDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return cDao;
			}
		}
		return cDao;
	}
//	
//	public ProductDao getOrderDao(){
//		if (pDao == null){
//			Properties properties = new Properties() ;
//			try{
//				properties.load(this.getClass().getResourceAsStream("/shoppingSystem.properties"));		
//				String className = properties.getProperty("dao.ProductDaoName");
//				if (className!=null){
//					pDao = (ProductDao)Class.forName(className).newInstance();
//					log.info("Using " + className + " to get ProductInfo...");
//				}else{
//					log.info("property not found, using default implementation");
//					System.out.println("property not found, using default implementation");
//					pDao = new ProductDaoMemImpl();
//				}
//			}catch (Exception e){ 
//				log.info(e.getMessage());
//				e.printStackTrace();
//				pDao =  new ProductDaoMemImpl();
//				System.out.println("Exception, using default implementation");
//				return pDao;
//			}
//		}
//		return pDao;
//	}
//	
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

