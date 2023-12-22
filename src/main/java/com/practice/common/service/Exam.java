package com.practice.common.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @description: desc
 * @author: Francis
 * @date: 2023/9/3 9:46
 */
public class Exam {


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//
//
//
//        while (in.hasNext()) {
//
//            String services = in.nextLine();
//            String[] serviceStr = services.split(",");
//            String[] baseServices = new String[serviceStr.length];
//            String[] dependedServices = new String[serviceStr.length];
//            LinkedHashSet<String> allService =new LinkedHashSet<>();
//            for(int i =0 ; i< serviceStr.length; i++){
//                String[] serArr = serviceStr[i].split("-");
//                baseServices[i] = serArr[0];
//                dependedServices[i] = serArr[1];
//                allService.add(serArr[0]);
//                allService.add(serArr[1]);
//            }
//            String errServices = in.nextLine();
//            String[] errSerArr = errServices.split(",");
//            for(int i = 0; i < errSerArr.length; i++){
//                findAndRemoveErrSer(errSerArr[i],allService,baseServices,dependedServices);
//            }
//            StringBuilder sb = new StringBuilder();
//            for (String s : allService) {
//                sb.append(s).append(",");
//            }
//            if(sb.length() > 0){
//                sb.deleteCharAt(sb.length() -1);
//            }
//            if(sb.length() == 0){
//                sb.append(",");
//            }
//            System.out.println(sb);
//            break;
//        }
        int[] nums = new int[]{2,3,1,1,4};
        int destination = nums[nums.length -1];
//        System.out.println(canToDestination(nums,4,0,0));

        System.out.println(dp(10));

    }

    private static int dp(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <=n;i++){
           dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


    public static boolean canToDestination(int[] nums, int destination,int current,int startIndex){
        if(current == destination){
            return true;
        }
        for(int i =startIndex; i< nums.length-1; i++  ){
            for(int step = 1; step<= nums[i]; step++){
                if(step == 0){
                    continue;
                }
                current += step;
                if(canToDestination(nums,nums[nums.length -1],current,current)){
                    return true;
                };
                current -= step;

            }
        }


        return false;
    }

    public  static  void toDestination(int n ){
        int max =1;

        for(int i = 1; i < n; i++){
            int temp2 = dp(i) * dp(n-i);
            int temp3 = i * (n-i);
            int tempMax = Math.max(temp2,temp3);
            if(tempMax > max){
                max = tempMax;
            }
        }
        max = Math.max(dp(n),max);
        System.out.println(max);
    }

    private static int dp2(int n) {
        int[] dp = new int[n+1];
        int max = 0;
        for (int i = 2; i <=n;i++){
            for(int j =1; j < i; j++){
                max = Math.max(max,Math.max(j*(i-j),j*dp[i-j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    /**
     * 查找到问题服务及关联服务并删除
     * @param errService
     * @param allService
     * @param baseServices
     * @param dependedServices
     */
    private static void findAndRemoveErrSer(String errService, LinkedHashSet<String> allService, String[] baseServices, String[] dependedServices) {
        allService.remove(errService);
        for(int i =0; i < dependedServices.length; i++){
            if(dependedServices[i] == null){
                continue;
            }
            if(errService.equals(dependedServices[i])){
                allService.remove(dependedServices[i]);
                allService.remove(baseServices[i]);
                findAndRemoveErrSer(baseServices[i],allService,baseServices,dependedServices);
                dependedServices[i] = null;
            }
        }
    }


    private static void third(Scanner in) {
        String services = in.nextLine();
        String[] serviceStr = services.split(",");
        String[] baseServices = new String[serviceStr.length];
        String[] dependedServices = new String[serviceStr.length];
        LinkedHashSet<String> allService =new LinkedHashSet<>();
        for(int i =0 ; i< serviceStr.length; i++){
            String[] serArr = serviceStr[i].split("-");
            baseServices[i] = serArr[0];
            dependedServices[i] = serArr[1];
            allService.add(serArr[0]);
            allService.add(serArr[1]);
        }
        String errServices = in.nextLine();
        String[] errSerArr = errServices.split(",");
        for(int i = 0; i < errSerArr.length; i++){
            findAndRemoveErrSer(errSerArr[i],allService,baseServices,dependedServices);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : allService) {
            sb.append(s).append(",");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() -1);
        }
        if(sb.length() == 0){
            sb.append(",");
        }
        System.out.println(sb);
    }

//    /**
//     * 查找到问题服务及关联服务并删除
//     * @param errService
//     * @param allService
//     * @param baseServices
//     * @param dependedServices
//     */
//    private static void findAndRemoveErrSer(String errService, LinkedHashSet<String> allService, String[] baseServices, String[] dependedServices) {
//        allService.remove(errService);
//        for(int i =0; i < dependedServices.length; i++){
//            if(errService.equals(dependedServices[i])){
//                allService.remove(dependedServices[i]);
//                allService.remove(baseServices[i]);
//                findAndRemoveErrSer(baseServices[i],allService,baseServices,dependedServices);
//            }
//        }
//
//
//    }




    private static void second(List<Integer> list, int count) {
        int[] allPersons = new int[list.size()];
        int[] numFlags = new int[list.size()];
        int index = 0;
        int totalFlag = 0;
        int currentNum = 1;
        while (true){
            allPersons[index] = currentNum;
            if(currentNum % 7 == 0 || String.valueOf(currentNum).contains("7")){
                numFlags[index] ++;
                totalFlag ++;
                if(totalFlag == count){
                    break;
                }
            }
            currentNum++;
            index++;
            if(index == list.size()){
                index = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int numFlag : numFlags) {
            sb.append(numFlag).append(" ");
        }
        sb.deleteCharAt(sb.length() -1);

        System.out.println(sb);
    }

    /**
     * 第一题
     */
    private static void extracted() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) { // 注意 while 处理多个 case
            int length = in.nextInt();
            int arrSize = in.nextInt();
            List<List<Integer>> allArrs = new ArrayList<>(arrSize);

            for(int i = 0 ; i < arrSize; i++){
                String arrStr = in.nextLine();
                if("".equals(arrStr)){
                    arrStr = in.nextLine();
                }
                String[] arr = arrStr.split(",");
                List<Integer> temp = new ArrayList<>();
                for (String s : arr) {
                    temp.add(Integer.parseInt(s));
                }
                allArrs.add(temp);
            }

            StringBuilder sb = new StringBuilder();


            while (true){
                boolean allAdded = true;
                for (List<Integer> allArr : allArrs) {
                    if(allArr.size() > 0){
                        allAdded =false;
                    }
                }
                if(allAdded){
                    break;
                }

                for(int i =0; i< arrSize;i++){
                    List<Integer> subList = allArrs.get(i);
                    if(subList.size()  ==0 ){
                        continue;
                    }
                    for(int j = 0 ;j  < length; j++){
                        if (j >= subList.size()){
                            break;
                        }
                        sb.append(subList.get(j)).append(",");
                    }
                    //删除
                    for(int j = 0 ;j  < length; j++){
                        if (subList.size() == 0){
                            break;
                        }
                        subList.remove(0);
                    }

                }

            }
            sb.deleteCharAt(sb.length() -1);
            System.out.println(sb);

        }
    }

}
