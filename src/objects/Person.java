package objects;

public class Person {
    private String id;
    private String name;

    public Person(){
        this.id = null;
        this.name = null;
    }

    public Person(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Person(Person person){
        this.id = person.getId();
        this.name = person.getName();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (getId() != null ? !getId().equals(person.getId()) : person.getId() != null) return false;
        return getName() != null ? getName().equals(person.getName()) : person.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" + '\n' +
                "    id='" + id + '\'' + '\n' +
                "    , name='" + name + '\'' + '\n' +
                "}" + '\n';
    }
}
