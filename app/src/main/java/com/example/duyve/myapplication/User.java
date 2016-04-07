package com.example.duyve.myapplication;

import java.sql.Ref;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class User {

    /**
     * The class work can be used to store both work and school data.
     */
    class Work{
        private String place;

        private Date startDate;

        private Date endDate;

        private String city;

        private String state;

        private String[] info;

        public Work(String place, Date startDate, Date endDate, String city, String state, String[] info) {
            this.place = place;
            this.startDate = startDate;
            this.endDate = endDate;
            this.city = city;
            this.state = state;
            this.info = info;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String[] getInfo() {
            return info;
        }

        public void setInfo(String[] info) {
            this.info = info;
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
    }

    class Reference{
        private String firstName;

        private String lastName;

        private String title;

        private String contact[];

        public Reference(String[] contact, String firstName, String lastName, String title) {
            this.contact = contact;
            this.firstName = firstName;
            this.lastName = lastName;
            this.title = title;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String[] getContact() {
            return contact;
        }

        public void setContact(String[] contact) {
            this.contact = contact;
        }
    }

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String careerTitle;

    private String address;

    private String[] skills;

    private String[] actvities;

    private Work[] education;

    private Work[] experience;

    private Reference[] references;


    public User(String email, String firstName, String lastName, String phone, String careerTitle, String address, String[] skills, String[] actvities, Work[] education, Work[] experience, Reference[] references) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.careerTitle = careerTitle;
        this.address = address;
        this.skills = skills;
        this.actvities = actvities;
        this.education = education;
        this.experience = experience;
        this.references = references;
    }

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

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getActvities() {
        return actvities;
    }

    public void setActvities(String[] actvities) {
        this.actvities = actvities;
    }

    public Work[] getEducation() {
        return education;
    }

    public void setEducation(Work[] education) {
        this.education = education;
    }

    public Work[] getExperience() {
        return experience;
    }

    public void setExperience(Work[] experience) {
        this.experience = experience;
    }

    public Reference[] getReferences() {
        return references;
    }

    public void setReferences(Reference[] references) {
        this.references = references;
    }

}
