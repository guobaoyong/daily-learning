package Spring支持的常用数据库事务传播属性和事务隔离级别.tx.service;

public interface BookShopService {

	/**
	 * 买东西
	 * 
	 * @param userId
	 * @param isbn
	 */
	void purchase(int userId, String isbn);
}
