package com.cosmos.shiro.core.vo;

import com.cosmos.shiro.common.vo.BaseVO;
import lombok.Data;

/**
 * Created by nijiahui on 2019-06-24.
 */
@Data
public class SysUserRoleVO extends BaseVO {


    private Long userId;

    private Long roleId;
}