package com.daniel.order.util;


import com.daniel.order.vo.ResultVO;

/**
 * Daniel on 2018/7/10.
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
