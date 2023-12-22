package com.practice.common.service;

import java.util.Stack;

/**
 * @description: 动态规划练习
 * @author: Francis
 * @date: 2023/9/6 10:01
 */
public class Dp {


    public static void main(String[] args) {
        int[] nums = new int[]{8,9,2,5,4,7,1};

//        System.out.println(maxSumInArr(nums));

//        FindGreatestSumOfSubArray(nums);
//        System.out.println(maxProfit(nums));
        
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(IsPopOrder(new int[]{1,2,3,4,5},new int[]{4,3,5,1,2}));
    }


    /**
     *
     * @param pushV
     * @param popV
     * @return
     */
    public static boolean IsPopOrder(int [] pushV,int [] popV){
        Stack<Integer> stack = new Stack<>();
        System.out.println("空栈弹出");
        if(pushV.length != popV.length){
            return false;
        }
        if(pushV.length ==1 && pushV[0] != popV[0]){
            return false;
        }
        int popIndex = 0;
        for(int i =0; i< pushV.length; i++){
            stack.push(pushV[i]);
            if(stack.isEmpty()){
                break;
            }
            while (popV[popIndex] == stack.peek()){
                if(popIndex == popV.length -1){
                    break;
                }
                popIndex++;
                stack.pop();
            }

        }
        return popIndex == popV.length -1;
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
     * @param s string字符串
     * @return int整型
     */
    public static int lengthOfLongestSubstring (String s) {
        // write code here
        int max = 1;
        for(int j = 0; j < s.length(); j++){
            String newS = s.substring(j);
            int[] dp = new int[newS.length()];
            dp[0] = 1;
            for(int i =1; i < newS.length(); i++){
                dp[i] = newS.substring(0,i).contains(String.valueOf(newS.charAt(i))) ? 1 : dp[i-1] +1;
                if(dp[i] > max){
                    max = dp[i];
                }
            }
        }

        return max;
    }


    /**
     * 在一个
     * m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * @param grid int整型二维数组
     * @return int整型
     */
    public static int maxValue (int[][] grid) {
        // write code here
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < grid.length;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1; i < grid[0].length;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1;i < grid.length;i++){
            for(int j = 1; j < grid[0].length;j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    /**
     * 买股票最大收益
     * @param prices
     * @return
     */
    public static int maxProfit (int[] prices) {
        // write code here
        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for(int i =1; i < prices.length; i++){
            dp[i] = 0;
            int index = i-1;
            while (index >=0){
                int temp = prices[i] - prices[index--];
                if(temp > dp[i]){
                    dp[i] =temp;
                }
            }
            if(max < dp[i]){
                max = dp[i];
            }
        }

        return max;
    }

    /**
     * 返回连续数组最大值
     * @param array
     * @return
     */
    private static int maxSumInArr(int[] array) {
        if(array.length ==1){
            return array[0];
        }
        int[] dp = new int[array.length];
        int[] length = new int[array.length];
        dp[0] = array[0];
        length[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int i =1; i < array.length; i++){
            dp[i] = Math.max(dp[i-1] +array[i],array[i]);
            length[i] = dp[i] >= array[i] ? length[i-1] +1 :1;
            max = Math.max(max,dp[i]);
        }

        return max;
    }

    /**
     * 返回具有最大值的最长连续数组
     * @param array
     * @return
     */
    private static int[] FindGreatestSumOfSubArray(int[] array) {
        if(array.length ==1){
            return new int[]{array[0]};
        }
        int[] dp = new int[array.length];
        int[] length = new int[array.length];
        dp[0] = array[0];
        length[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int i =1; i < array.length; i++){
            dp[i] = Math.max(dp[i-1] +array[i],array[i]);
            length[i] = dp[i-1] + array[i] >= array[i] ? length[i-1] +1 :1;
            max = Math.max(max,dp[i]);
        }
        int len =0;
        int endIndex = 0;
        for(int i =0;i <array.length;i++){
            if(max == dp[i] && length[i] > len){
                len = length[i];
                endIndex = i;
            }
        }

        System.out.println("max | len | endindex :" + max + "|" +len + "|" +endIndex);
        int[] result = new int[len];
        for(int i = endIndex -len +1; i <= endIndex; i++){
            result[i-endIndex + len -1] = array[i];
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i : result) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb);
        return result;
    }
}


