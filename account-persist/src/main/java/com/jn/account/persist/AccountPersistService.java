package com.jn.account.persist;

/*
增删改查的操作
 */
public interface AccountPersistService
{
    Account createAccount(Account account)
    	throws AccountPersistException;

    Account readAccount(String id)
    	throws AccountPersistException;

    Account updateAccount(Account account)
    	throws AccountPersistException;

    void deleteAccount(String id)
    	throws AccountPersistException;
}
