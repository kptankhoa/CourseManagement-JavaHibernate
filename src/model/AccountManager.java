package model;

import dao.AccountDAO;

public class AccountManager {
    public static Account activeAccount = null;

    //log in, set active account, return account if successfully, null if not
    public static Account logIn(Account acc) {
        Account resAcc = AccountDAO.getAccountByUsername(acc.getUsername());
        if ((resAcc != null) && (resAcc.getPassword().equals(acc.getPassword()))){
            activeAccount = resAcc;
            return resAcc;
        } else {
            return null;
        }
    }

    //return current active account if log out successfully, null if not
    public static Account logOut(){
        Account res = activeAccount;
        if(res!=null){
            activeAccount = null;
        }
        return res;
    }
}
