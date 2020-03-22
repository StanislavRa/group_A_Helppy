package com.sda.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class SessionUtil extends HibernateUtil {
	private Session session;
	private Transaction transaction;

	public SessionUtil(String hibernateConfigurationFilePath) {
		super(hibernateConfigurationFilePath);
	}

	public Session getSession() {
		return session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Session openSession() {
		return getSessionFactory().openSession();
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
