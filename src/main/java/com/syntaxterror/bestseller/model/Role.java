package com.syntaxterror.bestseller.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rooliId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;

    private String rooliNimi;

    public String getRooliNimi() {
        return rooliNimi;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setRooliNimi(String rooliNimi) {
        this.rooliNimi = rooliNimi;
    }

    public void lisaaAccount(Account account){
        accounts.add(account);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
