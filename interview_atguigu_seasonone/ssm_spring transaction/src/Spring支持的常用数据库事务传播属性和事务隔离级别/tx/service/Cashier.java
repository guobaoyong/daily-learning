package Spring支持的常用数据库事务传播属性和事务隔离级别.tx.service;

import java.util.List;

public interface Cashier {

	/**
	 * 去结账
	 * 
	 * @param userId
	 * @param isbns
	 */
	void checkout(int userId, List<String> isbns);
}
