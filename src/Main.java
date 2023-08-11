import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        Stream<Person> children = persons.stream().filter(a -> a.getAge() < 18);
        long count = children.count();
        System.out.println(count); // кол-во людей младше 18 лет

        List<String> military = persons.stream().filter(a -> a.getSex() == Sex.MAN)
                .filter(a -> a.getAge() > 18 && a.getAge() < 27)
                .map(Person::getFamily)
                .toList();
        System.out.println(military); // список призывников

        List<Person> labor = persons.stream().filter(a -> a.getEducation() == Education.HIGHER)
                .filter(a -> a.getSex() == Sex.MAN && a.getAge() > 18 && a.getAge() < 65 || a.getSex() == Sex.WOMAN && a.getAge() > 18 && a.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(labor); //отсортированный по фамилии список потенциально работоспособных людей


    }
}
