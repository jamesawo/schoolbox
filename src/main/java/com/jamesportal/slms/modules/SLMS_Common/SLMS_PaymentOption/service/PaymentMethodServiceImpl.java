
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod getPaymentMethodById(long id) {
        return paymentMethodRepository.getOne(id);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethodByType(String type) {
        return paymentMethodRepository.findByType(type);
    }

    @Override
    public PaymentMethod getPaymentMethodByDescription(String description) {
        return paymentMethodRepository.findByDescription(description);
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> createBulkPaymentMethod(List<PaymentMethod> paymentMethods) {
        return paymentMethodRepository.saveAll(paymentMethods);
    }
}
