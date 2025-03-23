package org.example.swe304.Repository;

import org.example.swe304.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , PagingAndSortingRepository<Student, Long> {
    // 📌 Öğrenci adına göre arama
    Student findByFirstName(String firstName);

}
