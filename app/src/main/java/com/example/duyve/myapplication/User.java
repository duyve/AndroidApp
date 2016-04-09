package com.example.duyve.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable
{

    /**
     * The class work can be used to store both work and school data.
     */
    public class Work{
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

    private ArrayList<String> skills;

    private ArrayList<String> actvities;

    private ArrayList<Work> education;

    private ArrayList<Work> experience;

    private ArrayList<Reference> references;


    public User(String email, String firstName, String lastName, String phone, String careerTitle, String address, ArrayList<String> skills, ArrayList<String> actvities, ArrayList<Work> education, ArrayList<Work> experience, ArrayList<Reference> references) {
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

    public User() {
        this.email = "example@mail.com";
        this.firstName = "Jane";
        this.lastName = "Doe";
        this.phone = "000-000-0000";
        this.careerTitle = "Business";
        this.address = "1234 Exampe Street";
        this.skills = new ArrayList<>();
        this.actvities = new ArrayList<>();
        this.education = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.references = new ArrayList<>();
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

    public ArrayList<Work> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Work> education) {
        this.education = education;
    }

    public ArrayList<Work> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<Work> experience) {
        this.experience = experience;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<Reference> references) {
        this.references = references;
    }

}
