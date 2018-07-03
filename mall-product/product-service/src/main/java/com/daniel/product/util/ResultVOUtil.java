package com.daniel.product.util;

import com.daniel.product.vo.ResultVO;

/**
 * Daniel on 2018/7/3.
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
