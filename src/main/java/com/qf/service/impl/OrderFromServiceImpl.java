package com.qf.service.impl;

import com.qf.dao.OrderFromDao;
import com.qf.dto.OrderFromDto;
import com.qf.dto.OrderSubmissionDto;
import com.qf.service.OrderFromService;
import com.qf.vo.R;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: OrderFromServiceImpl
 * @Author: 王赛
 * @Date: 2020/7/18
 * @Time: 20:10
 */
@Service
public class OrderFromServiceImpl implements OrderFromService {

    @Autowired
    private OrderFromDao dao;

    // 订单个人查询
    @Override
    public R findAll(Integer user_id) {
        if (user_id != null) {

                List<OrderFromDto> list = dao.findOrderWhereFlag(user_id);
                System.out.println(list);
                    return R.ok(list);
        }
        return R.error("亲，请登录！");
    }

    //提交个人订单详细信息
    @Override
    public R findOrder(Integer car_id) {

        List<OrderSubmissionDto> order = dao.findOrder(car_id);
        return R.ok(order);
        
    }


    //确认订单详细信息
    @Override
    public R insertOrderFrom(OrderFromDto dto) {
        int i = dao.insertOrderFrom(dto);
        return R.ok(i);
    }

}