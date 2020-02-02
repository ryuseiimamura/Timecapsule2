package com.ryusei_imamura.timecapsule;

public class Access {
    String aCapsuleName;
    String aGraduate;
    String aSchool;
    String aTeacher;
    String aAikotoba;


    public Access(){

    }

    public Access(String aGraduate, String aSchool, String aTeacher, String aAikotoba) {
        this.aAikotoba = aAikotoba;
        this.aSchool = aSchool;
        this.aGraduate = aGraduate;
        this.aTeacher = aTeacher;
    }

    public String getaCapsuleName() { return aCapsuleName; }

    public void setaCapsuleName(String aCapsuleName) { this.aCapsuleName = aCapsuleName; }

    public String getaAikotoba() { return aAikotoba; }

    public void setaAikotoba(String aAikotoba) { this.aAikotoba = aAikotoba; }

    public String getaGraduate() { return aGraduate; }

    public void setaGraduate(String aGraduate) { this.aGraduate = aGraduate; }

    public String getaSchool() { return aSchool; }

    public void setaSchool(String aSchool) { this.aSchool = aSchool; }

    public String getaTeacher() { return aTeacher; }

    public void setaTeacher(String aTeacher) { this.aTeacher = aTeacher; }
}