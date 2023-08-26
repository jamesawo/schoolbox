
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_SystemPerformance.service;

import com.jamesportal.slms.modules.SLMS_SystemPerformance.dto.TestCategoryStructureDto;

import java.io.IOException;

public interface IAutomateInstallationService {

    void generateTestInstitutionStructure(int numberOfDataToGenerate);

    void generateTestStudentDetails(int numberOfDataToGenerate, int startRegNumberCount);

    void generateTestInstitutionCategory(int numberOfDataToGenerate);

    void generateTestUserDetails(int numberOfDataToGenerate);

    void generateTestCourses(int numberOfDataToGenerate);

    void generateApplicationPermissions();

    void generateApplicationRoles();

    void generateSuperUser();

    void attachRoleToPermission(String roleType);

    void attachRoleToSuperUser();

    void initializeGlobalSettingConfigs();

    void generateStateAndLga() throws IOException;

    void generateStudentEntryTypes();

    void deactivateTestStudentAccount(int startPoint, int endPoint);

    void generateTestCollegeInfo(TestCategoryStructureDto TestCategoryStructureDto);

    void generateDefaultPaymentMethod();


}
