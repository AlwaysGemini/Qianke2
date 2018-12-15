package com.example.administrator.qianke;

public class Record {
    private String student_id;
    private String state;

    Record(String student_id,String state){
        this.student_id = student_id;
        this.state = state;
    }

    Record(){

    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
