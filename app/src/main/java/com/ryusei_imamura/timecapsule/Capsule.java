package com.ryusei_imamura.timecapsule;

public  class Capsule {
    String graduate;
    String school;
    String teacher;
    String aikotoba;

    public Capsule() {

    }

    public Capsule(String graduate, String school, String teacher, String aikotoba) {
        this.aikotoba = aikotoba;
        this.school = school;
        this.graduate = graduate;
        this.teacher = teacher;
    }

    public String getGraduate() {return graduate;}

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getSchool() {return school;}

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeacher(){return teacher;}

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAikotoba(){return aikotoba;}

    public void setAikotoba(String aikotoba) {
        this.aikotoba = aikotoba;
    }
}