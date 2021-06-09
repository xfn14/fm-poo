package objects.player;

import java.util.Objects;

public class Person {
    /**
     * Id representing a person
     */
    private final int id;

    /**
     * Person's name
     */
    private String name;

    /**
     * Instantiates a new Person
     */
    public Person(){
        this.id = -1;
        this.name = null;
    }

    /**
     * Instantiates a new Person with the given arguments
     * @param id Identification of the Person
     * @param name Name of the Person
     */
    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new Person with the given Person
     * @param person Person's object
     */
    public Person(Person person){
        this.id = person.getId();
        this.name = person.getName();
    }

    /**
     * Get Person's Id
     * @return Person's Id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get Person's name
     * @return Person's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get Person's Id
     * @param name Person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Person's first and last name
     * @return String containing person's first and last name
     */
    public String getFstLstName(){
        String[] names = this.getName().split(" ");
        if(names.length != 1) return names[0] + " " + names[names.length-1];
        return names[0];
    }

    /**
     * Clones Person's instance
     * @return Person's new object
     */
    @Override
    public Person clone() {
        return new Person(this);
    }

    /**
     * Equality between Person's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return Objects.equals(name, person.name);
    }

    /**
     * String representation of Person's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
