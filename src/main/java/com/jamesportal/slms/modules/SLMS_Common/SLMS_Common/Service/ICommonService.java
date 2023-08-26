
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;

public interface ICommonService {

    long getCurrentAuthUserId(String username);

    SlmsIdentity getCurrentAuthUserIdentityRecord(String username);

}
