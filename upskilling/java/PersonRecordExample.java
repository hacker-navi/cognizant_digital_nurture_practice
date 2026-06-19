import java.util.Arrays;
import java.util.List;

public class PersonRecordExample {
    record Person(String name, int age) {
    }

    public static void main(String[] args) {
        Person firstPerson = new Person("Ava", 22);
        Person secondPerson = new Person("Noah", 17);
        Person thirdPerson = new Person("Mia", 30);

        List<Person> people = Arrays.asList(firstPerson, secondPerson, thirdPerson);

        System.out.println(firstPerson);
        System.out.println(secondPerson);
        System.out.println(thirdPerson);

        people.stream()
                .filter(person -> person.age() >= 18)
                .forEach(System.out::println);
    }
}
