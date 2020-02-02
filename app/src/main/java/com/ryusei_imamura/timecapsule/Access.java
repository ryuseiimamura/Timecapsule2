package com.ryusei_imamura.timecapsule;

public class Access {
    String aCapsuleName;
    String aGraduate;
    String aSchool;
    String aTeacher;
    String aAikotoba;
}

    public Access(String aGraduate, String aSchool, String aTeacher, String aAikotoba) {
        this.aAikotoba = aAikotoba;
        this.aSchool = aSchool;
        this.aGraduate = aGraduate;
        this.aTeacher = aTeacher;
    }

    public String getGraduate() {return aGraduate;}

    public void setGraduate(String aGraduate) {
        this.aGraduate = aGraduate;
    }

    public String getSchool() {return aSchool;}

    public void setSchool(String aSchool) {
        this.aSchool = aSchool;
    }

    public String getTeacher(){return aTeacher;}

    public void setTeacher(String aTeacher) {
        this.aTeacher = aTeacher;
    }

    public String getAikotoba(){return aAikotoba;}

    public void setAikotoba(String aAikotoba) {
        this.aAikotoba = aAikotoba;
    }
}