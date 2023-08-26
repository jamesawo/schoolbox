
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.service.SlmsIdentityServiceWorker;
import com.jamesportal.slms.modules.SLMS_UserManagement.service.UserServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    UserServiceWorker userServiceWorker;

    @Autowired
    SlmsIdentityServiceWorker slmsIdentityServiceWorker;

    private Authentication authentication;


    @Override
    public long getCurrentAuthUserId(String username) {
        User user = userServiceWorker.getUserByUsername( username );
        return user.getId();
    }

    @Override
    public SlmsIdentity getCurrentAuthUserIdentityRecord(String username) {

        long userId = getCurrentAuthUserId(username);
        return slmsIdentityServiceWorker.getSlmsIdentityByUser( new User(userId));
    }
}
