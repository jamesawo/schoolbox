
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.*;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionStructureServiceX {

    @Autowired
    CategoryNameServiceImpl categoryNameServiceImpl;

    @Autowired
    InstitutionStructureServiceImpl institutionStructureServiceImpl;

    @Autowired
    InstitutionLevelServiceImpl institutionLevelServiceImpl;

    @Autowired
    ProgrammeCategoryServiceImpl programmeCategoryServiceImpl;

    @Autowired
    ProgrammeTypeServiceImpl programmeTypeServiceImpl;

    @Autowired
    CourseDurationServiceImpl courseDurationService;

    @Autowired
    SemesterServiceImpl semesterServiceImpl;

    @Autowired
    InstitutionSessionServiceImpl institutionSessionService;

    @Autowired
    InstitutionCurrentSessionServiceImpl institutionCurrentSessionService;


    /* INSTITUTION CATEGORY */
    public List<InstitutionCategory> getAllCategoryNames() {
        return categoryNameServiceImpl.getAllCategoryNames();
    }

    ;

    public void createCategoryNameFromList(CategoryNameDTO categoryNameDTO) {
        categoryNameServiceImpl.createCategoryNameFromList(categoryNameDTO.getCategoryList());
    }

    public void createCategory(CategoryNameDTO categoryNameDTO) {
        InstitutionCategory newCategoryName = new InstitutionCategory();
        newCategoryName.setDescription(categoryNameDTO.getDescription());
        newCategoryName.setHierarchy(categoryNameDTO.getHierarchy());
        categoryNameServiceImpl.saveCategoryName(newCategoryName);
    }

    public void editCategoryName(CategoryNameDTO categoryNameDTO) {
        InstitutionCategory categoryNameToEdit = categoryNameServiceImpl.getCategoryNameById(categoryNameDTO.getId());
        categoryNameToEdit.setHierarchy(categoryNameDTO.getHierarchy());
        categoryNameToEdit.setDescription(categoryNameDTO.getDescription());
        categoryNameServiceImpl.updateCategoryName(categoryNameToEdit);
    }

    public InstitutionCategory getInstitutionCategoryByLeastHierarchy() { // smallest hierarchy
        return categoryNameServiceImpl.getCategoryNameByLeastHierarchy();
    }

    public InstitutionCategory getInstitutionCategoryByHighestHierarchy() { // highest hierarchy
        return categoryNameServiceImpl.getCategoryNameByHighestHierarchy();
    }

    public InstitutionCategory getNextInstitutionCategoryByCategoryId(long startFrom) {
        InstitutionCategory institutionCategory = getInstitutionCategoryById(startFrom);
        return getInstitutionCategoryByHierarchy(institutionCategory.getHierarchy() + 1);

    }

    private InstitutionCategory getInstitutionCategoryByHierarchy(int i) {
        return categoryNameServiceImpl.getCategoryNameByHierarchy(i);
    }

      private InstitutionCategory getInstitutionCategoryById(long id) {
        return categoryNameServiceImpl.getCategoryNameById(id);
    }

    /* INSTITUTION STRUCTURE */
    public void createNewInstitutionStructure(InstitutionStructureDTO institutionStructureDTO) {
        InstitutionStructure InsStructure = new InstitutionStructure();
        InsStructure.setDescription(institutionStructureDTO.getDescription());
        InsStructure.setCode(institutionStructureDTO.getCode());
        InsStructure.setParent(institutionStructureDTO.getParent_id());
        InsStructure.setCategory(categoryNameServiceImpl.getCategoryNameById(institutionStructureDTO.getGroup_id()));
        institutionStructureServiceImpl.saveInstitutionStructure(InsStructure);
    }

    public List<InstitutionStructure> getInstitutionStructureListByCategory(InstitutionCategory categoryName) {
        return institutionStructureServiceImpl.getInstitutionStructureByCategory(categoryName);
    }

    public List<InstitutionStructure> getAllInstitutionStructure() {
        return institutionStructureServiceImpl.getAllInstitutionStructure();
    }

    public InstitutionStructure getInstitutionStructureById(long id) {
        return institutionStructureServiceImpl.getInstitutionStructureById(id);
    }

    public List<InstitutionStructure> getInstitutionStructureByParent(long id) {
        return institutionStructureServiceImpl.getInstitutionStructureByParent(id);
    }

    public List<InstitutionStructure> getInstitutionStructureByLeastCategoryHierarchy() {
        return getInstitutionStructureListByCategory(getInstitutionCategoryByLeastHierarchy());
    }

    public List<InstitutionStructure> getInstitutionStructureListByCategoryIdAndParentId(long categoryId, long parentId) {
        return institutionStructureServiceImpl.getInstitutionStructureListByCategoryAndParent(new InstitutionCategory(categoryId), new InstitutionStructure(parentId));
    }

    public InstitutionStructure getInstitutionStructureByDescription(String description) {
        return institutionStructureServiceImpl.getInstitutionStructureByDescription(description);
    }

    /* INSTITUTION LEVEL */
    public InstitutionLevel createInstitutionLevel(LevelDTO levelDTO) {
        InstitutionLevel institutionLevel = new InstitutionLevel();
        institutionLevel.setAlternative(levelDTO.getAlternativeDescription());
        institutionLevel.setDescription(levelDTO.getDescription());
        institutionLevel.setHierarchy(levelDTO.getHierarchy());
        return institutionLevelServiceImpl.saveInstitutionLevel(institutionLevel);
    }

    public InstitutionLevel updateInstitutionLevel(LevelDTO levelDTO) {
        InstitutionLevel institutionLevel = institutionLevelServiceImpl.getInstitutionLevel(levelDTO.getId());
        institutionLevel.setAlternative(levelDTO.getAlternativeDescription());
        institutionLevel.setDescription(levelDTO.getDescription());
        institutionLevel.setHierarchy(levelDTO.getHierarchy());
        return institutionLevelServiceImpl.saveInstitutionLevel(institutionLevel);
    }

    public List<InstitutionLevel> getAllInstitutionLevel() {
        return institutionLevelServiceImpl.getAllInstitutionLevel();
    }


    /* INSTITUTION PROGRAMME CATEGORY */
    public InstitutionProgrammeCategory createProgrammeCategory(ProgrammeTypeDTO programmeTypeDTO) {
        InstitutionProgrammeCategory newInstitutionProgrammeCategory = new InstitutionProgrammeCategory();
        newInstitutionProgrammeCategory.setAliasDescription(programmeTypeDTO.getAliasDescription());
        newInstitutionProgrammeCategory.setCode(programmeTypeDTO.getCode());
        newInstitutionProgrammeCategory.setStatus(true);
        newInstitutionProgrammeCategory.setDescription(programmeTypeDTO.getDescription());

        return programmeCategoryServiceImpl.createProgrammeCategory(newInstitutionProgrammeCategory);
    }

    public InstitutionProgrammeCategory updateProgrammeCategory(ProgrammeTypeDTO programmeTypeDTO) {
        InstitutionProgrammeCategory institutionProgrammeCategory = programmeCategoryServiceImpl.getProgrammeCategory(programmeTypeDTO.getId());
        institutionProgrammeCategory.setCode(programmeTypeDTO.getCode());
        institutionProgrammeCategory.setDescription(programmeTypeDTO.getDescription());
        institutionProgrammeCategory.setAliasDescription(programmeTypeDTO.getAliasDescription());
        return programmeCategoryServiceImpl.updateProgrammeCategory(institutionProgrammeCategory);
    }

    public List<InstitutionProgrammeCategory> getAllProgrammeCategory() {
        return programmeCategoryServiceImpl.getAllProgrammeCategory();
    }

    public void deleteProgrammeCategory(Long id) {
        return;
    }

    //    Deprecated
    public InstitutionProgrammeCategory getProgrammeTypesByCategory(Long id) {
        return programmeCategoryServiceImpl.getProgrammeCategory(id);
    }


    /* INSTITUTION PROGRAMME TYPE */
    public InstitutionProgrammeType createProgrammeType(ProgrammeTypeDTO programmeTypeDTO) {
        InstitutionProgrammeCategory institutionProgrammeCategory = programmeCategoryServiceImpl.getProgrammeCategory(programmeTypeDTO.getInstitutionProgrammeCategoryId());
        InstitutionProgrammeType newInstitutionProgrammeType = new InstitutionProgrammeType();
        newInstitutionProgrammeType.setAliasDescription(programmeTypeDTO.getAliasDescription());
        newInstitutionProgrammeType.setCode(programmeTypeDTO.getCode());
        newInstitutionProgrammeType.setStatus(true);
        newInstitutionProgrammeType.setDescription(programmeTypeDTO.getDescription());
        newInstitutionProgrammeType.setInstitutionProgrammeCategory(institutionProgrammeCategory);
        return programmeTypeServiceImpl.createInstitutionProgrammeType(newInstitutionProgrammeType);
    }

    public List<InstitutionProgrammeType> getAllProgrammeType() {
        return programmeTypeServiceImpl.getAllInstitutionProgrammeType();
    }

    public List<ProgrammeTypeDTO> getInstitutionProgrammeTypeByProgrammeCategory(long id) {
        InstitutionProgrammeCategory institutionProgrammeCategory = new InstitutionProgrammeCategory();
        institutionProgrammeCategory.setId(id);

        List<InstitutionProgrammeType> institutionProgrammeTypeList = programmeTypeServiceImpl.getInstitutionProgrammeTypeByProgrammeCategory(institutionProgrammeCategory);
        List<ProgrammeTypeDTO> programmeTypeDTOList = new ArrayList<>();

        for (InstitutionProgrammeType ins : institutionProgrammeTypeList) {
            ProgrammeTypeDTO programmeTypeDTO = new ProgrammeTypeDTO();
            programmeTypeDTO.setId(ins.getId());
            programmeTypeDTO.setDescription(ins.getDescription());
            programmeTypeDTOList.add(programmeTypeDTO);
        }
        return programmeTypeDTOList;
    }


    public InstitutionProgrammeType updateInstitutionProgrammeType(ProgrammeTypeDTO programmeTypeDTO) {
        InstitutionProgrammeType institutionProgrammeType = programmeTypeServiceImpl.getInstitutionProgrammeTypeById(programmeTypeDTO.getId());

        institutionProgrammeType.setCode(programmeTypeDTO.getCode());
        institutionProgrammeType.setDescription(programmeTypeDTO.getDescription());
        institutionProgrammeType.setAliasDescription(programmeTypeDTO.getAliasDescription());
        institutionProgrammeType.setMerchantId(programmeTypeDTO.getMerchantId());
        institutionProgrammeType.setCertificate(programmeTypeDTO.getCertificate());

        if (programmeTypeDTO.getInstitutionProgrammeCategoryId() != 0) {
            InstitutionProgrammeCategory institutionProgrammeCategory = new InstitutionProgrammeCategory();
            institutionProgrammeCategory.setId(programmeTypeDTO.getInstitutionProgrammeCategoryId());
            institutionProgrammeType.setInstitutionProgrammeCategory(institutionProgrammeCategory);
        }
        return programmeTypeServiceImpl.updateInstitutionProgrammeType(institutionProgrammeType);
    }


    //COURSE OF STUDY DURATION
    public InstitutionCourseDuration getInstitutionCourseDuration(long id) {
        return courseDurationService.getInstitutionCourseDuration(id);
    }

    public void createInstitutionCourseDurationByList(CourseOfStudyDTO courseOfStudyDTO) {
        courseDurationService.createInstitutionCourseDurationByList(courseOfStudyDTO.getCourseDurationList());
    }

    public InstitutionCourseDuration createInstitutionCourseDuration(CourseOfStudyDTO courseOfStudyDTO) {
        InstitutionCourseDuration institutionCourseDuration = new InstitutionCourseDuration();

        institutionCourseDuration.setId(courseOfStudyDTO.getId());
        institutionCourseDuration.setDuration(courseOfStudyDTO.getDuration());
        institutionCourseDuration.setNumberOfLevels(courseOfStudyDTO.getNumberOfLevels());
        institutionCourseDuration.setInstitutionProgrammeType(new InstitutionProgrammeType(courseOfStudyDTO.getInstitutionProgrammeType()));
        institutionCourseDuration.setInstitutionStructure(new InstitutionStructure(courseOfStudyDTO.getInstitutionStructure()));
        return courseDurationService.createInstitutionCourseDuration(institutionCourseDuration);
    }

    public InstitutionCourseDuration updateInstitutionCourseDuration(CourseOfStudyDTO courseOfStudyDTO) {
        InstitutionCourseDuration institutionCourseDuration = courseDurationService.getInstitutionCourseDuration(courseOfStudyDTO.getId());
        institutionCourseDuration.setId(courseOfStudyDTO.getId());
        institutionCourseDuration.setDuration(courseOfStudyDTO.getDuration());
        institutionCourseDuration.setNumberOfLevels(courseOfStudyDTO.getNumberOfLevels());
        institutionCourseDuration.setInstitutionProgrammeType(new InstitutionProgrammeType(courseOfStudyDTO.getInstitutionProgrammeType()));
        institutionCourseDuration.setInstitutionStructure(new InstitutionStructure(courseOfStudyDTO.getInstitutionStructure()));
        return courseDurationService.updateInstitutionCourseDuration(institutionCourseDuration);
    }

    public void deleteInstitutionCourseDuration(CourseOfStudyDTO courseOfStudyDTO) {
    }

    public List<InstitutionCourseDuration> getAllInstitutionCourseDuration(CourseOfStudyDTO courseOfStudyDTO) {
        return courseDurationService.getAllInstitutionCourseDuration();
    }

    // SEMESTER SETUP
    public InstitutionSemester createInstitutionSemester(InstitutionSemester institutionSemester) {
        return semesterServiceImpl.createInstitutionSemester(institutionSemester);
    }

    public void createInstitutionSemesterFromList(SemesterDTO semesterDTO) {
        semesterServiceImpl.createInstitutionSemesterFromList(semesterDTO.getInstitutionSemesterList());
    }

    public InstitutionSemester updateInstitutionSemester(SemesterDTO semesterDTO) {
        InstitutionSemester institutionSemester = semesterServiceImpl.getInstitutionSemesterById(semesterDTO.getId());
        institutionSemester.setDescription(semesterDTO.getDescription());
        institutionSemester.setHierarchy(semesterDTO.getHierarchy());
        return semesterServiceImpl.updateInstitutionSemester(institutionSemester);
    }

    public List<InstitutionSemester> getAllInstitutionSemester() {
        return semesterServiceImpl.getAllInstitutionSemester();
    }

    public void deleteInstitutionSemester(long id) {
    }

    public InstitutionSemester getInstitutionSemesterById(long id) {
        return semesterServiceImpl.getInstitutionSemesterById(id);
    }

    public InstitutionSemester setCurrentSemester(SessionDTO sessionDTO) {
        return null;
    }

    //SESSION SETUP
    public InstitutionSession getInstitutionSessionById(long id) {
        return institutionSessionService.getInstitutionSessionById(id);
    }

    public InstitutionSession createInstitutionSession(SessionDTO sessionDTO) {
        InstitutionSession institutionSession = new InstitutionSession();
        institutionSession.setCode(sessionDTO.getCode());
        institutionSession.setDescription(sessionDTO.getDescription());
        institutionSession.setStartDate(sessionDTO.getStartDate());
        institutionSession.setEndDate(sessionDTO.getEndDate());
        institutionSession.setProgrammeType(sessionDTO.getInstitutionProgrammeType());

        return institutionSessionService.createInstitutionSession(institutionSession);
    }

    public InstitutionSession updateInstitutionSession(InstitutionSession institutionSession) {
        return institutionSessionService.updateInstitutionSession(institutionSession);
    }

    public void disableInstitutionSessionById(long id) {
        institutionSessionService.disableInstitutionSessionById(id);
    }

    public void deleteInstitutionSessionById(long id) {
        institutionSessionService.deleteInstitutionSessionById(id);
    }

    public List<InstitutionSession> getAllInstitutionSession() {
        return institutionSessionService.getAllInstitutionSession();
    }

    public void setInstitutionCurrentSession(SessionDTO sessionDTO) {
        InstitutionCurrentSession institutionCurrentSession = institutionCurrentSessionService.getInstitutionCurrentSessionByProgrammeType(sessionDTO.getInstitutionProgrammeType());
        InstitutionCurrentSession institutionCurrentSession1 = new InstitutionCurrentSession();

        if (institutionCurrentSession == null) {

            institutionCurrentSession1.setInstitutionProgrammeType(sessionDTO.getInstitutionProgrammeType());
            institutionCurrentSession1.setInstitutionSession(sessionDTO.getInstitutionSession());
        } else {
            institutionCurrentSession1.setInstitutionProgrammeType(sessionDTO.getInstitutionProgrammeType());
            institutionCurrentSession1.setInstitutionSession(sessionDTO.getInstitutionSession());
            institutionCurrentSession1.setId(institutionCurrentSession.getId());
            institutionCurrentSession1.setInstitutionSemester(institutionCurrentSession.getInstitutionSemester());
            institutionCurrentSession1.setCreatedAt(institutionCurrentSession.getCreatedAt());

        }


        institutionCurrentSessionService.saveOrUpdateInstitutionCurrentSession(institutionCurrentSession1);
    }

    public InstitutionCurrentSession getInstitutionCurrentSessionByProgrammeType(long id) {
        return institutionCurrentSessionService.getInstitutionCurrentSessionByProgrammeType(new InstitutionProgrammeType(id));
    }

    public void setInstitutionCurrentSemester(SessionDTO sessionDTO) {
        InstitutionCurrentSession institutionCurrentSemester = institutionCurrentSessionService.getInstitutionCurrentSessionByProgrammeType(sessionDTO.getInstitutionProgrammeType());
        InstitutionCurrentSession institutionCurrentSemester1 = new InstitutionCurrentSession();

        if (institutionCurrentSemester == null) {
            institutionCurrentSemester1.setInstitutionProgrammeType(sessionDTO.getInstitutionProgrammeType());
            institutionCurrentSemester1.setInstitutionSemester(sessionDTO.getInstitutionSemester());
        } else {
            institutionCurrentSemester1.setInstitutionProgrammeType(sessionDTO.getInstitutionProgrammeType());
            institutionCurrentSemester1.setInstitutionSemester(sessionDTO.getInstitutionSemester());
            institutionCurrentSemester1.setId(institutionCurrentSemester.getId());
            institutionCurrentSemester1.setInstitutionSession(institutionCurrentSemester.getInstitutionSession());
            institutionCurrentSemester1.setCreatedAt(institutionCurrentSemester.getCreatedAt());

        }

        institutionCurrentSessionService.setInstitutionCurrentSemester(institutionCurrentSemester1);

    }

    //Get List of session by programme type
    public List<SessionDTO> getAllInstitutionSessionByProgrammeType(long id) {
        InstitutionProgrammeType programmeType = new InstitutionProgrammeType(id);
        List<InstitutionSession> sessionList = institutionSessionService.getAllInstitutionSessionByProgrammeType(programmeType);
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        sessionList.forEach(s -> sessionDTOList.add(new SessionDTO(s.getId(), s.getDescription())));
        return sessionDTOList;
    }

    public InstitutionProgrammeType getInstitutionProgrammeTypeById(long id) {
        return programmeTypeServiceImpl.getInstitutionProgrammeTypeById(id);
    }

    public InstitutionLevel getInstitutionLevelById(Long id) {
        return institutionLevelServiceImpl.getInstitutionLevel(id);
    }
}
