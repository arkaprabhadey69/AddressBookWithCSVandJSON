package com.bl.csvjson;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookList {
    public static HashMap<String,AddressBookMain> addrbooklist;

    public AddressBookList()
    {
        addrbooklist= new HashMap<>();
    }
    public void addAddressbook(String name)
    {
        AddressBookMain a= new AddressBookMain();
        boolean f=true;
        while(f)
        {

            System.out.println("\n1.Add\n2.Edit\n3.Delete\n4.Exit\nEnter your choice : ");
            Scanner in = new Scanner(System.in);
            int ch = in.nextInt();

            switch(ch)
            {
                case 1:
                    a.AddName();
                    break;

                case 2:
                    System.out.println("Enter name to edit");
                    Scanner s1=new Scanner(System.in);
                    String a1= s1.nextLine();
                    a.edit(a1);
                    break;

                case 3:
                    System.out.println("Enter name to delete");
                    Scanner s2=new Scanner(System.in);
                    String a2=s2.nextLine();
                    a.delete(a2);
                    break;

                case 4:
                    System.out.println("Terminated!");
                    f=false;
                    break;

            }
        }

        addrbooklist.put(name, a);
        for (Map.Entry<String,AddressBookMain> entry : addrbooklist.entrySet())
        {
            System.out.println("AddressBookName = " + entry.getKey() + ", Details = " + entry.getValue());
        }


        System.out.println("Added successfully");


    }
    public void SearchContact(String city)
    {
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            System.out.println("Address Book : "+entry.getKey());
            AddressBookMain adr= entry.getValue();

            adr.FindName(city);


        }

    }
    public void SearchPerson(String state)
    {
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            System.out.println("Address Book : "+entry.getKey());
            AddressBookMain adr= entry.getValue();

            adr.FindContactName(state);

        }
    }
    public void CountByState(String state)
    {
        int count=0;
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {

            AddressBookMain a1= entry.getValue();
            a1.FindCountContactName(state);
        }

    }
    public void CountByCity(String city)
    {
        int count1=0;
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            AddressBookMain a1= entry.getValue();
            a1.FindCountContactName(city);

        }
    }

    public void SearchContactByHash(String city){
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            AddressBookMain addBook = entry.getValue();
            ArrayList<Contact> contacts = addBook.CityPersonMap.entrySet().stream().filter(findState -> findState.getKey().equals(city)).map(Map.Entry::getValue).findFirst().orElse(null);
            for(Contact contact:contacts)
            {
                System.out.println(contact.firstname+" "+contact.lastname+" "+contact.address+" "+contact.city+" "+contact.email+" "+contact.number+" "+contact.state+" "+contact.zip+"\n");
            }
        }

    }
    public void GetPersonDetailsByHash(String state)
    {
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            AddressBookMain addBook = entry.getValue();
            ArrayList<Contact> contacts = addBook.StatePersonMap.entrySet().stream().filter(findState -> findState.getKey().equals(state)).map(Map.Entry::getValue).findFirst().orElse(null);
            for(Contact contact:contacts)
            {
                System.out.println(contact.firstname+" "+contact.lastname+" "+contact.address+" "+contact.city+" "+contact.email+" "+contact.number+" "+contact.state+" "+contact.zip+"\n");
            }
        }

    }
    public void SortByName()
    {
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            AddressBookMain addBook=entry.getValue();
            System.out.println("Address Book : "+entry.getKey());
            addBook.SortNames();
        }

    }
    public void Findduplicates()
    {
        for(Map.Entry<String, AddressBookMain> entry: addrbooklist.entrySet())
        {
            AddressBookMain addBook=entry.getValue();
            System.out.println("Address Book : "+entry.getKey());
            addBook.FindDuplicates();
        }

    }
    public static void main(String[] args)
    {
        System.out.println("Welcome to AddressBookManagementSystem");
        AddressBookList adr= new AddressBookList();
        Scanner s6= new Scanner(System.in);
        boolean m=true;
        while(m)
        {
            System.out.println("1.Enter new address book\n2.Exit\n3.SearchByCity\n4.SearchByState\n5.CountByState\n6.CountByCity\n7.GetDeatilsbyHashCity\n8.GetDeatilsbyHashState\n9.Sort\n10.FindDuplicates");
            Scanner s7=new Scanner(System.in);
            int choice=s7.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Enter address book name: ");
                    String addressbookname= s6.nextLine();
                    if(addrbooklist.containsKey(addressbookname))
                    {
                        System.out.println("Error! Name already taken!");
                        break;
                    }
                    adr.addAddressbook(addressbookname);
                    break;
                case 2:
                    s7.close();
                    System.out.println("Exited!");
                    m=false;
                    break;
                case 3:
                    System.out.println("Enter city name: ");
                    String city= s6.nextLine();
                    adr.SearchContact(city);
                    break;
                case 4:
                    System.out.println("Enter state name: ");
                    String state= s6.nextLine();
                    adr.SearchPerson(state);
                    break;
                case 5:
                    System.out.println("Enter state name: ");

                    String state1= s6.nextLine();
                    adr.CountByState(state1);
                    break;
                case 6:
                    System.out.println("Enter city name: ");
                    String state2= s6.nextLine();
                    adr.CountByCity(state2);
                    break;
                case 7:
                    System.out.println("Enter city name: ");
                    String state3= s6.nextLine();
                    adr.SearchContactByHash(state3);
                    break;
                case 8:
                    System.out.println("Enter state name: ");
                    String state4= s6.nextLine();
                    adr.GetPersonDetailsByHash(state4);
                    break;
                case 9:
                    //String state5= s6.nextLine();
                    adr.SortByName();
                    break;
                case 10:
                    adr.Findduplicates();



            }
        }




    }



}


