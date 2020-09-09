package com.twu.controller;


import com.twu.pojo.HotTopic;
import com.twu.pojo.User;
import com.twu.service.HotTopicService;
import com.twu.service.UserService;

import java.util.Scanner;

public class MainController {

    UserService userService = new UserService();

    HotTopicService hotTopicService = new HotTopicService();

    User loginUser = null;

    //键入
    Scanner sc = new Scanner(System.in);

    //运行方法
    public void run() {
        while (true) {
            System.out.println("请输入用户名：");
            String name = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();
            loginUser = userService.loginCheck(name, password);
            if (loginUser == null) {
                System.out.println("用户名或密码有误，请重新登陆");
                continue;
            }
            switch (loginUser.getUserType()) {
                case 1:
                    forAdmin();
                    break;
                case 2:
                    forNormal();
                    break;
            }
        }
    }

    //管理员
    private void forAdmin() {
        while (true) {
            System.out.println("请选择要进行的操作：\n 1 查看热搜排行榜\n 2 添加热搜\n 3 添加超级热搜\n 4 退出登录");
            String op = sc.next();
            switch (op) {
                case "1":
                    hotTopicService.listHotTopic();
                    break;
                case "2":
                    addHotTopic(false);
                    break;
                case "3":
                    addHotTopic(true);
                    break;
            }
            if (op.equals("4")) {
                System.out.println("用户退出！");
                loginUser = null;
                break;
            }
        }
    }

    private void forNormal() {
        while (true) {
            System.out.println("请选择要进行的操作：\n 1 查看热搜排行榜\n 2 给热搜事件投票\n 3 购买热搜\n 4 添加热搜\n 5 退出登录");
            String op = sc.next();
            switch (op) {
                case "1":
                    hotTopicService.listHotTopic();
                    break;
                case "2":
                    vote();
                    break;
                case "3":
                    buyHotTopic();
                    break;
                case "4":
                    addHotTopic(false);
                    break;
            }
            if (op.equals("5")) {
                System.out.println("用户退出！");
                loginUser = null;
                break;
            }
        }
    }

    //添加热搜，传入是否是超级热搜
    public void addHotTopic(boolean isSuperHot) {
        System.out.println("请输入要添加的热搜名称：");
        String name = sc.next();
        boolean isExit = hotTopicService.addHotTopic(name, isSuperHot);
        if (isExit) {
            System.out.println("该条热搜已经存在咯，请看排行榜！");
        } else {
            System.out.println("添加成功啦，快去投票吧！");
        }
    }

    //投票
    public void vote() {
        while (true) {
            System.out.println("请输入要投票的热搜名称：");
            String topicName = sc.next();
            System.out.println("请输入票数(剩余"+loginUser.getRemainVotes()+"票)：");
            int votes = sc.nextInt();
            if (votes < 0) {
                System.out.println("票数只能是正整数");
                continue;
            }
            if (votes > loginUser.getRemainVotes()) {
                System.out.println("你的剩余票数不够了，只剩" + loginUser.getRemainVotes() + "票了");
                continue;
            }
            boolean isSucceeded = hotTopicService.vote(loginUser, topicName, votes);
            if (isSucceeded) {
                System.out.println("投票成功！ 剩余" + loginUser.getRemainVotes() + "票");
                break;
            } else {
                System.out.println("热搜不存在，请输入正确的热搜名称！");
            }
        }
    }

    //买热搜
    public void buyHotTopic() {
        int rank = 0;
        int price = 0;
        while (true) {
            System.out.println("请输入要买的热搜名称：");
            String topicName = sc.next();
            try {
                System.out.println("请输入要买的位置：");
                rank = sc.nextInt();
                System.out.println("请输入购买价格：");
                price = sc.nextInt();
                if (rank < 0 || price < 0) {
                    System.out.println("不能是负数哟");
                }
            } catch (Exception e) {
                System.out.println("请输入正整数哟");
            }
            int isSucceeded = hotTopicService.buyHotTopic(topicName,rank,price);

            if(isSucceeded == 0){
                HotTopic thisRank = hotTopicService.getThisHotTopic(rank);
                System.out.println("你的购买金额不够！当前位置的价格是"+thisRank.getPrice()+"元");
                continue;
            }
            if(isSucceeded == 1){
                System.out.println("购买成功！");
                break;
            }
        }
    }

}
