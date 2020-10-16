package com.bl.csvjson;

import com.opencsv.bean.CsvBindByName;
    public class Contact {
        @CsvBindByName
        public String firstname;
        @CsvBindByName
        public String lastname;
        @CsvBindByName
        public String address;
        @CsvBindByName
        public String city;
        @CsvBindByName
        public String state;
        @CsvBindByName
        public String zip;
        @CsvBindByName
        public String number;
        @CsvBindByName
        public String email;
        public Contact(String firstname, String lastname, String address, String city, String state, String zip, String number,
                       String email) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.number = number;
            this.email = email;
        }
        public Contact()
        {

        }
        public void Display()
        {
            System.out.println(firstname+" "+lastname+" "+address+" "+city+" "+state+" "+zip+" "+number+" "+email);
        }
        public String getCity()
        {
            return city;
        }
        public String getState()
        {
            return state;
        }
        public String getName()
        {
            return firstname;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getAddress() {
            return address;
        }

        public String getNumber() {
            return number;
        }

        public String getEmail() {
            return email;
        }

        public String getZip(){return zip;}

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", zip='" + zip + '\'' +
                    ", number='" + number + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }




    }


