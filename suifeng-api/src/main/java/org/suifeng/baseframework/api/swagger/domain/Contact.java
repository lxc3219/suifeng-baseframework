package org.suifeng.baseframework.api.swagger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contact {

    /**
     * 联系人
     */
    private String name = "";

    /**
     * 联系人url
     */
    private String url = "";

    /**
     * 联系人email
     */
    private String email = "";

}
