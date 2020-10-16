package com.bl.csvjson;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddressBookMainTest {


    @Test
    public void given_Contacts_Should_Write_To_A_File(){
        Contact[] contacts={new Contact("Arka","Dey","lmao","kol","wb","700026","8961377960","abc@gmail.com" ),
                new Contact("Mayuri","Dey","lmao","kol","wb","700089","8961388960","abcd@gmail.com" ),
                new Contact("Nayanika","Guha","lmao","kol","wb","700096","8961377900","abcdef@gmail.com" )};
        ArrayList<Contact> arr=new ArrayList<>();
        arr.add(contacts[0]);
        arr.add(contacts[1]);
        arr.add(contacts[2]);
        AddressBookMain adressBook= new AddressBookMain();
        adressBook.writeData(arr);
        long entries=adressBook.countEntries();
        Assert.assertEquals(3,entries);


    }
    @Test
    public void given_Contacts_Should_Write_To_A_File_And_Give_Count(){
        Contact[] contacts={new Contact("Arka","Dey","lmao","kol","wb","700026","8961377960","abc@gmail.com" ),
                new Contact("Mayuri","Dey","lmao","kol","wb","700089","8961388960","abcd@gmail.com" ),
                new Contact("Nayanika","Guha","lmao","kol","wb","700096","8961377900","abcdef@gmail.com" )};
        ArrayList<Contact> arr=new ArrayList<>();
        arr.add(contacts[0]);
        arr.add(contacts[1]);
        arr.add(contacts[2]);
        AddressBookMain adressBook= new AddressBookMain();
        adressBook.writeData(arr);
        long entries=adressBook.countEntries();
        Assert.assertEquals(3,entries);


    }
    @Test
    public void given_Contacts_Should_Read_A_File_And_Give_Count(){
        Contact[] contacts={new Contact("Arka","Dey","lmao","kol","wb","700026","8961377960","abc@gmail.com" ),
                new Contact("Mayuri","Dey","lmao","kol","wb","700089","8961388960","abcd@gmail.com" ),
                new Contact("Nayanika","Guha","lmao","kol","wb","700096","8961377900","abcdef@gmail.com" )};
        ArrayList<Contact> arr=new ArrayList<>();
        arr.add(contacts[0]);
        arr.add(contacts[1]);
        arr.add(contacts[2]);
        AddressBookMain adressBook= new AddressBookMain();
        adressBook.writeData(arr);
        long entries=adressBook.readData(arr);
        Assert.assertEquals(3,entries);


    }
    @Test
    public void read_Contacts_From_JSON() throws IOException {
        int count=AddressBookMain.readFromJSON();
        Assert.assertEquals(2,count);

    }
    @Test
    public void write_Contacts_Into_JSon() throws IOException {
        Contact user1= new Contact("Sundar  ", "Pichai", "kol", "kolkata","wb","700026","9087655","abc@gmail,com");
        Contact user2=new Contact("Mayuri","Dey","lmao","kol","wb","700089","8961388960","abcd@gmail.com");
        Contact user3=new Contact("Nayanika","Guha","lmao","kol","wb","700096","8961377900","abcdef@gmail.com");
        ArrayList<Contact> users= new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        int count=AddressBookMain.writeJSON(users);
        Assert.assertEquals(3,count);

    }
    @Test
    public void write_Into_CSV_File() throws IOException {
        Contact user1= new Contact("Sundar  ", "Pichai", "kol", "kolkata","wb","700026","9087655","abc@gmailcom");
        Contact user2=new Contact("Mayuri","Dey","lmao","kol","wb","700089","8961388960","abcd@gmail.com");
        Contact user3=new Contact("Nayanika","Guha","lmao","kol","wb","700096","8961377900","abcdef@gmail.com");
        ArrayList<Contact> users= new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        int count=AddressBookMain.writeCsv(users);
        Assert.assertEquals(3,count);

    }
    @Test
    public void read_when_done() throws IOException {
        int count=AddressBookMain.readCsv();
        Assert.assertEquals(2,count);

    }

}