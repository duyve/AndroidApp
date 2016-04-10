package com.example.duyve.myapplication;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User
{


    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String careerTitle;

    private String address;

    private String city;

    private String state;

    private String zip;

    private ArrayList<String> skills;

    private ArrayList<String> actvities;

    private ArrayList<Experience> education;

    private ArrayList<Experience> experience;

    private ArrayList<Reference> references;

    public User() {
//        this.email = "ANDROID";
//        this.firstName = "ANDROID";
//        this.lastName = "ANDROID";
//        this.phone = "ANDROID";
//        this.careerTitle = "ANDROID";
//        this.address = "ANDROID";
//        this.city = "ANDROID";
//        this.state = "AD";
//        this.zip = "ANDROID";
        this.skills = new ArrayList<>();
        this.actvities = new ArrayList<>();
        this.education = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.references = new ArrayList<>();
    }

    //START GETTERS AND SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCareerTitle() {
        return careerTitle;
    }

    public void setCareerTitle(String careerTitle) {
        this.careerTitle = careerTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getActvities() {
        return actvities;
    }

    public void setActvities(ArrayList<String> actvities) {
        this.actvities = actvities;
    }

    public ArrayList<Experience> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Experience> education) {
        this.education = education;
    }

    public ArrayList<Experience> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<Experience> experience) {
        this.experience = experience;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<Reference> references) {
        this.references = references;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    //END GETTERS AND SETTERS

    public void addActivity(String activity){
        this.actvities.add(activity);
    }

    public void addSkill(String skill){
        this.skills.add(skill);
    }

    public void addExperience(Experience experience){
        this.experience.add(experience);
    }

    public void addEducation(Experience education){
        this.education.add(education);
    }

    public void addReference(Reference reference){
        this.references.add(reference);
    }
}
