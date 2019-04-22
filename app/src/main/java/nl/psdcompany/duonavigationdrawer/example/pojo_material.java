package nl.psdcompany.duonavigationdrawer.example;

public class pojo_material {
    String c_name,sub_name,title,materialtype,file_path;

    public pojo_material() {
    }

    public pojo_material(String c_name, String sub_name, String title, String materialtype, String file_path) {
        this.c_name = c_name;
        this.sub_name = sub_name;
        this.title = title;
        this.materialtype = materialtype;
        this.file_path = file_path;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
