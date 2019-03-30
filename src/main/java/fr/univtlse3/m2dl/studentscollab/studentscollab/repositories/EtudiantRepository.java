package fr.univtlse3.m2dl.studentscollab.studentscollab.repositories;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface EtudiantRepository extends PagingAndSortingRepository<Etudiant, Long> {

    @Query("select e from Etudiant e")
    public List<Etudiant> findAll();

    @Query("select e from Etudiant e where e.email = :email and e.motDePasse = :motDePasse")
    public Etudiant login(@Param("email") String email, @Param("motDePasse") String motDePasse);
}
