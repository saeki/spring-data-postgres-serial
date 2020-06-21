# spring-data-postgres-serial

Example for Spring Data JPA with PostgreSQL SERIAL data type which is NOT a primary key.

```
CREATE TABLE person
(
    uid SERIAL PRIMARY KEY,
    gid SERIAL NOT NULL,
    name VARCHAR(16)
);
```

If you want to generate from sequence all the time, just add `@Generated(GenerationTime.INSERT)` to the property. 

In my case, I needed to use both the existing and generated values, so I had to do the following.

```
@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    @Query(value = "select nextval('person_gid_seq')", nativeQuery = true)
    fun getNextGid(): Long
}

@Service
class PersonService(
        private val repository: PersonRepository
) {

    @Transactional(readOnly = false)
    fun save(person: Person): Person {
        if (person.gid == null) {
            person.gid = repository.getNextGid()
        }
        return repository.save(person)
    }
}
```

### Launch PostgreSQL

```
$ docker-compose up -d
```

### Launch Spring Boot application

```
$ ./gradlew bootRun
```

Access to API:

* [http://localhost:8080/person/](http://localhost:8080/person/)

### References

* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [PostgreSQL JSON Types](https://www.postgresql.org/docs/current/datatype-json.html)

