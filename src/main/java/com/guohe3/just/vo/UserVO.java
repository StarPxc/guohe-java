package com.guohe3.just.vo;

import com.guohe3.just.DO.User;
import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Data
public class UserVO<T> {
    private User user;
    private T detailInfo;

}
