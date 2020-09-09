package com.twu.service;

import com.twu.dao.HotTopicDao;
import com.twu.pojo.HotTopic;
import com.twu.pojo.User;

import java.util.*;

public class HotTopicService {

    HotTopicDao hotTopicDao = new HotTopicDao();

    List<HotTopic> currentList = new ArrayList<>();

    //查看热搜榜
    int sort = 1;  //排序序号
    public void listHotTopic() {
        List<HotTopic> isPurchasedList = new ArrayList<>();
        List<HotTopic> normalList = new ArrayList<>();
        currentList.forEach(i->{
            if(i.getPurchased()&& !i.getDeleted()){
                isPurchasedList.add(i);
            } else {
                normalList.add(i);
            }
        });
        normalList.sort(Comparator.comparingInt(HotTopic::getVotes));
        Collections.reverse(normalList);
        List<HotTopic> listList = new ArrayList<>(normalList);
        isPurchasedList.forEach(item->{
            listList.add(item.getRank()-1,item);
        });
        listList.forEach(i->{
            System.out.println(sort+"  "+i.getHotTopicName()+"  "+i.getVotes());
            sort++;
        });
        System.out.println(currentList.toString());
        sort = 1;   //循环结束进行下一次循环，序号就要重置
    }

    //增加热搜
    //在增加之前，要获取现在已经存在的list，然后判断增加的是否已经存在了
    public Boolean addHotTopic (String name ,Boolean isSuperHot){
        boolean isExit = hotTopicDao.selectByName(currentList, name);
        if(isExit){
            //System.out.println(currentList.toString());
            return true;
        } else {
            currentList.add(new HotTopic(name,isSuperHot));
            //System.out.println(currentList.toString());
            return false;
        }
    }

    //给热搜投票
    //必须保证热搜的名称正确
    boolean isExit = false;
    public Boolean vote(User loginUser,String topicName,int votes){
        currentList.forEach(i->{
            if (i.getHotTopicName().equals(topicName)){
                isExit = true;
                if(i.getSuperHot()){
                    i.setVotes(i.getVotes()+votes*2);
                }else {
                    i.setVotes(i.getVotes()+votes);
                }
            }
        });
        if(isExit){
            loginUser.setRemainVotes(loginUser.getRemainVotes()-votes);
            return true;
        }else {
            return false;
        }
    }

    //根据位置查热搜
    HotTopic thisHotTopic =new HotTopic();
    public HotTopic getThisHotTopic(int rank){
        currentList.forEach(i->{
            if(i.getRank()==rank){
                thisHotTopic = i;
            }
        });
        return thisHotTopic;
    }

    //买热搜
    //判断该位置是否有事件
    boolean isHad = false;
    HotTopic rankTopic = new HotTopic();
    public Integer buyHotTopic(String topicName,int rank,int price){
        //判断该位置是否有事件了
        currentList.forEach(i->{
            if(i.getRank()==rank){
                isHad = true;
                rankTopic = i;
            }
        });
        //如果已经有事件了，判断价格
        if(isHad){
            if(rankTopic.getPrice() >= price){
                return 0;
            } else {
                currentList.forEach(i->{
                    if(i.getRank()==rank){
                        i.setRank(0);
                        i.setDeleted(true);
                    }
                    if(i.getHotTopicName().equals(topicName)){
                        i.setRank(rank);
                        i.setPrice(price);
                        i.setPurchased(true);
                    }
                });
                return 1;
            }
        } else {
            currentList.forEach(i->{
                if(i.getHotTopicName().equals(topicName)){
                    i.setRank(rank);
                    i.setPrice(price);
                    i.setPurchased(true);
                }
            });
            return 1;
        }
    }
}
