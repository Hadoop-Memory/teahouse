package com.qf.dao;

import com.qf.dto.ShoppingCarDto;
import com.qf.pojo.ShoppingCar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingCarDao {

    //根据用户id查询其购物车中的商品信息
    @Select("SELECT shoppingcar.car_id, shoppingcar.car_goods_num, goods.goods_name, goods_type.goods_type_name, goods.goods_id, goods_img.img_main, goods.goods_market_price, goods.goods_shop_price, shoppingcar.user_id\n" +
            "FROM shoppingcar\n" +
            "LEFT JOIN goods\n" +
            "on goods.goods_id = shoppingcar.goods_id\n" +
            "LEFT JOIN goods_type\n" +
            "on goods_type.goods_type_id = goods.goods_type_id\n" +
            "LEFT JOIN goods_img\n" +
            "on goods_img.img_id = goods.goods_img\n" +
            "WHERE shoppingcar.user_id = #{userId}\n")
    List<ShoppingCarDto> selectShoppingCarByUserId(Integer userId);

    //删除用户购物车中的单个商品
    @Delete("DELETE FROM shoppingcar\n" +
            "WHERE shoppingcar.car_id = #{carId}")
    int deleteGoodsInShoppingCarById(Integer carId);

    //将某商品添加到购物车
    @Insert("INSERT INTO shoppingcar \n" +
            "(shoppingcar.user_id, shoppingcar.goods_id, shoppingcar.car_goods_num) \n" +
            "VALUES (#{user_id}, #{goods_id}, #{car_goods_num})")
    @Options(useGeneratedKeys = true, keyProperty = "car_id", keyColumn = "car_id")
    int insertGoodsInShoppingCarByGoodsId(ShoppingCar shoppingCar);

    //改变购物车中指定商品的数量
    @Update("UPDATE shoppingcar set car_goods_num = #{goodsNum} WHERE car_id = #{carId}")
    int updateGoodsNumInShoppingByCarId(Integer carId, Integer goodsNum);

    //查询指定购物车中的商品数量
    @Select("SELECT shoppingcar.car_goods_num FROM shoppingcar WHERE shoppingcar.car_id = #{carId}")
    int queryGoodsNumInShoppingCarByCarId(Integer carId);

    //批量删除
    int deleteAllGoodsInShoppingCarById(Integer userId, Integer[] carIds);

    //查询购物车商品总数
    @Select("SELECT SUM(car_goods_num) \n" +
            "FROM shoppingcar\n" +
            "WHERE shoppingcar.user_id = #{userId}\n")
    int queryGoodsNumInShoppingCar(Integer userId);

    //查询    购物车 选中  商品  总商城价
    List<ShoppingCarDto> queryGoodsShopPriceSumByCarId(Integer[] carIds);

    //查询    购物车 选中  商品  总市场价
    List<ShoppingCarDto> queryGoodsMarketPriceSumByCarId(Integer[] carIds);

    //根据用户id查看购物车，测试令牌用
    @Select("SELECT shoppingcar.car_id, shoppingcar.car_goods_num, goods.goods_name, goods_type.goods_type_name, goods.goods_id, goods_img.img_main, goods.goods_market_price, goods.goods_shop_price, shoppingcar.user_id\n" +
            "FROM shoppingcar\n" +
            "LEFT JOIN goods\n" +
            "on goods.goods_id = shoppingcar.goods_id\n" +
            "LEFT JOIN goods_type\n" +
            "on goods_type.goods_type_id = goods.goods_type_id\n" +
            "LEFT JOIN goods_img\n" +
            "on goods_img.img_id = goods.goods_img\n" +
            "WHERE shoppingcar.user_id = #{userId}\n")
    List<ShoppingCarDto> testToken(Integer userId);
}
