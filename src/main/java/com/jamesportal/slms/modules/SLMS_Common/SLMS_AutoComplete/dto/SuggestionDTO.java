
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString @NoArgsConstructor
public class SuggestionDTO {

    long value;

    String text;

    String studentSurname;

    String studentFirstName;

    String studentLastName;


    public SuggestionDTO(long value, String text, String studentSurname, String studentFirstName, String studentLastName) {
        this.value = value;
        this.text = text;
        this.studentSurname = studentSurname;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public SuggestionDTO(long value, String text) {
        this.value = value;
        this.text = text;
    }
}
