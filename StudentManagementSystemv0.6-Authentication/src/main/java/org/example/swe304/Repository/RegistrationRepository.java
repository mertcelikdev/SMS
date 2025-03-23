package org.example.swe304.Repository;


import org.example.swe304.Model.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> , PagingAndSortingRepository<Registration, Long> {
    Page<Registration> findAll(Pageable pageable); // Sayfalama ve sıralama desteği

    List<Registration> findByStudentId(Long studentId);
}

