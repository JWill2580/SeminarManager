/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;


public class Seminar {
    
   private String seminarID;
   private String topic;
   private String title;
   private String abstracts;
   private String location;
   private String modality;
   private String date;

    public String getSeminarID() {
        return seminarID;
    }

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public String getLocation() {
        return location;
    }

    public String getModality() {
        return modality;
    }

    public String getDate() {
        return date;
    }

    public void setSeminarID(String seminarID) {
        this.seminarID = seminarID;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
   
    
}