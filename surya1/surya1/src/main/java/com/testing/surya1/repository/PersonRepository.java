package com.testing.surya1.repository;

import com.testing.surya1.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByIsActive(Boolean isActive);

    @Query("FROM Person WHERE lower(name) LIKE lower(concat('%', ?1, '%')) AND isActive=true")
    List<Person> searchByKeywordName(String keyword);
}
