
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Country;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import com.jamesportal.slms.modules.SLMS_StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSearchService {

    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public Page<Student> searchStudents(final StudentSearchParamDTO searchParamDTO){
        Pageable pageable =  PageRequest.of(searchParamDTO.getPageNumber(), searchParamDTO.getPageSize());
        return studentRepository.findAll(StudentSearchSpecification.findByCriteria(searchParamDTO), pageable );
    }
    
    @Transactional
    public List<Student> searchStudentReportData(final StudentSearchParamDTO searchParamDTO){
        return studentRepository.findAll(StudentSearchSpecification.findByCriteria(searchParamDTO));
    }

    private  static  class StudentSearchSpecification {

        private static Specification<Student> findByCriteria(final StudentSearchParamDTO searchParamDTO){
            return new Specification<Student>() {
                private static final long serialVersionUID = 1L;

                @Override
                public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                    List<Predicate> predicates = new ArrayList<>();

                    //Single Search
                    if(searchParamDTO.getId() != 0){
                        predicates.add(criteriaBuilder.equal(root.get("id"), searchParamDTO.getId()));
                        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
                    }

                    if ( searchParamDTO.getRegNumber() != null && !searchParamDTO.getRegNumber().equals("")){
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("regNumber")), "%"+searchParamDTO.getRegNumber().toLowerCase() +"%" ));
                    }

                    if  (searchParamDTO.getFullName() != null && !searchParamDTO.getFullName().isEmpty()){

                        Predicate surnamePredicate = criteriaBuilder.like( criteriaBuilder.lower( root.get("surname") ), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );
                        Predicate firstNamePredicate = criteriaBuilder.like( criteriaBuilder.lower( root.get("firstName")), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );
                        Predicate lastNamePredicate = criteriaBuilder.like( criteriaBuilder.lower( root.get("lastName")), "%" + searchParamDTO.getFullName().toLowerCase() + "%" );
                        predicates.add(criteriaBuilder.or(surnamePredicate,firstNamePredicate,lastNamePredicate) );
                    }
                    // Multiple Search
                    if (searchParamDTO.getInstitutionStructureIds() != null && !searchParamDTO.getInstitutionStructureIds().isEmpty()) {
                        Join<Student, InstitutionStructure> institutionStructureJoin = root.join("institutionStructure");
                        predicates.add(institutionStructureJoin.in(searchParamDTO.getInstitutionStructureIds()));
                    }
                    if (searchParamDTO.getProgrammeType() != 0) {
                        Join<Student, InstitutionProgrammeType> institutionProgrammeTypeJoin = root.join("programmeType");
                        predicates.add(criteriaBuilder.equal(institutionProgrammeTypeJoin.get("id"), searchParamDTO.getProgrammeType()));
                    }

                    if (searchParamDTO.getCountry() != 0){
                        Join<Student, Country> countryJoin = root.join("country");
                        predicates.add(criteriaBuilder.equal(countryJoin.get("id"), searchParamDTO.getCountry()));
                    }

                    if (searchParamDTO.getSession() != null){
                        Join<Student, InstitutionSession> sessionJoin = root.join("session");
                        predicates.add(criteriaBuilder.equal(sessionJoin.get("id"), searchParamDTO.getSession().getId()));
                    }

                    if (searchParamDTO.getStatus() != null){
                        if (searchParamDTO.getStatus().equals("true")) {
                            predicates.add(criteriaBuilder.isTrue(root.get("status").as(Boolean.class)));
                        }
                        if (searchParamDTO.getStatus().equals("false")){
                            predicates.add(criteriaBuilder.isFalse(root.get("status").as(Boolean.class)));
                        }
                    }
                    if (searchParamDTO.getEntryType() != 0){
                        Join<Student, StudentEntryType> entryTypeJoin = root.join("entryType");
                        predicates.add(criteriaBuilder.equal(entryTypeJoin.get("id"), searchParamDTO.getEntryType()));
                    }

                    if (searchParamDTO.getLevel() != 0){
                        Join<Student, InstitutionLevel> levelJoin = root.join("level");
                        predicates.add(criteriaBuilder.equal(levelJoin.get("id"), searchParamDTO.getLevel()));
                    }

                    query.orderBy(criteriaBuilder.asc(root.join("institutionStructure").get("description")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
                }
            };

        }

    }

}
