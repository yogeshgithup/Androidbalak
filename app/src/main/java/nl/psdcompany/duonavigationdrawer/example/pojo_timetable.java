package nl.psdcompany.duonavigationdrawer.example;

public class pojo_timetable {

    String day,shift,slot,batchname;

    public pojo_timetable() {
    }

    public pojo_timetable(String day, String shift, String slot, String batchname) {
        this.day = day;
        this.shift = shift;
        this.slot = slot;
        this.batchname = batchname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }
}
