package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteCoursRepository extends CrudRepository<NoteCours, Long> {

}
