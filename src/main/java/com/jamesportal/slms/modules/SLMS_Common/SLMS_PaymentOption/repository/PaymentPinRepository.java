
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentPin;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentPinRepository extends JpaRepository<PaymentPin, Long> {

    PaymentPin findBySerialAndPin(String serial, String pin);

    List<PaymentPin> findByPin(String pin);

    List<PaymentPin> findBySlmsIdentity(SlmsIdentity slmsIdentity);

    List<PaymentPin> findByStudent(Student student);

    List<PaymentPin> findByUsedDate(Date date);

    List<PaymentPin> findBySpecificFee(SpecificFee specificFee);

    PaymentPin findByRef(String ref);

    List<PaymentPin> findBySerial(String serial);

    List<PaymentPin> findByPaymentMethod(PaymentMethod paymentMethod);
}
