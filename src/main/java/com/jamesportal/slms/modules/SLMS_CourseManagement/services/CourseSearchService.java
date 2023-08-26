
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.services;

import com.jamesportal.slms.modules.SLMS_CourseManagement.dto.CourseSearchDto;
import com.jamesportal.slms.modules.SLMS_CourseManagement.entity.Course;
import com.jamesportal.slms.modules.SLMS_CourseManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSearchService {

    @Autowired
    CourseRepository courseRepository;



    @Transactional
    public Page<Course> searchCoursePageable(final CourseSearchDto courseSearchDto){
        Pageable pageable = PageRequest.of(courseSearchDto.getPageNumber(), courseSearchDto.getPageSize());
        return courseRepository.findAll(CourseSearchService.CourseSearchSpecification.findByCriteria(courseSearchDto), pageable);
    }

    public List<Course> searchCourseReportData(final CourseSearchDto courseSearchDto){
        return courseRepository.findAll(CourseSearchService.CourseSearchSpecification.findByCriteria(courseSearchDto));
    }


    @Transactional
    private static class CourseSearchSpecification{
        private static Specification<Course> findByCriteria(CourseSearchDto courseSearchDto){
            return new Specification<Course>() {
                private static final long serialVersionUID = 1L;

                @Override
                public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();



                    return null;
                }
            };
        }
    }

}
