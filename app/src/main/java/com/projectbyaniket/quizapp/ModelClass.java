package com.projectbyaniket.quizapp;

public class ModelClass {

    String Question;
    String OA;
    String OB;
    String OC;
    String OD;
    String Ans;

    public ModelClass() {
    }

    public ModelClass(String question, String OA, String OB, String OC, String OD, String ans) {
        Question = question;
        this.OA = OA;
        this.OB = OB;
        this.OC = OC;
        this.OD = OD;
        Ans = ans;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOA() {
        return OA;
    }

    public void setOA(String OA) {
        this.OA = OA;
    }

    public String getOB() {
        return OB;
    }

    public void setOB(String OB) {
        this.OB = OB;
    }

    public String getOC() {
        return OC;
    }

    public void setOC(String OC) {
        this.OC = OC;
    }

    public String getOD() {
        return OD;
    }

    public void setOD(String OD) {
        this.OD = OD;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }
}
