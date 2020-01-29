import java.util.TreeMap;

public class BankStatement {
    //    private String accountType;
//    private String accountNumber;
//    private String currency;
//    private Date date;
//    private String transactionReference;
    private String contractor;
    private double arrivalMoney;
    private double expenses;
//    private TreeMap<String, Double> treeMap;


    public BankStatement(String contractor, double arrivalMoney, double expenses) {
//        this.accountType = accountType;
//        this.accountNumber = accountNumber;
//        this.currency = currency;
//        this.date = date;
//        this.transactionReference = transactionReference;
        this.contractor = contractor;
        this.arrivalMoney = arrivalMoney;
        this.expenses = expenses;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public double getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(double arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }


}
