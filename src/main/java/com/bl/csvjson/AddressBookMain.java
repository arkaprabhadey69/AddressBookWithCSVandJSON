package com.bl.csvjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddressBookMain {
    public ArrayList<Contact> persons;
    public HashMap<String, ArrayList<Contact>> StatePersonMap;
    public HashMap<String, ArrayList<Contact>> CityPersonMap;


    Scanner s = new Scanner(System.in);
    Scanner s1 = new Scanner(System.in);
    Scanner s2 = new Scanner(System.in);

    public AddressBookMain() {
        persons = new ArrayList<Contact>();
        StatePersonMap = new HashMap<String, ArrayList<Contact>>();
        CityPersonMap = new HashMap<String, ArrayList<Contact>>();

    }

    //Method to read from JSON file using GSON
    public static int readFromJSON() {
        int size = 0;
        try {

            Reader reader = Files.newBufferedReader(Paths.get("contacts.json"));
            List<Contact> users = new Gson().fromJson(reader, new TypeToken<List<Contact>>() {
            }.getType());
            for (Contact user : users) {
                System.out.println(user);
                size++;
            }
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return size;

    }

    //Method to write contacts into a JSON file
    public static int writeJSON(ArrayList<Contact> contacts) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List list = contacts.stream().collect(Collectors.toList());
        String json = gson.toJson(list);
        FileWriter writer = new FileWriter("jsoncontacts.json");
        writer.write(json);
        writer.close();
        return list.size();

    }

    //Method to write to a CSV File
    public static int writeCsv(ArrayList<Contact> c) throws IOException {
        int count = 0;
        try (
                Writer writer = Files.newBufferedWriter(Paths.get("csvcontacts.csv"));
        ) {
            StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            ArrayList<Contact> myUsers = new ArrayList<>();
            for (Contact user : c) {
                myUsers.add(user);
                count++;
            }
            beanToCsv.write(myUsers);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        return count;

    }
    //Method of reading from a CSV file
    public static int readCsv() throws IOException {
        int count = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get("readonlycontacts.csv"));
        ) {
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<Contact> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()){
                count++;
                Contact csvUser = csvUserIterator.next();
                System.out.println("First Name : " + csvUser.getFirstname());
                System.out.println("Last Name : " + csvUser.getLastname());
                System.out.println("Address: " + csvUser.getAddress());
                System.out.println("City : " + csvUser.getCity());
                System.out.println("State : " + csvUser.getState());
                System.out.println("Zip : " + csvUser.getZip());
                System.out.println("Number : " + csvUser.getNumber());
                System.out.println("Email : " + csvUser.getEmail());
                System.out.println("==========================");
            }
        }
        return count;
    }

    //Method to add data to a file
    public void writeData(ArrayList<Contact> persons) {
        StringBuffer empBuffer = new StringBuffer();
        persons.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get("address-file.txt"), empBuffer.toString().getBytes());

        } catch (IOException e) {

        }
    }

    //Method to count entries in a file
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File("address-file.txt").toPath())
                    .count();
        } catch (IOException e) {

        }
        return entries;
    }

    //Method to read data from a file
    public long readData(ArrayList<Contact> employeePayrollList) {

        try {
            Files.lines(new File("address-file.txt").toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));

        } catch (IOException e) {

        }
        return employeePayrollList.size();

    }
    //Method to Write to CSV File


    public Contact Findname(String name) {
        Contact d = new Contact();

        for (int i = 0; i < persons.size(); i++) {
            Contact c = (Contact) persons.get(i);

            if (name.equals(c.firstname)) {
                d = c;
            }

        }
        System.out.println("\n" + d.firstname + " " + d.lastname + " " + d.address + " " + d.city + " " + d.email + " " + d.zip + "\n");

        return d;

    }

    public void FindName(String city) {

        List<Contact> list = persons.stream()
                .filter(p -> p.getCity().equals(city))
                .collect(Collectors.toList());
        for (Contact c : list) {
            System.out.println(c.firstname);
        }
    }

    public void FindContactName(String state) {

        List<Contact> list = persons.stream().filter(p -> p.getState().equals(state)).collect(Collectors.toList());
        for (Contact c : list) {
            System.out.println(c.firstname);
        }
    }

    public void FindCountContactName(String state) {

        int list = Math.toIntExact(persons.stream().filter(p -> p.getState().equals(state)).count());
        System.out.println("The number of people living in this state in this address book is: " + list);
    }

    public void FindCountContactNameByCity(String city) {

        int list = Math.toIntExact(persons.stream().filter(p -> p.getCity().equals(city)).count());
        System.out.println("The number of people living in this city in this address book is: " + list);
    }

    public void SortNames() {

        List<Contact> list = persons.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());

        for (Contact c : list) {
            System.out.println(c.firstname);
        }

    }

    public void SortNamesbyZip() {

        List<Contact> list = persons.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());

        for (Contact c : list) {
            System.out.println(c.firstname);
        }

    }

    public void SortNamesbyState() {

        List<Contact> list = persons.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());

        for (Contact c : list) {
            System.out.println(c.firstname);
        }

    }

    public void FindDuplicates() {
        Set<String> items = new HashSet<>();

        Set<Contact> items1 = persons.stream()
                .filter(n -> !items.add(n.firstname))
                .collect(Collectors.toSet());
        for (Contact c : items1) {
            System.out.println("Duplicate entry is: " + c.firstname);
        }
    }

    public ArrayList<Contact> getList() {
        return persons;
    }


    public void FindPersonByHash(String city) {
        ArrayList<Contact> person1 = new ArrayList<Contact>();
        person1 = CityPersonMap.get(city);
        for (int i = 0; i < person1.size(); i++) {
            System.out.println("Firstname " + person1.get(i).firstname + " Lastname " + person1.get(i).lastname +
                    " Address " + person1.get(i).address + " City " + person1.get(i).city + " State " + person1.get(i).state +
                    " Zip " + person1.get(i).zip + " Phone number " + person1.get(i).number + " Email " + person1.get(i).email + "\n");
        }


    }

    public void FindPersonByHashState(String state) {
        ArrayList<Contact> person1 = new ArrayList<Contact>();
        person1 = StatePersonMap.get(state);
        for (int i = 0; i < person1.size(); i++) {
            System.out.println("Firstname " + person1.get(i).firstname + " Lastname " + person1.get(i).lastname +
                    " Address " + person1.get(i).address + " City " + person1.get(i).city + " State " + person1.get(i).state +
                    " Zip " + person1.get(i).zip + " Phone number " + person1.get(i).number + " Email " + person1.get(i).email + "\n");
        }

    }

    public void AddName() {
        String x, y, z, w, a, b, e;
        String c1;
        System.out.println("Enter your details\n");
        System.out.println("Firstname\n");
        x = s2.nextLine();
        System.out.println("Lastname\n");
        y = s2.nextLine();
        System.out.println("Address\n");
        z = s2.nextLine();
        System.out.println("City\n");
        w = s2.nextLine();
        System.out.println("State\n");
        a = s2.nextLine();
        System.out.println("Zip\n");
        b = s2.nextLine();
        System.out.println("Phone No.\n");
        c1 = s2.nextLine();
        System.out.println("Email\n");
        e = s2.nextLine();
        Contact c = new Contact(x, y, z, w, a, b, c1, e);
        persons.add(c);
        if (!StatePersonMap.containsKey(a))
            StatePersonMap.put(a, new ArrayList<Contact>());
        StatePersonMap.get(a).add(c);

        if (!CityPersonMap.containsKey(w))
            CityPersonMap.put(w, new ArrayList<Contact>());
        CityPersonMap.get(w).add(c);
        System.out.println("Added succesfully, the contacts are: \n");
        for (int i = 0; i < persons.size(); i++) {
            persons.get(i).Display();
        }

    }

    public void edit(String name) {

        Contact c = Findname(name);
        System.out.println("Enter which detail to edit\n\n\n1.FirstName\n2.Secondname\n3.Address\n4.City\n5.State\n6.Zip\n7.Phone\n8.Email\n");
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter new name: \n");
                c.firstname = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 2:
                System.out.println("Enter last name: \n");
                c.lastname = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 3:
                System.out.println("Enter new address:");
                c.address = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 4:
                System.out.println("Enter new city:");
                c.city = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 5:
                System.out.println("Enter new state:");
                c.state = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 6:
                System.out.println("Enter new zip:");
                c.zip = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 7:
                System.out.println("Enter new number:");
                c.number = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            case 8:
                System.out.println("Enter new email:");
                c.email = s1.nextLine();
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).Display();
                }
                break;
            default:
                break;


        }


    }

    public void delete(String name) {
        for (int i = 0; i < persons.size(); i++) {

            Contact c = (Contact) persons.get(i);
            if (name.equals(c.firstname)) {
                persons.remove(i);
            }
        }
        for (int i = 0; i < persons.size(); i++) {
            System.out.println("After Deletion");
            persons.get(i).Display();
        }


    }


}

