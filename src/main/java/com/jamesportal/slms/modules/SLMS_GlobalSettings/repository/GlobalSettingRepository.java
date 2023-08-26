
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.repository;

import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Long> {
    GlobalSetting findByKey(String key);

    @Transactional
    @Modifying
    @Query("update GlobalSetting g set g.value =:value where g.key =:key")
    int updateGlobalSetting(@Param("value") String value, @Param("key") String key);
}
