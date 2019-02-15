package nl.psdcompany.duonavigationdrawer.example;

public class person {
    String c_name,sub_name,sec_name;
    Integer fees,duration;

    public person() {
    }

    public person(String c_name, String sub_name, String sec_name, Integer fees, Integer duration) {
        this.c_name = c_name;
        this.sub_name = sub_name;
        this.sec_name = sec_name;
        this.fees = fees;
        this.duration = duration;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
