package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abdou on 20/04/19.
 * @project studentscollab
 */

@Repository
public interface FormationRepository extends PagingAndSortingRepository<Formation, Long> {

    @Query("select f from Formation f")
    public List<Formation> findAll();
}
