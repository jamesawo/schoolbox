
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.dto.PaginationDto;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.service.PaymentMethodServiceImpl;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.FeeCategoryDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SFeeSearchParamDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SpecificFeeDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeInstallment;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeeCategoryServiceWorker {

    @Autowired
    FeeCategoryServiceImpl feeCategoryService;

    @Autowired
    SpecificFeeServiceImpl specificFeeService;

    @Autowired
    FeeInstalmentServiceImpl feeInstalmentService;

    @Autowired
    StudentServiceWorker studentServiceWorker;

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @Autowired
    PaymentMethodServiceImpl paymentMethodService;


    //FeeCategory Management
    public List<FeeCategory> getAllFeeCategory()
    {
        return feeCategoryService.getAllFeeCategory();
    }

    public void createFeeCategory(FeeCategoryDTO feeCategoryDTO)
    {
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setDescription(feeCategoryDTO.getDescription());
        feeCategory.setType(feeCategoryDTO.getType());
        feeCategoryService.createFeeCategory(feeCategory);
    }

    public void updateFeeCategory(FeeCategoryDTO feeCategoryDTO)
    {
        FeeCategory feeCategory = feeCategoryService.getFeeCategoryById(feeCategoryDTO.getId());
        feeCategory.setDescription(feeCategoryDTO.getDescription());
        feeCategory.setType(feeCategoryDTO.getType());
        feeCategoryService.updateFeeCategory(feeCategory);
    }

    //SpecificFee Management

    // General Fee
    public void createSpecificFee(SpecificFeeDTO specificFeeDTO)
    {
        SpecificFee specificFee = setSpecific(specificFeeDTO);

        specificFee.setType("General");
        SpecificFee fee = specificFeeService.createSpecificFee(specificFee);
        if(specificFeeDTO.isAllowPartPayment()){
            specificFeeDTO.setId(fee.getId());
            createSpecificFeeInstalment(specificFeeDTO);
        }

    }

    public void createSpecificFeeInstalment(SpecificFeeDTO specificFeeDTO){
        feeInstalmentService.createFeeInstalment(specificFeeDTO);

    }

    public SpecificFee updateSpecificFee(SpecificFeeDTO specificFeeDTO){

        SpecificFee specificFee = specificFeeService.getSpecificFeeById(specificFeeDTO.getId());
        specificFee.setMerchant(specificFeeDTO.getMerchantId());
        specificFee.setItemCode(specificFeeDTO.getItemCode());

        return specificFeeService.updateSpecificFee(specificFee);
    }

    public void deactivateSpecificFee(long id){
        specificFeeService.deactivateSpecificFee(new SpecificFee(id));
    }

    public  void activateSpecificFee(long id){
        specificFeeService.deactivateSpecificFee(new SpecificFee(id));
    }

    public SpecificFee getSpecificFeeById(long id){
        return specificFeeService.getSpecificFeeById(id);
    }

    public List<SpecificFee> getAllSpecificFee(){
        return specificFeeService.getAllSpecificFee();
    }

    public List<SpecificFee> getSpecificFeeByProgrammeType(InstitutionProgrammeType institutionProgrammeType){
        return specificFeeService.getSpecificFeeByProgrammeType(institutionProgrammeType);
    }

    public List<SpecificFee> getAllSpecificFeeByInstitutionStructure(InstitutionStructure institutionStructure){
        return specificFeeService.getAllSpecificFeeByInstitutionStructure(institutionStructure);
    }

    //Global Fee Setup
    public void createGlobalFee(SpecificFeeDTO specificFeeDTO)
    {
        SpecificFee specificFee = setSpecific(specificFeeDTO);
        specificFee.setType("Global");
        SpecificFee fee = specificFeeService.createSpecificFee(specificFee);
        if(specificFeeDTO.isAllowPartPayment()){
            specificFeeDTO.setId(fee.getId());
            createSpecificFeeInstalment(specificFeeDTO);
        }
    }

    private SpecificFee setSpecific(SpecificFeeDTO specificFeeDTO) {
        SpecificFee specificFee = new SpecificFee();
        specificFee.setProgrammeType(specificFeeDTO.getProgrammeType());
        specificFee.setSession(specificFeeDTO.getSession());
        specificFee.setLevel(specificFeeDTO.getLevel());
        if(specificFeeDTO.getInstitutionStructureValue() != 0){
            specificFee.setInstitutionStructure(specificFeeDTO.getInstitutionStructure());
        }
        specificFee.setSemester(specificFeeDTO.getSemester());
        specificFee.setFeeCategory(specificFeeDTO.getFeeCategory());
        specificFee.setDependentFee(specificFeeDTO.getDependentFee());
        specificFee.setAmount(specificFeeDTO.getAmount());
        specificFee.setItemCode(specificFeeDTO.getItemCode());
        specificFee.setMerchant(specificFeeDTO.getMerchantId());
        specificFee.setAllowPartPayment(specificFeeDTO.isAllowPartPayment());
        return specificFee;
    }

    public PaginationDto searchSpecificFee(SFeeSearchParamDto sFeeSearchParamDto){
        PaginationDto paginationDto = new PaginationDto();

        List<Long> longList = studentServiceWorker.setInstStructureListFromSearchFilter( sFeeSearchParamDto.getInstitutionStructure(), sFeeSearchParamDto.getInstitutionStructureValue() );
        sFeeSearchParamDto.setInstitutionStructureIds(longList);

        Page<SpecificFee> specificFees =  specificFeeService.specificFeeSearchCriteria(sFeeSearchParamDto);
        paginationDto.setHasNext(specificFees.hasNext());
        paginationDto.setHasPrevious(specificFees.hasPrevious());
        paginationDto.setPageNumber(specificFees.getNumber());
        paginationDto.setTotalPages(specificFees.getTotalPages());
        paginationDto.setPageSize(specificFees.getSize());
        paginationDto.setTotalResultSize(specificFees.getTotalElements());

        List<SpecificFeeDTO> specificFeeDtoList = new ArrayList<>();

        for (SpecificFee i : specificFees) {
            SpecificFeeDTO feeDTO = new SpecificFeeDTO();
            if( i.getProgrammeType() != null )
                feeDTO.setProgrammeTypeDescription(i.getProgrammeType().getDescription());
            if (i.getSession() != null)
                feeDTO.setSessionDescription(i.getSession().getDescription());
            if (i.getLevel() != null)
                feeDTO.setLevelDescription(i.getLevel().getAlternative());
            if(i.getInstitutionStructure() != null )
                feeDTO.setInstitutionStructureDescription(i.getInstitutionStructure().getDescription());
            if (i.getFeeCategory() != null)
                feeDTO.setFeeCategoryDescription(i.getFeeCategory().getDescription());
            if(i.getDependentFee() != null)
                feeDTO.setDependentFee(i.getDependentFee());
            if(i.getSemester() != null)
                feeDTO.setSemesterDescription(i.getSemester().getDescription());

            feeDTO.setGlobalFeeBool(i.getType().equals("Global"));

            feeDTO.setId(i.getId());
            feeDTO.setAmount(i.getAmount());
            feeDTO.setStatus(i.isStatus());
            feeDTO.setItemCode(i.getItemCode());
            feeDTO.setDateModified(i.getCreatedAt().toLocalDate().toString());
            feeDTO.setFeeType(i.getType());
            specificFeeDtoList.add(feeDTO);
        }
        paginationDto.setSpecificFeeDTOS(specificFeeDtoList);
        return paginationDto;
    }

    //service method
    public List<SpecificFeeDTO> getStudentApplicableFee(SFeeSearchParamDto sFeeSearchParamDto){

        Student student = studentServiceWorker.getStudentDetailsByRegNumber(sFeeSearchParamDto.getStudentRegNumber());
        List<SpecificFee> specificFeeList = getAllSpecificFee();
        return filterStudentApplicableFee(student, specificFeeList, sFeeSearchParamDto.getSession() );

    }

    //private get specific allocated to student
    private List<SpecificFeeDTO> filterStudentApplicableFee(Student student, List<SpecificFee> specificFeeList, long sessionId){
        List<SpecificFeeDTO> specificFeeDTOList =  new ArrayList<>();
        InstitutionProgrammeType studentProgrammeType = student.getProgrammeType();
        InstitutionStructure studentInstitutionStructure = student.getInstitutionStructure();
        InstitutionLevel studentLevel = student.getLevel();


        for (SpecificFee specificFee : specificFeeList) {

            Optional<InstitutionStructure> sfInstStructure = Optional.ofNullable(specificFee.getInstitutionStructure());

            Optional<InstitutionLevel> sfLevel = Optional.ofNullable(specificFee.getLevel());
            Optional<InstitutionSemester> sfSemester = Optional.ofNullable(specificFee.getSemester());

            if (specificFee.isStatus()  && specificFee.getSession().getId() == sessionId){
                SpecificFeeDTO specificFeeDTO = new SpecificFeeDTO();

                if (specificFee.getType().equals("Global")){
                    if(specificFee.getProgrammeType().getId() == studentProgrammeType.getId()  ){

                        specificFeeDTO.setAmount(specificFee.getAmount());
                        specificFeeDTO.setFeeCategoryDescription(specificFee.getFeeCategory().getDescription());
                        specificFeeDTO.setFeeCategoryType(specificFee.getFeeCategory().getType());
                        specificFeeDTO.setAllowPartPayment(specificFee.isAllowPartPayment());
                        specificFeeDTO.setJsAllowPartPayment(specificFee.isAllowPartPayment()?1:0);

                        if(specificFee.isAllowPartPayment()) {
                            FeeInstallment feeInstalment = getFeeInstalmentBySpecificFee(specificFee);
                            specificFeeDTO.setFirstInstalment(feeInstalment.getFirstInstalment());
                            specificFeeDTO.setSecondInstalment(feeInstalment.getSecondInstalment());
                        }
                        specificFeeDTOList.add(specificFeeDTO);
                    }

                }
                else if(specificFee.getType().equals("General")){

                    if(sfLevel.isPresent() && sfLevel.get().getId() == studentLevel.getId() || ObjectUtils.isEmpty(sfLevel) ){

                        //check if institution structure is null (means it is allocated to all faculty or hierarchy 1)
                        if( specificFee.getInstitutionStructure() == null ){
                            //if true it means it is allocated to all institution structure
                            specificFeeDTO.setAmount(specificFee.getAmount());
                            specificFeeDTO.setFeeCategoryDescription(specificFee.getFeeCategory().getDescription());
                            specificFeeDTO.setFeeCategoryType(specificFee.getFeeCategory().getType());
                            specificFeeDTO.setJsAllowPartPayment(specificFee.isAllowPartPayment()?1:0);
                            specificFeeDTO.setAllowPartPayment(specificFee.isAllowPartPayment());
                            if(specificFee.isAllowPartPayment()) {
                                FeeInstallment feeInstalment = getFeeInstalmentBySpecificFee(specificFee);
                                specificFeeDTO.setFirstInstalment(feeInstalment.getFirstInstalment());
                                specificFeeDTO.setSecondInstalment(feeInstalment.getSecondInstalment());
                            }
                            specificFeeDTOList.add(specificFeeDTO);

                        }else if( specificFee.getInstitutionStructure() != null  ){
                             //check the student institution structure against the specific fee institution structure
                            // otherwise check student institution structure parent if it match specific inst structure
                            if(checkStudentInstitutionStructureParent(student.getInstitutionStructure(), specificFee.getInstitutionStructure())){
                                specificFeeDTO.setAmount(specificFee.getAmount());
                                specificFeeDTO.setFeeCategoryDescription(specificFee.getFeeCategory().getDescription());
                                specificFeeDTO.setFeeCategoryType(specificFee.getFeeCategory().getType());
                                specificFeeDTO.setJsAllowPartPayment(specificFee.isAllowPartPayment()?1:0);
                                specificFeeDTO.setAllowPartPayment(specificFee.isAllowPartPayment());
                                if(specificFee.isAllowPartPayment()) {
                                    FeeInstallment feeInstalment = getFeeInstalmentBySpecificFee(specificFee);
                                    specificFeeDTO.setFirstInstalment(feeInstalment.getFirstInstalment());
                                    specificFeeDTO.setSecondInstalment(feeInstalment.getSecondInstalment());
                                }
                                specificFeeDTOList.add(specificFeeDTO);
                            }
                        }
                         //todo:: filter student fee by current semester

                    }

                }

            }
        }

        return specificFeeDTOList;

    }

    //fee instalment method
    public FeeInstallment getFeeInstalmentBySpecificFee(SpecificFee specificFee){
        return feeInstalmentService.getFeeInstalmentBySpecificFee(specificFee);
    }

    //payment method
    public List<PaymentMethod> getAllPaymentMethod(){
        return paymentMethodService.getAllPaymentMethod();
    }

    private boolean checkStudentInstitutionStructureParent(  InstitutionStructure studentInstitutionStructure, InstitutionStructure sfInstitutionStructure  ){
        return true;
    }



}
