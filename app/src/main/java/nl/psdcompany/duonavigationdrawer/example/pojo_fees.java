package nl.psdcompany.duonavigationdrawer.example;

public class pojo_fees {
    String c_name;
    Integer fees,fees_paid;

    public pojo_fees() {
    }

    public pojo_fees(String c_name, Integer fees, Integer fees_paid) {
        this.c_name = c_name;
        this.fees = fees;
        this.fees_paid = fees_paid;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public Integer getFees_paid() {
        return fees_paid;
    }

    public void setFees_paid(Integer fees_paid) {
        this.fees_paid = fees_paid;
    }
}
