package edu.poly.managerstudents.example.models;

public class Students {
    private String name;
    private int id ;
    private String email ;
    private String sdt;
    private String nganh;

    public Students() {
    }

    public Students(String name, String nganh, String email, String sdt ) {
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.nganh = nganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public Students(String name, String sdt, String email) {
        this.name = name;

        this.sdt = sdt;
        this.email = email;
    }

    public Students(int id, String name, String email, String sdt) {
        this.id = id;
        this.name = name;

        this.email = email;
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "Sinhvien{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                ", nganh='" + nganh + '\'' +
                '}';
    }
}
