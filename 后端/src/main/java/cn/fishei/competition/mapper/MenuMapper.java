package cn.fishei.competition.mapper;

import cn.fishei.competition.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Goods> queryHomeLists();

    List<Goods> queryPotLists();

    List<Goods> querySnackLists();

    List<Goods> queryDrinksLists();

    List<Goods> queryLikeGoods(String key);
}
