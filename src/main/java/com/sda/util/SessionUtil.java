package com.sda.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class SessionUtil {
	private Session session;
	private Transaction transaction;
	public String configFile;

	public SessionUtil(String configFile) {
		this.configFile = configFile;
	}

	public Session getSession() {
		return session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Session openSession() {
		return HibernateUtil.getSessionFactory(configFile).openSession();
	}

	public Session openTransactionAndSession() {
		session = openSession();
		transaction = session.beginTransaction();
		return session;
	}

	public void closeSession() {
		session.close();
	}

	public void closeTransactionAndSession() {
		transaction.commit();
		closeSession();
	}
}
