package me.workloads.person.logic;

import me.models.PersonDTO;
import me.workloads.person.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;

@ApplicationScoped
public class PersonRepoImpl implements PersonRepo {

    private final EntityManager entityManager;

    public PersonRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void update(Person u) {
        entityManager.merge(u);
    }

    @Override
    public void add(Person u) {
        entityManager.persist(u);
        System.out.println("User has been added");
    }

    @Override
    public Person validateUser(PersonDTO personDTO) {
        TypedQuery<Person> typedQuery = this.entityManager
                .createQuery("select p from Person p where p.email = :email", Person.class)
                .setParameter("email", personDTO.getEmail());
        Person person = typedQuery.getResultStream().findFirst().orElse(null);

        if (person == null){
            return null;
        }

        byte[] password = Person.hash(personDTO.getPassword(), person.getSalt());


        if (Arrays.equals(person.getPassword(), password)){
            person.generateSessionCode();
            this.entityManager.merge(person);
            return person;
        }

        return null;
    }
}
