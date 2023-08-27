package com.jamesportal.slms.modules.SLMS_SystemPerformance.service;
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jamesportal.slms.entity.Permission;
import com.jamesportal.slms.entity.Role;
import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.dto.StateDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Country;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.CountryRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.LgaRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.StateRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.service.PaymentMethodServiceImpl;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository.StudentEntryTypeRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service.StudentEntryTypeServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.repository.GlobalSettingRepository;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.*;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import com.jamesportal.slms.modules.SLMS_StudentManagement.repository.StudentRepository;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceWorker;
import com.jamesportal.slms.modules.SLMS_SystemPerformance.dto.TestCategoryStructureDto;
import com.jamesportal.slms.repository.PermissionRepository;
import com.jamesportal.slms.repository.RoleRepository;
import com.jamesportal.slms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class AutomateInstallationServiceImpl implements IAutomateInstallationService {

    @Value(value = "${app.user.default.username}")
    private String username;

    @Value(value = "${app.user.default.password}")
    private String password;

    @Value(value = "${app.user.default.email}")
    private String email;

    private final InstitutionStructureServiceX institutionStructureServiceX;
    private final StudentEntryTypeServiceImpl studentEntryTypeService;
    private final StudentServiceWorker studentServiceWorker;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final GlobalSettingRepository globalSettingRepository;
    private final StateRepository stateRepository;
    private final LgaRepository lgaRepository;
    private final StudentEntryTypeRepository entryTypeRepository;
    private final StudentRepository studentRepository;
    private final CountryRepository countryRepository;
    private final PaymentMethodServiceImpl paymentMethodService;
    private final CategoryNameServiceImpl categoryNameService;
    private final InstitutionStructureServiceImpl institutionStructureService;
    private final InstitutionLevelServiceImpl levelService;
    private final ProgrammeCategoryServiceImpl programmeCategoryService;
    private final ProgrammeTypeServiceImpl programmeTypeService;
    private final InstitutionSessionServiceImpl institutionSessionService;

    @Override
    public void generateTestInstitutionStructure(int numberOfDataToGenerate) {
        // create institution category
        generateTestInstitutionCategory(2);
        // setup institution structure for the categories created
        setupInstitutionStructure();
        // setup institution level
        setUpInstitutionLevel();
        // setup programCategories and programTypes
        setupProgramCategoriesAndType();
        // create student entry type
        // generateStudentEntryTypes();
    }

    @Override
    public void generateTestStudentDetails(int numberOfDataToGenerate, int startRegNumberCount) {

        if (studentServiceWorker.getAllStudent().size() == 0) {

            List<InstitutionProgrammeType> institutionProgrammeTypeList = institutionStructureServiceX.getAllProgrammeType();
            List<Long> institutionProgrammeTypeListOfId = new ArrayList<>();
            institutionProgrammeTypeList.forEach(x -> {
                institutionProgrammeTypeListOfId.add(x.getId());
            });

            List<InstitutionStructure> institutionStructureList = institutionStructureServiceX.getInstitutionStructureListByCategory(institutionStructureServiceX.getInstitutionCategoryByHighestHierarchy());
            List<Long> institutionStructureListOfId = new ArrayList<>();
            institutionStructureList.forEach(x -> institutionStructureListOfId.add(x.getId()));

            List<StudentEntryType> studentEntryTypeList = studentEntryTypeService.getAllStudentEntryType();
            List<Long> studentEntryTypeListOfId = new ArrayList<>();
            studentEntryTypeList.forEach(x -> studentEntryTypeListOfId.add(x.getId()));

            List<InstitutionLevel> institutionLevelList = institutionStructureServiceX.getAllInstitutionLevel();
            List<Long> institutionLevelListOfId = new ArrayList<>();
            institutionLevelList.forEach(x -> institutionLevelListOfId.add(x.getId()));

            List<InstitutionSession> institutionSessionList = institutionStructureServiceX.getAllInstitutionSession();
            List<Long> institutionSessionListOfId = new ArrayList<>();
            institutionSessionList.forEach(x -> institutionSessionListOfId.add(x.getId()));

            for (int i = 0; i < numberOfDataToGenerate; i++) {
                Faker faker = new Faker();
                StudentDTO student = new StudentDTO();
                student.setInstitutionStructure(new InstitutionStructure(selectFromList(institutionStructureListOfId)));
                student.setProgrammeType(new InstitutionProgrammeType(selectFromList(institutionProgrammeTypeListOfId)));
                student.setEntryType(new StudentEntryType(selectFromList(studentEntryTypeListOfId)));
                student.setSession(new InstitutionSession(selectFromList(institutionSessionListOfId)));
                student.setLevel(new InstitutionLevel(selectFromList(institutionLevelListOfId)));
                student.setEmail(faker.bothify("????###@example.com"));
                student.setGender(generateTestGender());
                student.setDateOfBirth(faker.date().birthday());
                student.setSurname(faker.name().prefix().toUpperCase());
                student.setFirstName(faker.name().firstName().toUpperCase());
                student.setLastName(faker.name().lastName().toUpperCase());
                student.setRegNumber(generateTestRegNumber(i, startRegNumberCount));
                student.setStatus(true);
                studentServiceWorker.createStudent(student);
                // System.out.println(i + " New Student Generated with Reg Number: " + newStudent.getRegNumber());
            }
            System.out.printf("seed : %d numbers of student%n", numberOfDataToGenerate);
        }
    }

    @Override
    public void generateTestInstitutionCategory(int numberOfDataToGenerate) {
        if (categoryNameService.getAllCategoryNames().size() == 0) {
            InstitutionCategory faculty = new InstitutionCategory();
            faculty.setHierarchy(1);
            faculty.setDescription("FACULTY");

            InstitutionCategory department = new InstitutionCategory();
            department.setHierarchy(2);
            department.setDescription("DEPARTMENT");

            categoryNameService.createCategoryNameFromList(Arrays.asList(faculty, department));
            System.out.println("---- seed institution categories");
        }

    }

    private void setupInstitutionStructure() {
        if (institutionStructureService.getAllInstitutionStructure().size() == 0) {
            InstitutionCategory faculty = categoryNameService.getCategoryNameByDescription("FACULTY");
            InstitutionCategory department = categoryNameService.getCategoryNameByDescription("DEPARTMENT");

            InstitutionStructure facultyOfScience = new InstitutionStructure();
            facultyOfScience.setDescription("FACULTY OF SCIENCES");
            facultyOfScience.setCategory(faculty);
            facultyOfScience.setCode("FAS");
            InstitutionStructure savedFacultyOfSciences = institutionStructureService.saveInstitutionStructure(facultyOfScience);


            InstitutionStructure depCompScience = new InstitutionStructure();
            depCompScience.setDescription("DEPARTMENT OF COMPUTER SCIENCE");
            depCompScience.setCategory(department);
            depCompScience.setCode("DCOM");
            // depComScience.setParent(SavedFacultyOfSciences);
            InstitutionStructure savedDepCompScience = institutionStructureService.saveInstitutionStructure(depCompScience);
            savedDepCompScience.setParent(savedFacultyOfSciences);
            institutionStructureService.saveInstitutionStructure(savedDepCompScience);
            System.out.println("---- seed institution structure for 2 categories ----");
        }
    }

    private void setUpInstitutionLevel() {
        if (levelService.getAllInstitutionLevel().size() == 0) {
            InstitutionLevel level100 = new InstitutionLevel();
            level100.setHierarchy(1);
            level100.setDescription("100 LEVEL");
            level100.setAlternative("100");
            levelService.saveInstitutionLevel(level100);

            InstitutionLevel level200 = new InstitutionLevel();
            level200.setHierarchy(2);
            level200.setDescription("200 LEVEL");
            level200.setAlternative("200");
            levelService.saveInstitutionLevel(level200);
            System.out.println("---- seed institution levels ----");
        }
    }

    private void setupProgramCategoriesAndType() {
        InstitutionProgrammeCategory fullTime = new InstitutionProgrammeCategory();
        fullTime.setCode("FT");
        fullTime.setDescription("FULL TIME");
        fullTime.setAliasDescription("FULL-TIME");
        InstitutionProgrammeCategory fullTimeSaved = programmeCategoryService.createProgrammeCategory(fullTime);

        InstitutionProgrammeType fullTimeDay = new InstitutionProgrammeType();
        fullTimeDay.setDescription("FULL TIME DAY");
        fullTimeDay.setCode("FT-D");
        fullTimeDay.setInstitutionProgrammeCategory(fullTimeSaved);
        InstitutionProgrammeType savedFullTimeDay = programmeTypeService.createInstitutionProgrammeType(fullTimeDay);
        setupInstitutionSessions(savedFullTimeDay);


        InstitutionProgrammeType fullTimeNight = new InstitutionProgrammeType();
        fullTimeNight.setDescription("FULL TIME NIGHT");
        fullTimeNight.setCode("FT-N");
        fullTimeNight.setInstitutionProgrammeCategory(fullTimeSaved);
        InstitutionProgrammeType savedFullTimeNight = programmeTypeService.createInstitutionProgrammeType(fullTimeNight);
        setupInstitutionSessions(savedFullTimeNight);

    }

    private void setupInstitutionSessions(InstitutionProgrammeType type) {
        InstitutionSession session = new InstitutionSession();
        int year = LocalDate.now().getYear();
        session.setDescription(String.format("%d/%d", year, year + 1));
        session.setProgrammeType(type);
        institutionSessionService.createInstitutionSession(session);

    }

    @Override
    public void generateTestUserDetails(int numberOfDataToGenerate) {
    }

    @Override
    public void generateTestCourses(int numberOfDataToGenerate) {
    }

    @Override
    public void generateApplicationPermissions() {
        if (permissionRepository.count() == 0) {

            // Create System Permission
            Set<Permission> permissions = new HashSet<>();

            permissions.add(new Permission("SUPER_ADMIN", "ACCESS.SLMS"));

            permissions.add(new Permission("ACCESS_ADMIN_PANEL", "ACCESS.SLMS"));
            permissions.add(new Permission("ACCESS_SITE_FRONT_END", "ACCESS.SLMS"));

            permissions.add(new Permission("ACCESS_MANAGE_SPECIAL_EFFECTS", "SLMS.COMMON.FEATURE"));
            permissions.add(new Permission("ACCESS_VIEW_ADMIN_DASHBOARD", "SLMS.COMMON.FEATURE"));
            permissions.add(new Permission("ACCESS_VIEW_GUARDIAN_DASHBOARD", "SLMS.COMMON.FEATURE"));

            permissions.add(new Permission("ACCESS_ACCEPT_ADMISSION", "SLMS.ACCEPTANCE.MANAGEMENT.FEATURE"));

            permissions.add(new Permission("ACCESS_MANAGING_ADMISSION", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_ADMISSION_LIST", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_APPLICATION_LIST", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_CHECK_ADMISSION_RESULT", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGE_COURSE_REQUIREMENT_SETTING", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGE_COURSE_BAND", "SLMS.ADMISSION.MANAGEMENT.FEATURE"));

            permissions.add(new Permission("ACCESS_EDIT_APPLICANT", "SLMS.APPLICATION.FEATURE"));

            permissions.add(new Permission("ACCESS_STUDENT_COURSE_REGISTRATION", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_COURSE_CREATION", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_COURSE_ALLOCATION", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_VIEW_ALLOCATED_COURSE", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_COURSE_ALLOCATION_TO_LECTURER", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_COURSE_PRE_REQUISITE_SETUP", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_SUBMITTED_COURSE_REGISTRATION", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_LECTURER_APPROVED_COURSE_LIST", "SLMS.COURSE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_DELETE_REGISTERED_COURSE", "SLMS.COURSE.MANAGEMENT.FEATURE"));


            permissions.add(new Permission("ACCESS_STUDENT_FEE", "SLMS.FEE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_FEE_REPORT", "SLMS.FEE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_FEE_CATEGORY_SETUP", "SLMS.FEE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_SPECIFIC_FEE_CATEGORY_SETUP", "SLMS.FEE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_DEPENDENT_FEE", "SLMS.FEE.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_TOGGLE_PAYMENT", "SLMS.FEE.MANAGEMENT.FEATURE"));

            permissions.add(new Permission("ACCESS_MANAGING_CATEGORY_NAMES", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_INSTITUTION_STRUCTURE", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_LEVELS", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_PROGRAMME_TYPES", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_COURSE_OF_STUDY_DURATION", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_SEMESTER", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_SESSION", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));
            permissions.add(new Permission("ACCESS_LEVEL_HISTORY", "SLMS.MANAGE.INSTITUTION.STRUCTURE.FEATURE"));

            permissions.add(new Permission("ACCESS_MANAGING_STUDENTS_RESULTS", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_GRADES_AND_WEIGHTS", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGE_STATUS_OF_RESULT_MODIFICATION_REQUETS", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGE_RESULT_MODIFICATION_REQUETS", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_CHECK_RESULT", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_ACCESS_COMPOSITE_SHEET", "SLMS.RESULT.MANAGER.FEATURE"));
            permissions.add(new Permission("ACCESS_APPROVE_COMPOSITE_RESULT", "SLMS.RESULT.MANAGER.FEATURE"));

            permissions.add(new Permission("ACCESS_MANAGING_STUDENTS", "SLMS.STUDENT.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_EDIT_PROFILE", "SLMS.STUDENT.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_STUDENT_PROFILE", "SLMS.STUDENT.MANAGEMENT.FEATURE"));


            permissions.add(new Permission("ACCESS_MANAGING_STAFF_POSTS", "SLMS.STAFF.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGING_STAFF_PROFILES", "SLMS.STAFF.MANAGEMENT.FEATURE"));

            permissions.add(new Permission("ACCESS_COMPLETING_TRANSCRIPT_APPLICATION", "SLMS.TRANSCRIPT.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_VIEW_TRANSCRIPT_APPLICATION_DETAILS", "SLMS.TRANSCRIPT.MANAGEMENT.FEATURE"));
            permissions.add(new Permission("ACCESS_MANAGE_TRANSCRIPT_APPLICANTS", "SLMS.TRANSCRIPT.MANAGEMENT.FEATURE"));
            permissionRepository.saveAll(permissions);
            System.out.println("---- seed permissions ----");
        }
    }

    @Override
    public void generateApplicationRoles() {
        if (roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();
            roles.add(new Role("ROLE_SUPER_ADMIN", "SUPER ADMIN ROLE"));
            roles.add(new Role("ROLE_STUDENT", "STUDENT ROLE"));
            roleRepository.saveAll(roles);
            System.out.println("---- seed default roles ----");
        }
    }

    @Override
    public void generateSuperUser() {
        if (!isEmpty(this.username) && !isEmpty(this.email) && !isEmpty(this.password)) {
            if (userRepository.findByUsername(this.username) == null) {
                User adminUser = new User();
                adminUser.setStatus(true);
                adminUser.setPassword(this.password);
                adminUser.setEmail(this.email);
                adminUser.setUsername(this.username);
                userRepository.save(adminUser);
                System.out.println("---- seed default system user ----");
            }
        } else {
            System.out.println("---- failed: user is not provided in system properties ----");
        }
    }

    @Override
    public void attachRoleToPermission(String roleType) {

        if (roleType.equals("SUPERUSER")) {
            Set adminPermission = new HashSet();
            adminPermission.add(permissionRepository.findByName("SUPER_ADMIN"));
            Role adminRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
            adminRole.setPermissions(adminPermission);
            roleRepository.save(adminRole);
        }

        if (roleType.equals("STUDENT")) {
            Set studentPerm = new HashSet();
            studentPerm.add(permissionRepository.findByName("ACCESS_STUDENT_PROFILE"));
            Role studentRole = roleRepository.findByName("ROLE_STUDENT");
            studentRole.setPermissions(studentPerm);
            roleRepository.save(studentRole);
        }
    }

    @Override
    public void attachRoleToSuperUser() {
        Set<Role> roleList = new HashSet<>();
        Role roleSuperAdmin = roleRepository.findByName("ROLE_SUPER_ADMIN");
        if (roleSuperAdmin != null) {
            roleList.add(roleSuperAdmin);
            User user = userRepository.findByUsername(username);
            if (user != null && user.getRoles().isEmpty()) {
                user.setRoles(roleList);
                userRepository.save(user);
                System.out.println("---- role attached to user ----");
            }
        }
    }

    @Override
    public void initializeGlobalSettingConfigs() {
        if (globalSettingRepository.count() == 0) {
            List<GlobalSetting> globalSettingsList = new ArrayList<>();

            globalSettingsList.add(new GlobalSetting("SchoolOfficialLogo", "", "College Profile"));
            globalSettingsList.add(new GlobalSetting("ReceiptSignature", "", "College Profile"));
            globalSettingsList.add(new GlobalSetting("RegistrarSignature", "", "College Profile"));
            globalSettingsList.add(new GlobalSetting("AlternativeLogo", "", "College Profile"));

            globalSettingsList.add(new GlobalSetting("SchoolName", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("SchoolAddress", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("PrivateMailBag", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("SchoolWebSiteUrl", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("CollegeAddress", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("PublicMailBag", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("PhoneLines", "", "College Information"));
            globalSettingsList.add(new GlobalSetting("SchoolSlogan", "", "College Information"));


            globalSettingsList.add(new GlobalSetting("ReportHeader", "", "ReportSetup"));
            globalSettingsList.add(new GlobalSetting("ReportFooter", "", "ReportSetup"));

            globalSettingsList.add(new GlobalSetting("MerchantID", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("ReceiptSignatory", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("ZenithCommission", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("UrbanMFBServiceUrl", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("InterswitchCommission", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("InterswitchItemCode", "", "Payment Setup"));
            globalSettingsList.add(new GlobalSetting("InterswitchMerchantID", "", "Payment Setup"));

            globalSettingsList.add(new GlobalSetting("SupportEmail", "", "Support Parameter"));
            globalSettingsList.add(new GlobalSetting("EmailPassword", "", "Support Parameter"));
            globalSettingsList.add(new GlobalSetting("MailSMTPServer", "", "Support Parameter"));
            globalSettingsList.add(new GlobalSetting("MailSMTPPort", "", "Support Parameter"));

            globalSettingsList.add(new GlobalSetting("AdmissionLetterSignatory", "", "Registry Info"));
            globalSettingsList.add(new GlobalSetting("AdmissionLetterSignatoryQualification", "", "Registry Info"));
            globalSettingsList.add(new GlobalSetting("RegistryEmail", "", "Registry Info"));
            globalSettingsList.add(new GlobalSetting("RegistryRef", "", "Registry Info"));

            globalSettingsList.add(new GlobalSetting("AdmissionResultMessage", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("SchoolID", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("AllowAcceptance", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("EnableCarryOverCourses", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("AllowResultCheck", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("AllowPassportChange", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("AllowStateChange", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("ApplicationVersion", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("ShowCustomizer", "", "Application Config"));
            globalSettingsList.add(new GlobalSetting("FrontCssMasterDefault", "menu-position-side menu-side-left full-screen with-content-panel", "Application Config"));
            globalSettingsList.add(new GlobalSetting("FrontCssMasterCustomize", "", "Application Config"));

            globalSettingRepository.saveAll(globalSettingsList);
            System.out.println("---- seed global settings  ----");
        }
    }

    @Override
    public void generateStateAndLga() throws IOException {
        if (countryRepository.count() == 0) {
            Country country = countryRepository.save(new Country("Nigeria"));

            //POPULATE STATE AND LGA TABLE FROM JSON FILE
            ObjectMapper objectMapper = new ObjectMapper();
            StateDTO state = objectMapper.readValue(new File("example.json"), StateDTO.class);
            state.getStateList().forEach(s -> {
                State newState = new State();
                newState.setDescription(s.getDescription());
                newState.setCountry(country);
                State st = stateRepository.save(newState);

                s.getLocals().forEach(l -> {
                    Lga lga1 = new Lga();
                    lga1.setDescription(l.getDescription());
                    lga1.setState(new State(st.getId()));
                    lgaRepository.save(lga1);
                });
            });
            System.out.println("---- seed states and local government data  ----");
        }
    }

    @Override
    public void generateStudentEntryTypes() {
        if (entryTypeRepository.count() == 0) {
            ArrayList<StudentEntryType> entryTypes = new ArrayList<>();
            entryTypes.add(new StudentEntryType("NEW STUDENT"));
            entryTypes.add(new StudentEntryType("DIRECT ENTRY"));
            entryTypeRepository.saveAll(entryTypes);
            System.out.println("---- seed default student entry type  ----");
        }
    }

    private Long selectFromList(List<Long> objects) {
        Random rand = new Random();
        return objects.get(rand.nextInt(objects.size()));
    }

    private String generateTestGender() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("Male", "Female");
        int randomIndex = rand.nextInt(givenList.size());
        return givenList.get(randomIndex);
    }

    private String generateTestRegNumber(int index, int regLastIndex) {
        int incrementStartingPoint = regLastIndex + index;
        String regNumberFormat = "TEST/STUDENT/";
        return regNumberFormat + incrementStartingPoint;
    }

    @Override
    public void deactivateTestStudentAccount(int startPoint, int endPoint) {
        List<Long> longList = new ArrayList<>();
        for (int i = startPoint; i < endPoint; i++) {
            longList.add((long) i + 1);
        }
        List<Student> studentList = studentRepository.findAllById(longList);
        studentList.forEach(student -> {
            studentRepository.deactivateStudentById(student.getId());
        });
    }

    @Override
    public void generateTestCollegeInfo(TestCategoryStructureDto TestCategoryStructureDto) {
        if (globalSettingRepository.count() != 0) {
            globalSettingRepository.updateGlobalSetting("SchoolName", "BABCOCK UNIVERSITY");
            globalSettingRepository.updateGlobalSetting("SchoolAddress", "121103, Ilishan-Remo, Ogun State, Nigeria.");
            globalSettingRepository.updateGlobalSetting("PhoneLines", "02232300");
            globalSettingRepository.updateGlobalSetting("SchoolSlogan", "Knowledge, Truth, Service");
        }
    }

    @Override
    public void generateDefaultPaymentMethod() {
        if (paymentMethodService.getAllPaymentMethod().size() == 0) {
            List<PaymentMethod> paymentMethodList = new ArrayList<>();
            paymentMethodList.add(new PaymentMethod("INTERSWITCH", "Wallet", "-"));
            paymentMethodList.add(new PaymentMethod("UBA BANK PIN", "Pin", "-"));
            paymentMethodList.add(new PaymentMethod("ZENITH BANK PIN", "Pin", "-"));
            paymentMethodList.add(new PaymentMethod("OPAY SCAN/PAY", "Scan", "-"));
            paymentMethodList.add(new PaymentMethod("PAYSTACK", "card", "-"));
            paymentMethodList.add(new PaymentMethod("OTHER BANK PIN", "Pin", "-"));

            paymentMethodService.createBulkPaymentMethod(paymentMethodList);
            System.out.println("---- seed default payment methods  ----");
        }

    }

}
