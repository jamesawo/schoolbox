
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Country;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository

//not in use - DEPRECATED

public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Student> searchStudentMultipleCriteriaApi(StudentSearchParamDTO searchParamDTO) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> student = criteriaQuery.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();
        Join<Student, InstitutionStructure> institutionStructureJoin = student.join("institutionStructure");
        Join<Student, InstitutionProgrammeType> programmeTypeJoin = student.join("programmeType");
        Join<Student, Country> countryJoin = student.join("country");
        Join<Student, InstitutionSession> sessionJoin = student.join("session");
        Join<Student, InstitutionLevel> levelJoin = student.join("level");
        Join<Student, StudentEntryType> entryTypeJoin = student.join("entryType");


        if (searchParamDTO.getProgrammeType() != 0 ){
            predicates.add(criteriaBuilder.equal( programmeTypeJoin.get("id") , searchParamDTO.getProgrammeType() ));
        }

        if (searchParamDTO.getInstitutionStructure() != null ){
            predicates.add( criteriaBuilder.like( criteriaBuilder.lower( institutionStructureJoin.get("description") ) ,  searchParamDTO.getInstitutionStructure().toLowerCase() ));
        }

        if (searchParamDTO.getCountry() != 0){
            predicates.add(criteriaBuilder.equal(countryJoin.get("id"), searchParamDTO.getCountry()));
        }
    
        assert searchParamDTO.getSession() != null;
        if (searchParamDTO.getSession() != null){
            predicates.add(criteriaBuilder.equal(sessionJoin.get("id"), searchParamDTO.getSession().getId()));
        }

//        if (searchParamDTO.isStatus()){
//            predicates.add(criteriaBuilder.equal(student.get("status"), searchParamDTO.isStatus()));
//        }

        if (searchParamDTO.getLevel() != 0){
            predicates.add(criteriaBuilder.equal(levelJoin.get("id"), searchParamDTO.getLevel()));
        }

        if (searchParamDTO.getEntryType() != 0){
            predicates.add(criteriaBuilder.equal(entryTypeJoin.get("id"), searchParamDTO.getEntryType()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Student> searchStudentSingleCriteriaApi(StudentSearchParamDTO searchParamDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        if ( searchParamDTO.getId() != 0 ){
            Predicate predicate = criteriaBuilder.equal(studentRoot.get("id"), searchParamDTO.getId());
            criteriaQuery.where(predicate);
            return entityManager.createQuery(criteriaQuery).getResultList();
        }

        if (searchParamDTO.getFullName() != null || !searchParamDTO.getFullName().isEmpty()){

//            Expression<Boolean> function = criteriaBuilder.function("CONTAINS", Boolean.class,
//                    studentRoot.<String>get("surname"), criteriaBuilder.parameter(String.class, searchParamDTO.getFullName()),
//                    studentRoot.<String>get("firstName"), criteriaBuilder.parameter(String.class, searchParamDTO.getFullName()),
//                    studentRoot.<String>get("lastName"), criteriaBuilder.parameter(String.class, searchParamDTO.getFullName())
//
//            );

            Predicate surnamePredicate = criteriaBuilder.like( criteriaBuilder.lower( studentRoot.get("surname") ), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );

            Predicate firstNamePredicate = criteriaBuilder.like( criteriaBuilder.lower( studentRoot.get("firstName")), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );

            Predicate lastNamePredicate = criteriaBuilder.like( criteriaBuilder.lower( studentRoot.get("lastName")), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );

            predicates.add(criteriaBuilder.or( surnamePredicate, firstNamePredicate, lastNamePredicate ));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
