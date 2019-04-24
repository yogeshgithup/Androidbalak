package nl.psdcompany.duonavigationdrawer.example;

public class pojo_report {
     String sub_name,exam_title,total_marks,obtained_marks;

    public pojo_report() {
    }

    public pojo_report(String sub_name, String exam_title, String total_marks, String obtained_marks) {
        this.sub_name = sub_name;
        this.exam_title = exam_title;
        this.total_marks = total_marks;
        this.obtained_marks = obtained_marks;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getExam_title() {
        return exam_title;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getObtained_marks() {
        return obtained_marks;
    }

    public void setObtained_marks(String obtained_marks) {
        this.obtained_marks = obtained_marks;
    }
}
