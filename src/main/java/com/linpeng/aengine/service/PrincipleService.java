package com.linpeng.aengine.service;

import com.linpeng.aengine.model.Disease;

public class PrincipleService {

	// static Connection conn;
	// static {
	// try {
	// conn = JdbcUtils.getConnection("org.h2.Driver",
	// "jdbc:h2:file:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1", "sa",
	// "");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	public Disease getOrSaveDisease(Disease disease) {
		// QueryRunner qr = new QueryRunner();
		// Disease d = getDisease(disease);
		// if (null == d) {
		// // save it
		// qr.insert(conn, "insert into disease(name) values(" +
		// disease.getName() + ")", null);
		// d = getDisease(disease);
		// }
		// return d;
		return null;
	}

	public Disease getDisease(Disease disease) {
		// QueryRunner qr = new QueryRunner();
		// String sql = "select id,name from disease where name = " +
		// disease.getName();
		// BeanHandler<Disease> beanHandler = new BeanHandler<>(Disease.class);
		// try {
		// return qr.query(conn, sql, beanHandler);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		return null;
	}
}
