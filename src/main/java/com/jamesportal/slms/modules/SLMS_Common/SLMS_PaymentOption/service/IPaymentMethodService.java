
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethod getPaymentMethodById(long id);

    List<PaymentMethod> getAllPaymentMethod();

    List<PaymentMethod> getAllPaymentMethodByType(String type);

    PaymentMethod getPaymentMethodByDescription(String description);

    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

    List<PaymentMethod> createBulkPaymentMethod(List<PaymentMethod> paymentMethods);



}
