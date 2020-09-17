package com.neuedu.service;

import com.neuedu.pojo.Register;

import java.io.IOException;
import java.util.List;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/28  10:20 28
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */

public interface IDoctorService {

    List<Register> registList(String type,String token) throws IOException;

}
