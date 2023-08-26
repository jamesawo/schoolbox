
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentPin;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;

import java.util.Date;
import java.util.List;

public interface IPaymentPinService {
    PaymentPin getPaymentPinBySerialAndPin(String serial, String pin);

    List<PaymentPin> getAllUsedPaymentPin();

    List<PaymentPin> getPaymentPinBySlmsIdentity(SlmsIdentity slmsIdentity);

    List<PaymentPin> getPaymentPinByStudent(Student student);

    List<PaymentPin> getPaymentPinByUsedDate(Date date);

    List<PaymentPin> getPaymentPinBySpecificFee(SpecificFee fee);

    PaymentPin getPaymentPinByRef(String ref);

    List<PaymentPin> getPaymentPinsBySerial(String serial);

    List<PaymentPin> getPaymentPinByPin(String pin);

    List<PaymentPin> getPaymentPinByPaymentMethod(PaymentMethod paymentMethod);
}
