package cn.fishei.competition.service.impl;

import cn.fishei.competition.bean.Goods;
import cn.fishei.competition.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl {

    @Resource
    private MenuMapper menuMapper;

    public List<Goods> getHomeLists(){
        return menuMapper.queryHomeLists();
    }

    public List<Goods> getPotLists(){
        return menuMapper.queryPotLists();
    }

    public List<Goods> getSnackLists(){
        return menuMapper.querySnackLists();
    }

    public List<Goods> getDrinksLists(){
        return menuMapper.queryDrinksLists();
    }

    public List<Goods> queryLikeGoods(String key){
        return menuMapper.queryLikeGoods(key);
    }

}
