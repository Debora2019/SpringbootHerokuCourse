package pt.com.curso.curso.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.com.curso.curso.model.Person;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long>{

    @Query(value = "Select u from Person u where upper(trim(u.name)) like %?1%")
    List<Person> findByName (String name);
    
}
