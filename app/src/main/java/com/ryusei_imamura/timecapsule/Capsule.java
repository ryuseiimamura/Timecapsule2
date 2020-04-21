package com.ryusei_imamura.timecapsule;

public class Capsule {
    String graduate;
    String school;
    String teacher;
    String aikotoba;
    Long openDate;
//
//    String message;
//    String userName;

    public Capsule() {

    }

    public Capsule(String graduate, String school, String teacher, String aikotoba, Long openDate) {
        this.aikotoba = aikotoba;
        this.school = school;
        this.graduate = graduate;
        this.teacher = teacher;
        this.openDate = openDate;
//        this.userName = userName;
//        this.message = message;

    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAikotoba() {
        return aikotoba;
    }

    public void setAikotoba(String aikotoba) {
        this.aikotoba = aikotoba;
    }

    public Long getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Long openDate) { this.openDate = openDate; }

//このアプローチじゃあキツイかもね
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
}
