
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.utility;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.CountryRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.LgaRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.StateRepository;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository.StudentEntryTypeRepository;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.repository.GlobalSettingRepository;
import com.jamesportal.slms.modules.SLMS_StudentManagement.repository.StudentRepository;
import com.jamesportal.slms.modules.SLMS_SystemPerformance.dto.TestCategoryStructureDto;
import com.jamesportal.slms.modules.SLMS_SystemPerformance.service.AutomateInstallationServiceImpl;
import com.jamesportal.slms.repository.PermissionRepository;
import com.jamesportal.slms.repository.RoleRepository;
import com.jamesportal.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitialize implements CommandLineRunner {
    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    GlobalSettingRepository globalSettingRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    LgaRepository lgaRepository;

    @Autowired
    StudentEntryTypeRepository entryTypeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    AutomateInstallationServiceImpl automateInstallationService;


    @Override
    public void run(String... args) throws Exception {

        //Create Permissions
        automateInstallationService.generateApplicationPermissions();

        //Create Role
        automateInstallationService.generateApplicationRoles();

        // Create User
        automateInstallationService.generateSuperUser();

        //Attach role to permission (ADMIN)
        automateInstallationService.attachRoleToPermission("SUPERUSER");

        //Attach role to permission (STUDENT PROFILE)
        automateInstallationService.attachRoleToPermission("STUDENT");

        //Attach role to user
        automateInstallationService.attachRoleToSuperUser();

        // Initialize Global Setting Keys
        automateInstallationService.initializeGlobalSettingConfigs();

        // Update global setting value
        automateInstallationService.generateTestCollegeInfo(new TestCategoryStructureDto());

        //Generate Country, State & LGA
        automateInstallationService.generateStateAndLga();

        //POPULATE ENTRY TYPE TABLE
        automateInstallationService.generateStudentEntryTypes();

        //Generate Default Payment Method
        automateInstallationService.generateDefaultPaymentMethod();

        // generate test institution structure
        automateInstallationService.generateTestInstitutionStructure(2);

        // generate test student data
        automateInstallationService.generateTestStudentDetails(220, 101);

        // deactivate some student data - for testing purpose
        automateInstallationService.deactivateTestStudentAccount(1, 2);

    }
}
