package com.sda.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.sda.util.Constants.DB_SETTINGS;


public abstract class SessionUtil {
	private Session session;
	private Transaction transaction;

	public Session getSession() {
		return session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Session openSession() {
		return HibernateUtil.getSessionFactory(DB_SETTINGS).openSession();
	}

	public void openTransactionAndSession() {
		session = openSession();
		transaction = session.beginTransaction();
	}

	public void closeSession() {
		session.close();
	}

	public void closeTransactionAndSession() {
		transaction.commit();
		closeSession();
	}
}
