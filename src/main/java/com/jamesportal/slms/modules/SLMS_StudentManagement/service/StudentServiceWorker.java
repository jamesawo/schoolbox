
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.service;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service.CommonServiceImpl;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.dto.PaginationDto;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.dto.SlmsIdentityDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.service.SlmsIdentityServiceWorker;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service.StudentEntryTypeServiceImpl;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service.StudentPassportServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.ProgrammeTypeServiceImpl;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentProfileDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import com.jamesportal.slms.modules.SLMS_UserManagement.service.UserServiceWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceWorker {
    private final SlmsIdentityServiceWorker slmsIdentityServiceWorker;
    private final StudentServiceImpl studentServiceimpl;
    private final UserServiceWorker userServiceWorker;
    private final CommonServiceImpl commonService;
    private final ProgrammeTypeServiceImpl programmeTypeService;
    private final InstitutionStructureServiceX institutionStructureServiceX;
    private final StudentEntryTypeServiceImpl entryTypeService;
    private final StudentPassportServiceImpl studentPassportService;
    private final StudentReportService reportService;
    private final GlobalSettingServiceWorker globalSettingService;
    
    public void deactivateStudent(long id) {
        studentServiceimpl.activateStudent(id);
    }
    
    public void activateStudent(long id) {
        studentServiceimpl.deactivateStudent(id);
    }
    
    private Student getStudentById(long id) {
        return studentServiceimpl.getStudentById(id);
    }

    public Student getStudentDetailsByRegNumber(String regNumber){
        return studentServiceimpl.getStudentByRegNumber(regNumber);
    }
    
    public List<Student> getAllStudent() {
        return studentServiceimpl.getAllStudent();
    }
    
    private void setStudent(StudentDTO studentDTO, Student student) {
        student.setSurname(studentDTO.getSurname());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGender(studentDTO.getGender());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEntryType(studentDTO.getEntryType());
        student.setRegNumber(studentDTO.getRegNumber());
        student.setInstitutionStructure(studentDTO.getInstitutionStructure());
        student.setLevel(studentDTO.getLevel());
        student.setProgrammeType(studentDTO.getProgrammeType());
        student.setSession(studentDTO.getSession());
        student.setMaritalStatus(studentDTO.getMaritalStatus());
        setUpdateStudent(studentDTO, student);
        student.setContactAddress(studentDTO.getContactAddress());
        student.setPostalAddress(studentDTO.getPostalAddress());
        student.setGuardianFullName(studentDTO.getGuardianFullName());
        student.setGuardianOccupation(studentDTO.getGuardianOccupation());
        student.setGuardianRelationship(studentDTO.getGuardianRelationship());
        student.setGuardianPhone(studentDTO.getGuardianPhone());
        student.setGuardianAddress(studentDTO.getGuardianAddress());
        student.setGuardianEmail(studentDTO.getGuardianEmail());
    }
    
    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        setStudent(studentDTO, student);
        User newUser = userServiceWorker.createUserForNewStudent(studentDTO);
        Student newStudent = studentServiceimpl.createStudent(student);
        slmsIdentityServiceWorker.createSlmsIdentity(new SlmsIdentityDTO(newStudent.getId(), newUser.getId()));
        return newStudent;
    }
    
    public void updateStudent(StudentDTO studentDTO) throws IOException {
        Student student = studentServiceimpl.getStudentById(studentDTO.getId());
        student.setMaritalStatus(studentDTO.getMaritalStatus());
        student.setEmail(studentDTO.getEmail());

        setUpdateStudent(studentDTO, student);

        student.setPostalAddress(studentDTO.getPostalAddress());
        student.setContactAddress(studentDTO.getContactAddress());
        student.setGuardianFullName(studentDTO.getGuardianFullName());
        student.setGuardianOccupation(studentDTO.getGuardianOccupation());
        student.setGuardianRelationship(studentDTO.getGuardianRelationship());
        student.setGuardianPhone(studentDTO.getGuardianPhone());
        student.setGuardianAddress(studentDTO.getGuardianAddress());
        student.setGuardianEmail(studentDTO.getGuardianEmail());

        MultipartFile file = studentDTO.getPassport();
        try {
            updateStudentPassport(file, student);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        studentServiceimpl.updateStudent(student);
    }
    
    public void updateStudent(StudentProfileDTO studentProfileDTO) throws IOException, ParseException {
        //Todo::Check some validation before saving
        Student student = studentServiceimpl.getStudentById(studentProfileDTO.getId());
        student.setId(student.getId());
        student.setSurname(studentProfileDTO.getSurname());
        student.setFirstName(studentProfileDTO.getFirstName());
        student.setLastName(studentProfileDTO.getLastName());

        //TODO::format and validate date
        String stringDate = studentProfileDTO.getDateOfBirth();
        SimpleDateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = stringDateFormat.parse(stringDate);

        student.setDateOfBirth(dateFormat);
        student.setGender(studentProfileDTO.getGender());
        student.setEmail(studentProfileDTO.getEmail());
        if (!ObjectUtils.isEmpty(studentProfileDTO) && !ObjectUtils.isEmpty(studentProfileDTO.getRegNumber())) {
            student.setRegNumber(studentProfileDTO.getRegNumber());
        }
        student.setMaritalStatus(studentProfileDTO.getMaritalStatus());
        student.setPhone(studentProfileDTO.getPhone());
        student.setHeight(studentProfileDTO.getHeight());
        student.setWeight(studentProfileDTO.getWeight());
        student.setTown(studentProfileDTO.getTown());
        student.setFacialMarks(studentProfileDTO.getFacialMarks());
        student.setContactAddress(studentProfileDTO.getContactAddress());
        student.setPostalAddress(studentProfileDTO.getPostalAddress());
        student.setGuardianFullName(studentProfileDTO.getGuardianFullName());
        student.setGuardianOccupation(studentProfileDTO.getGuardianOccupation());
        student.setGuardianRelationship(studentProfileDTO.getGuardianRelationship());
        student.setGuardianPhone(studentProfileDTO.getGuardianPhone());
        student.setGuardianAddress(studentProfileDTO.getGuardianAddress());
        student.setGuardianEmail(studentProfileDTO.getGuardianEmail());
        student.setCountry(studentProfileDTO.getStudentCountry());
        student.setState(studentProfileDTO.getStudentState());
        student.setLga(studentProfileDTO.getStudentLga());
        student.setLevel(studentProfileDTO.getInstitutionLevel());
        if (studentProfileDTO.getInstitutionStructure() != null) {
            student.setInstitutionStructure(studentProfileDTO.getInstitutionStructure());
        }
        if (studentProfileDTO.getInstitutionProgrammeType() != null) {
            student.setProgrammeType(studentProfileDTO.getInstitutionProgrammeType());
        }
        student.setEntryType(studentProfileDTO.getStudentEntryType());
        updateStudentPassport(studentProfileDTO.getPassport(), student);
        studentServiceimpl.updateStudent(student);
    }

    private void updateStudentPassport(MultipartFile file, Student student) throws IOException, ParseException  {
        if (file != null && !file.isEmpty()) {
            if (student.getPassport() != null) {
                StudentPassport passport = studentPassportService.updateStudentPassport(new StudentPassport(student.getPassport().getId(), file.getBytes()));
                student.setPassport(passport);
            } else {
                StudentPassport passport = studentPassportService.createStudentPassport(new StudentPassport(file.getBytes()));
                student.setPassport(passport);
            }
        }
    }

    private void setUpdateStudent(StudentDTO studentDTO, Student student) {
        student.setPhone(studentDTO.getPhone());
        student.setHeight(studentDTO.getHeight());
        student.setWeight(studentDTO.getWeight());
        student.setCountry(studentDTO.getCountry());
        student.setState(studentDTO.getState());
        student.setLga(studentDTO.getLga());
        student.setTown(studentDTO.getTown());
        student.setFacialMarks(studentDTO.getFacialMarks());
    }
    
    public StudentProfileDTO getAuthUserStudentDetails(String authUsername) {
        SlmsIdentity slmsIdentity = commonService.getCurrentAuthUserIdentityRecord(authUsername);
        Student student = getStudentById(slmsIdentity.getStudent().getId());
        StudentProfileDTO studentProfileDTO = new StudentProfileDTO();
        setStudentProfileDTO(student, studentProfileDTO);
        return studentProfileDTO;
        
    }
    
    public StudentProfileDTO getStudentProfileDetailsByStudentId(long id) {
        Student student = getStudentById(id);
        StudentProfileDTO studentProfileDTO = new StudentProfileDTO();
        setStudentProfileDTO(student, studentProfileDTO);
        return studentProfileDTO;
    }
    
    private void setStudentProfileDTO(Student student, StudentProfileDTO studentProfileDTO) {
        
        studentProfileDTO.setId(student.getId());
        studentProfileDTO.setFullName(student.getFullName());
        studentProfileDTO.setFirstName(student.getFirstName());
        studentProfileDTO.setSurname(student.getSurname());
        studentProfileDTO.setLastName(student.getLastName());
        studentProfileDTO.setRegNumber(student.getRegNumber());
        if (student.getProgrammeType() != null)
            studentProfileDTO.setProgrammeType(student.getProgrammeType().getDescription());
        studentProfileDTO.setLevel(student.getLevel().getAlternative());
        studentProfileDTO.setLevelValue(student.getLevel().getId());
        studentProfileDTO.setEmail(student.getEmail());
        studentProfileDTO.setInstitutionStructure(student.getInstitutionStructure());
        
        InstitutionCurrentSession currentSession = institutionStructureServiceX.getInstitutionCurrentSessionByProgrammeType(student.getProgrammeType().getId());
        if (currentSession != null) {
            studentProfileDTO.setCurrentSession(currentSession.getInstitutionSession().getDescription());
        }
        
        studentProfileDTO.setProgrammeOfStudyStructure1(getStudentInstitutionStructureMap(student.getInstitutionStructure().getId()));
        studentProfileDTO.setInstitutionStructureMapData(getStudentInstitutionStructureDataMap(student.getInstitutionStructure().getId()));
        
        studentProfileDTO.setInstitutionProgrammeType(student.getProgrammeType());
        studentProfileDTO.setProgrammeCategory(student.getProgrammeType().getInstitutionProgrammeCategory());
        studentProfileDTO.setInstitutionProgrammeTypeList(programmeTypeService.getInstitutionProgrammeTypeByProgrammeCategory(student.getProgrammeType().getInstitutionProgrammeCategory()));
        studentProfileDTO.setProgrammeCategoryList(institutionStructureServiceX.getAllProgrammeCategory());
        studentProfileDTO.setEntryTypeList(entryTypeService.getAllStudentEntryType());
        studentProfileDTO.setLevelList(institutionStructureServiceX.getAllInstitutionLevel());
        
        if (student.getEntryType() != null)
            studentProfileDTO.setEntryType(student.getEntryType().getDescription());
        
        if (student.getLevel() != null)
            studentProfileDTO.setLevel(student.getLevel().getAlternative());
        
        if (student.getPhone() != null)
            studentProfileDTO.setPhone(student.getPhone());
        
        if (student.getMaritalStatus() != null)
            studentProfileDTO.setMaritalStatus(student.getMaritalStatus());
        
        if (student.getHeight() != null)
            studentProfileDTO.setHeight(student.getHeight());
        
        if (student.getWeight() != null)
            studentProfileDTO.setWeight(student.getWeight());
        
        if (student.getLga() != null) {
            studentProfileDTO.setLga(student.getLga().getDescription());
            studentProfileDTO.setLgaValue(student.getLga() != null ? student.getLga().getId() : 0);
            studentProfileDTO.setLgaList(student.getState().getLocals());
        }
        
        if (student.getTown() != null)
            studentProfileDTO.setTown(student.getTown());
        
        if (student.getFacialMarks() != null)
            studentProfileDTO.setFacialMarks(student.getFacialMarks());
        
        if (student.getContactAddress() != null)
            studentProfileDTO.setContactAddress(student.getContactAddress());
        
        if (student.getGender() != null)
            studentProfileDTO.setGender(student.getGender());
        
        if (student.getPostalAddress() != null)
            studentProfileDTO.setPostalAddress(student.getPostalAddress());
        
        if (student.getState() != null)
            studentProfileDTO.setState(student.getState().getDescription());

        if (student.getDateOfBirth() != null)
            studentProfileDTO.setDateOfBirth(student.getDateOfBirth().toString());
        
        if (student.getCountry() != null)
            studentProfileDTO.setCountry(student.getCountry().getDescription());
        
        if (student.getSession() != null)
            studentProfileDTO.setSession(student.getSession().getDescription());
        
        if (student.getGuardianFullName() != null)
            studentProfileDTO.setGuardianFullName(student.getGuardianFullName());
        
        if (student.getGuardianOccupation() != null)
            studentProfileDTO.setGuardianOccupation(student.getGuardianOccupation());
        
        if (student.getGuardianRelationship() != null)
            studentProfileDTO.setGuardianRelationship(student.getGuardianRelationship());
        
        if (student.getGuardianPhone() != null)
            studentProfileDTO.setGuardianPhone(student.getGuardianPhone());
        
        if (student.getGuardianAddress() != null)
            studentProfileDTO.setGuardianAddress(student.getGuardianAddress());
        
        if (student.getGuardianEmail() != null)
            studentProfileDTO.setGuardianEmail(student.getGuardianEmail());
        
        if (student.getPassport() != null) {
            byte[] passport = student.getPassport().getData();
            String encodedPassport = Base64.getEncoder().encodeToString(passport);
            studentProfileDTO.setPassportData(encodedPassport);
        }
        
    }
    
    //TODO::Clean Method
    // WIP::Check student structure if it matches institution structure
    private Map<Integer, String[]> getStudentInstitutionStructureMap(long institutionStructureId) {
        Map<Integer, String[]> studentInstitutionStructureMapUnSorted = new HashMap<>();
        Map<Integer, String[]> studentInstitutionStructureMapSorted = new HashMap<>();
        boolean hasNext = true;
        long institutionChecker = institutionStructureId;
        while (hasNext) {
            InstitutionStructure institutionStructure = institutionStructureServiceX.getInstitutionStructureById(institutionChecker);
            if (institutionStructure.getParent() != null) {
                studentInstitutionStructureMapUnSorted.put(institutionStructure.getCategory().getHierarchy(), new String[]{institutionStructure.getCategory().getDescription(), institutionStructure.getDescription()});
                institutionChecker = institutionStructure.getParent().getId();
            } else {
                studentInstitutionStructureMapUnSorted.put(institutionStructure.getCategory().getHierarchy(), new String[]{institutionStructure.getCategory().getDescription(), institutionStructure.getDescription()});
                hasNext = false;
            }
        }
        studentInstitutionStructureMapUnSorted.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> studentInstitutionStructureMapSorted.put(x.getKey(), x.getValue()));
        return studentInstitutionStructureMapSorted;
    }
    
    //TODO::Refactor code
    private Map<Integer, Map<String, List<Object[]>>> getStudentInstitutionStructureDataMap(long studentInstitutionStructureId) {
        List<Object[]> objects = new ArrayList<>();
        Map<String, List<Object[]>> detailMap = new HashMap<>();
        Map<Integer, Map<String, List<Object[]>>> finalDetailMap = new HashMap<>();
        List<InstitutionCategory> institutionCategories = institutionStructureServiceX.getAllCategoryNames();
        List<InstitutionStructure> institutionStructureListByLeastHierarchy = institutionStructureServiceX.getInstitutionStructureByLeastCategoryHierarchy();
        InstitutionStructure studentInstitutionStructureByLeastCategory = getLeastInstitutionStructureRecordByInstitutionStructureParent(studentInstitutionStructureId); // Selected in dropdown
        long parentID = studentInstitutionStructureByLeastCategory.getId();
        boolean hasNext = true;
        while (hasNext) {
            //TODO::Get the least hierarchy and increment it. Remove magic int
            for (InstitutionCategory c : institutionCategories) {
                if (c.getHierarchy() == 1) {
                    for (InstitutionStructure l : institutionStructureListByLeastHierarchy) {
                        objects.add(new Object[]{l.getId(), l.getDescription(), studentInstitutionStructureByLeastCategory.getId()});
                    }
                    detailMap.put(c.getDescription(), objects);
                    finalDetailMap.put(c.getHierarchy(), detailMap);
    
                }
                if (c.getHierarchy() > 1) {
                    List<Object[]> tempObjects = new ArrayList<>();
                    Map<String, List<Object[]>> tempDetailMap = new HashMap<>();
                    List<InstitutionStructure> institutionStructureList = getInstitutionStructureListByCategoryIdAndParentId(c.getId(), parentID);
                    InstitutionStructure nextInstitutionStructure = getInstitutionStructureBasedOnCategoryIdForInstitutionStructureId(c.getId(), studentInstitutionStructureId);
                    parentID = nextInstitutionStructure.getId();
                    for (InstitutionStructure k : institutionStructureList) {
                        tempObjects.add(new Object[]{k.getId(), k.getDescription(), nextInstitutionStructure.getId()});
                    }
                    tempDetailMap.put(c.getDescription(), tempObjects);
                    finalDetailMap.put(c.getHierarchy(), tempDetailMap);
                }
            }
            hasNext = false;
        }
        return finalDetailMap;
    }
    
    // get faculty, department, programme an institution structure belongs to
    //TODO:: move to institution structure
    private InstitutionStructure getInstitutionStructureBasedOnCategoryIdForInstitutionStructureId(long categoryId, long studentInstitutionStructureId) {
        boolean hasNext = true;
        long nextInstitutionId = studentInstitutionStructureId;
        InstitutionStructure leastInstitutionStructureByParent = new InstitutionStructure();
        while (hasNext) {
            InstitutionStructure institutionStructure = institutionStructureServiceX.getInstitutionStructureById(nextInstitutionId);
            if (institutionStructure.getParent() != null) {
                if (institutionStructure.getCategory().getId() == categoryId) {
                    leastInstitutionStructureByParent.setId(institutionStructure.getId());
                    leastInstitutionStructureByParent.setCategory(institutionStructure.getCategory());
                    leastInstitutionStructureByParent.setDescription(institutionStructure.getDescription());
                    leastInstitutionStructureByParent.setChildren(institutionStructure.getChildren());
                    leastInstitutionStructureByParent.setCode(institutionStructure.getCode());
                    hasNext = false;
                } else {
                    nextInstitutionId = institutionStructure.getParent().getId();
                }
            }
        }
        return leastInstitutionStructureByParent;
    }
    
    // TODO: Move to Institution structure
    private List<InstitutionStructure> getInstitutionStructureListByCategoryIdAndParentId(long categoryId, long parentId) {
        return institutionStructureServiceX.getInstitutionStructureListByCategoryIdAndParentId(categoryId, parentId);
    }
    
    // WIP:  Move to institution structure service
    private InstitutionStructure getLeastInstitutionStructureRecordByInstitutionStructureParent(long parentId) {
        boolean hasNext = true;
        long nextInstitutionId = parentId;
        InstitutionStructure leastInstitutionStructureByParent = new InstitutionStructure();
        while (hasNext) {
            InstitutionStructure institutionStructure = institutionStructureServiceX.getInstitutionStructureById(nextInstitutionId);
            if (institutionStructure.getParent() != null) {
                nextInstitutionId = institutionStructure.getParent().getId();
            } else {
                leastInstitutionStructureByParent.setId(institutionStructure.getId());
                leastInstitutionStructureByParent.setCategory(institutionStructure.getCategory());
                leastInstitutionStructureByParent.setDescription(institutionStructure.getDescription());
                leastInstitutionStructureByParent.setChildren(institutionStructure.getChildren());
                leastInstitutionStructureByParent.setCode(institutionStructure.getCode());
                hasNext = false;
            }
        }
        return leastInstitutionStructureByParent;
    }
    
    
    public PaginationDto searchStudentList(StudentSearchParamDTO studentSearchParamDto) {

        List<Long> longList = setInstStructureListFromSearchFilter(studentSearchParamDto.getInstitutionStructure(), studentSearchParamDto.getInstitutionStructureValue());
        studentSearchParamDto.setInstitutionStructureIds(longList);

        Page<Student> students = studentServiceimpl.StudentSearchCriteria(studentSearchParamDto);
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setHasNext(students.hasNext());
        paginationDto.setHasPrevious(students.hasPrevious());
        paginationDto.setPageNumber(students.getNumber());
        paginationDto.setTotalPages(students.getTotalPages());
        paginationDto.setPageSize(students.getSize());
        paginationDto.setTotalResultSize(students.getTotalElements());
        
        List<StudentProfileDTO> studentProfileDTOS = new ArrayList<>();
        students.forEach(x -> {
            StudentProfileDTO profileDTO = new StudentProfileDTO();
            profileDTO.setId(x.getId());
            profileDTO.setSurname(x.getSurname());
            profileDTO.setFirstName(x.getFirstName());
            profileDTO.setLastName(x.getLastName());
    
            profileDTO.setCourseOfStudy(x.getInstitutionStructure().getDescription());
            profileDTO.setProgrammeType(x.getProgrammeType().getDescription());
            profileDTO.setRegNumber(x.getRegNumber());
            profileDTO.setEmail(x.getEmail());
            profileDTO.setGender(x.getGender());
            if (x.getState() != null)
                profileDTO.setState(x.getState().getDescription());
            profileDTO.setEntryType(x.getEntryType().getDescription());
            profileDTO.setLevel(x.getLevel().getAlternative());
            profileDTO.setStatus(x.isStatus());
            profileDTO.setPhone(x.getPhone());
            profileDTO.setJambReg(x.getJambReg());
            profileDTO.setApplicationNumber(x.getApplicationNumber());
            studentProfileDTOS.add(profileDTO);
            
        });
        
        paginationDto.setStudentProfileDTOS(studentProfileDTOS);
        return paginationDto;
    }

    //todo::move block to institution service worker
    public List<Long> setInstStructureListFromSearchFilter(@Nullable String instStructureDescription, @Nullable long instStrValue){

        List<Long> finalList = new ArrayList<>();
        Optional<InstitutionStructure> institutionStructure = Optional.of(new InstitutionStructure());


        assert instStructureDescription != null;
        if (!instStructureDescription.equals("") || instStrValue != 0) {

            if (!instStructureDescription.equals("")) {
                institutionStructure = Optional.ofNullable(institutionStructureServiceX.getInstitutionStructureByDescription(instStructureDescription));
            } else {
                institutionStructure = Optional.ofNullable(institutionStructureServiceX.getInstitutionStructureById(instStrValue));
            }

            if (institutionStructure.isPresent()) {
                if (checkIsLastInstitutionCategory(institutionStructure.get().getCategory().getId())) {
                    finalList.add(institutionStructure.get().getId());
                } else {
                    long childrenCategory = institutionStructure.get().getCategory().getId() + 1; // remove magic number replace with dynamic value (create method getNextCategory() that accepts categoryId and uses the hierarchy to get the next categoryId
                    List<InstitutionStructure> strIds = getLastListOfChildrenByInstitutionStructureParent(institutionStructure.get().getChildren(), childrenCategory);
                    strIds.stream().map(x -> {
                        finalList.add(x.getId());
                        return finalList;
                    }).collect(Collectors.toList());
                }
                return finalList;

            }
        }

        return finalList;
    }
    
    //todo::Move block to institution structure service worker
    @Transactional
    public List<InstitutionStructure> getLastListOfChildrenByInstitutionStructureParent(List<InstitutionStructure> inst, long instCategory) {
        List<InstitutionStructure> listOfIds = new ArrayList<>();
        long lastCategoryId = institutionStructureServiceX.getInstitutionCategoryByHighestHierarchy().getId();
        long currentCategory;
        List<InstitutionStructure> currentIns = inst;
        if (instCategory == lastCategoryId) {
            listOfIds.addAll(currentIns);
            return listOfIds;
        } else {
            boolean next = true;
            while (next) {
                for (InstitutionStructure ins : currentIns) {
                    currentCategory = ins.getCategory().getId();
                    if (currentCategory == lastCategoryId) {
                        listOfIds.add(ins);
                    } else {
                        List<InstitutionStructure> lastList;  // remove magic incrementing number -- +1, replace with dynamic integer
                        lastList = getLastListOfChildrenByInstitutionStructureParent(ins.getChildren(), ins.getCategory().getId() + 1); // use method (accepts categoryid and uses hierarchy to get next) next category to handle
                        if (lastList != null) {
                            listOfIds.addAll(lastList);
                        }
                    }
                }
                next = false;
            }
        }
        return listOfIds;
    }
    
    //todo::Move function to Institution Service Worker
    private boolean checkIsLastInstitutionCategory(long institutionCategoryIdToCheck) {
        InstitutionCategory lastCategory = institutionStructureServiceX.getInstitutionCategoryByHighestHierarchy();
        return lastCategory.getId() == institutionCategoryIdToCheck;
    }
    
    private List<StudentProfileDTO> getStudentReportData(StudentSearchParamDTO searchParamDTO) {
        
        List<Student> studentList = studentServiceimpl.getStudentReportParameter(searchParamDTO);
        
        List<StudentProfileDTO> studentProfileDTOS = new ArrayList<>();
        
        studentList.forEach(x -> {
            StudentProfileDTO profileDTO = new StudentProfileDTO();
            profileDTO.setCourseOfStudy(x.getInstitutionStructure().getDescription());
            profileDTO.setProgrammeType(x.getProgrammeType().getDescription());
            profileDTO.setSurname(x.getSurname());
            profileDTO.setFirstName(x.getFirstName());
            profileDTO.setLastName(x.getLastName());
            profileDTO.setRegNumber(x.getRegNumber());
            profileDTO.setEmail(x.getEmail());
            profileDTO.setGender(x.getGender());
            profileDTO.setState(x.getState() != null ? x.getState().getDescription() : "-");
            profileDTO.setEntryType(x.getEntryType().getDescription());
            profileDTO.setLevel(x.getLevel().getAlternative());
            profileDTO.setPhone(x.getPhone());
            profileDTO.setJambReg(x.getJambReg());
            profileDTO.setApplicationNumber(x.getApplicationNumber());
            studentProfileDTOS.add(profileDTO);
        });
        
        return studentProfileDTOS;
    }

	public void prepareStudentReportData(StudentSearchParamDTO searchParamDTO, HttpServletResponse response) throws FileNotFoundException {
        
        List<StudentProfileDTO> resultSet = getStudentReportData(searchParamDTO);
        assert searchParamDTO.getReportFormat() != null;
      
        if (searchParamDTO.getReportFormat() != null){
    
            switch (searchParamDTO.getReportFormat()) {
                case "PDF":
                    String generatedReportPath = reportService.generateReportToPDF(resultSet, prepareStudentReportParameters(searchParamDTO));
                    reportService.downloadReportFileFromPath(generatedReportPath, response, "STUDENT REPORT.pdf");
                    break;
                case "XLS":
                    reportService.generateReportToXLSX(resultSet, response, prepareStudentReportParameters(searchParamDTO));
                    break;
        
                default:
                    System.out.println("Could'nt get report format!");
                    break;
        
            }
        }
    }
    
    private Map<String, Object > prepareStudentReportParameters(StudentSearchParamDTO searchParamDTO) throws FileNotFoundException {
        Map<String, Object> parameters = new HashMap<>();
     
        if (searchParamDTO.getProgrammeType() != 0) {
            InstitutionProgrammeType programmeType = institutionStructureServiceX.getInstitutionProgrammeTypeById(searchParamDTO.getProgrammeType());
            parameters.put("programmeType", programmeType.getDescription());
        }
        
        if (searchParamDTO.getSession() != null){
            InstitutionSession session = institutionStructureServiceX.getInstitutionSessionById(searchParamDTO.getSession().getId());
            parameters.put("session", session.getDescription());
        }
        
        if (searchParamDTO.getLevel() != 0 ){
            InstitutionLevel level = institutionStructureServiceX.getInstitutionLevelById(searchParamDTO.getLevel());
            parameters.put("level", level.getAlternative());
        }
        
        if(searchParamDTO.getEntryType() != 0){
            StudentEntryType entryType = entryTypeService.getStudentEntryTypeById(searchParamDTO.getEntryType());
            parameters.put("entryType", entryType.getDescription());
        }
        parameters.put("schoolLogo", globalSettingService.getSchoolOfficialLogo());
        parameters.put("reportFooter", globalSettingService.getReportFooter());
        parameters.put("reportHeader", globalSettingService.getReportHeader());
        parameters.put("reportHeaderAddress", globalSettingService.getSchoolAddress());
        
        return parameters;
    }

}
