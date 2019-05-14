package Searching;


import java.util.Date;

/**
 * 计算key的hashCode
 */
public class Transaction {

    private final String who = "";
    private final Date when = new Date();
    private final double amount = 0;

    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();

        return hash;
    }
}
