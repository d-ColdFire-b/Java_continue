package entity;


public class Person {

    int id;
    String gender, name, surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Person(int id) {
        this.id = id;
    }


    public void setGender(String gender) {
        if (gender.equals("Male") || gender.equals("Female")) {
            this.gender = gender;

        } else {
            this.gender = "Retard";
        }
    }


    public static void printDetails(Person person) {

        System.out.println("Id = " + person.id);
        System.out.println("Name = " + person.name);
        System.out.println("Surname = " + person.surname);
        System.out.println("Gender = " + person.gender);


    }


}
