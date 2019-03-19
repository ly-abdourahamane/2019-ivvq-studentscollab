package fr.univtlse3.m2dl.studentscollab.studentscollab.repositories;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class EtudiantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Etudiant save(Etudiant etudiant) {
        entityManager.persist(etudiant);

        return etudiant;
    }

    public Etudiant update(Etudiant etudiant) {
        return entityManager.merge(etudiant);
    }

    public Etudiant findById(Long id) {
        return  entityManager.find(Etudiant.class, id);
    }

    public List<Etudiant> findAll() {
        TypedQuery<Etudiant> query = entityManager.createQuery("select e from Etudiant e", Etudiant.class);
        return query.getResultList();
    }
}
