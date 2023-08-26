
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
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.repository.PaymentPinRepository;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentPinServiceImpl implements IPaymentPinService {
    @Autowired
    PaymentPinRepository paymentPinRepository;

    @Override
    public PaymentPin getPaymentPinBySerialAndPin(String serial, String pin) {
        return paymentPinRepository.findBySerialAndPin(serial,pin);
    }

    @Override
    public List<PaymentPin> getAllUsedPaymentPin() {
        return paymentPinRepository.findAll();
    }

    @Override
    public List<PaymentPin> getPaymentPinBySlmsIdentity(SlmsIdentity slmsIdentity) {
        return paymentPinRepository.findBySlmsIdentity(slmsIdentity);
    }

    @Override
    public List<PaymentPin> getPaymentPinByStudent(Student student) {
        return paymentPinRepository.findByStudent(student);
    }

    @Override
    public List<PaymentPin> getPaymentPinByUsedDate(Date date) {
        return paymentPinRepository.findByUsedDate(date);
    }

    @Override
    public List<PaymentPin> getPaymentPinBySpecificFee(SpecificFee fee) {
        return paymentPinRepository.findBySpecificFee(fee);
    }

    @Override
    public PaymentPin getPaymentPinByRef(String ref) {
        return paymentPinRepository.findByRef(ref);
    }

    @Override
    public List<PaymentPin> getPaymentPinsBySerial(String serial) {
        return paymentPinRepository.findBySerial(serial);
    }

    @Override
    public List<PaymentPin> getPaymentPinByPin(String pin) {
        return paymentPinRepository.findByPin(pin);
    }

    @Override
    public List<PaymentPin> getPaymentPinByPaymentMethod(PaymentMethod paymentMethod) {
        return paymentPinRepository.findByPaymentMethod(paymentMethod);
    }
}
