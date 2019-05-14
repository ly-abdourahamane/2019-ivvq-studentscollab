package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteCoursRepository extends CrudRepository<NoteCours, Long> {

    public List<NoteCours> getByRedacteurId(Long etudiantId);
}
