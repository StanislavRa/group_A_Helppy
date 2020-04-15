package com.sda.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class SessionUtil {
	private Session session;
	private Transaction transaction;
	private final String CONFIG_FILE;

	public SessionUtil(String CONFIG_FILE) {
		this.CONFIG_FILE = CONFIG_FILE;
	}

	public Session getSession() {
		return session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Session openSession() {
		return HibernateUtil.getSessionFactory(CONFIG_FILE).openSession();
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
