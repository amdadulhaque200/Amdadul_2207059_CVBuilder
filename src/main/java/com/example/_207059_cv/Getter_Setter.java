package com.example._207059_cv;

public class Getter_Setter {

    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private String skills;
    private String education;
    private String experience;

    public Getter_Setter() { }

    public Getter_Setter(String fullName, String email, String phone, String address,
                         String summary, String skills, String education, String experience) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getSkills() { return skills; }
    public String getEducation() { return education; }
    public String getExperience() { return experience; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setEducation(String education) { this.education = education; }
    public void setExperience(String experience) { this.experience = experience; }
}
