package com.example.afinal.data.fetchedData;

public class User {
    public int id;
    public String firstName;
    public String lastName;
    public String maidenName;
    public int age;
    public String gender;
    public String email;
    public String phone;
    public String username;
    public String password;
    public String birthDate;
    public String image;
    public String bloodGroup;
    public int height;
    public double weight;
    public String eyeColor;
    public Hair hair;
    public String domain;
    public String ip;
    public Address address;
    public String macAddress;
    public String university;
    public Bank bank;
    public Company company;
    public String ein;
    public String ssn;
    public String userAgent;

    public User(int id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName="";
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getImage() {
        return image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public Hair getHair() {
        return hair;
    }

    public String getDomain() {
        return domain;
    }

    public String getIp() {
        return ip;
    }

    public Address getAddress() {
        return address;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getUniversity() {
        return university;
    }

    public Bank getBank() {
        return bank;
    }

    public Company getCompany() {
        return company;
    }

    public String getEin() {
        return ein;
    }

    public String getSsn() {
        return ssn;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
