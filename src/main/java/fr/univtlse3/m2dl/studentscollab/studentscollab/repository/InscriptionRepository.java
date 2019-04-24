package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abd-dell on 21/04/19.
 * @project studentscollab
 */

@Repository
public interface InscriptionRepository extends PagingAndSortingRepository<Inscription, Long> {

    @Query("select i from Inscription i")
    List<Inscription> findAll();

    @Query("select i from Inscription i where i.formation.id = :idFormation")
    public List<Inscription> findInscriptionByFormationId(@Param("idFormation") Long idFormation);
}
