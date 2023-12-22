package com.practice.common.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class ReferenceB {

    @Lazy
//    @Resource(type = ReferenceA.class)
    private ReferenceA referenceA;

    public ReferenceA getReferenceA() {
        return referenceA;
    }

    public void setReferenceA(ReferenceA referenceA) {
        this.referenceA = referenceA;
    }

    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int size = in.nextInt();
//            int[] nums = new int[size];
//            int evenNum = 0;
//            for(int i = 0; i < size;i++){
//                nums[i] = in.nextInt();
//                if(nums[i] % 2 == 0){
//                    evenNum++;
//                }
//            }
//            int[] evenNums = new int[evenNum];
//            int[] oddNums = new int[size -evenNum];
//            int i = 0, j=0;
//            for (int num : nums) {
//                if(num%2 == 0){
//                    evenNums[i++] = num;
//                }else {
//                    oddNums[j++] = num;
//                }
//            }
//
//            int count = 0;
//            int[] oddMatched = new int[oddNums.length];
//            for(int x = 0; x < evenNums.length; x++){
//                boolean[] oddUsed = new boolean[oddNums.length];
//                if(canFind(evenNums[x],oddUsed,oddMatched,oddNums)){
//                    count++;
//                }
//            }
//            System.out.println(count);
//            break;
//        }

        System.out.println("jump:" + jumpFloor2(10));
        System.out.println("dp:" + dp(10));


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

    /**
     * 动态规划
     * @return
     */
    public static int jumpFloor4(int n){
        int  a =1,b=1,c=1;
        for(int i =2; i <= n;i++){
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }

    public  static  boolean canFind(int target, boolean[] used,int[] oddMatched,int[] oddNums){
        for(int y = 0; y < oddNums.length; y++){
            if(!used[y] && isPrime(target + oddNums[y])){
                used[y] = true;
                if(oddMatched[y] == 0 || canFind(oddMatched[y],used,oddMatched,oddNums)){
                    oddMatched[y] = target;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 计算质数
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 腐烂的橘子 leetcode 994
     * @param grid
     * @return
     */
    private static int orangesRotting(int[][] grid) {
        //当前所有的橘子都腐烂后，返回
        boolean hasRotted = true;

        int count = 0;
        while (hasRotted){

            hasRotted =false;
            //如果周围有被感染的橘子，标记当前橘子
            for (int i =0; i < grid.length; i++){
                for(int j =0; j < grid[i].length;j++){
                    if(grid[i][j] == 1){
                        if(i -1 >= 0 && grid[i-1][j] == 2){
                            grid[i][j] =3;
                            hasRotted =true;
                        }
                        if(i+1 < grid.length  && grid[i+1][j] == 2){
                            grid[i][j] =3;
                            hasRotted =true;
                        }
                        if(j-1 >=0 && grid[i][j-1] == 2){
                            grid[i][j] =3;
                            hasRotted =true;
                        }
                        if(j+1 < grid[0].length && grid[i][j+1] == 2){
                            grid[i][j] =3;
                            hasRotted =true;
                        };
                    }
                }
            }

            //将标记的全部感染
            for (int i =0; i < grid.length; i++){
                for(int j =0; j < grid[i].length;j++){
                    if(grid[i][j] == 3){
                        grid[i][j] = 2;
                    }
                }
            }

            if(hasRotted){
                count++;
            }

        }

        for (int i =0; i < grid.length; i++){
            for(int j =0; j < grid[i].length;j++){
                if(grid[i][j] == 1){
                    return  -1;
                }
            }
        }
        return count;
    }

    private static int[][] rebuildGrid(int[][] grid) {
        int[][] result = new int[grid.length][grid[0].length];

        for (int i =0; i < grid.length; i++) {
            int[] temp = new int[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                  temp[j] = grid[i][j];
            }
            result[i] = temp;
        }
        return result;
    }


    /**
     * 数据分类处理
     */
    private static void extracted() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int sizeI = in.nextInt();
            int[] arrI = new int[sizeI];
            for(int i =0; i < sizeI;i++){
                arrI[i] = in.nextInt();
            }
            int sizeR = in.nextInt();

            Set<Integer> arrRSet = new HashSet<>();
            for(int i =0 ; i < sizeR; i++){
                arrRSet.add(in.nextInt());
            }
            int[] arrR = new int[arrRSet.size()];
            int n= 0;
            for (Integer num : arrRSet) {
                arrR[n++] = num;
            }
            Arrays.sort(arrR);
            List<Integer> result = new ArrayList<>();
            for(int i = 0; i < arrR.length; i++){
                List<Integer> tempList = new ArrayList<>();
                int count =0;
                for(int j =0; j <sizeI;j++){
                    if(String.valueOf(arrI[j]).contains(String.valueOf(arrR[i]))){
                        tempList.add(j);
                        tempList.add(arrI[j]);
                        count++;
                    }

                }

                if(CollectionUtils.isEmpty(tempList)){
                    continue;
                }
                result.add(arrR[i]);
                result.add(count);
                result.addAll(tempList);
            }

            StringBuilder sb = new StringBuilder();
            if(CollectionUtils.isEmpty(result)){
                return;
            }
            sb.append(result.size());
            for (Integer num : result) {
                sb.append(" ").append(num);
            }
            System.out.println(sb);
            break;
        }
    }



    private static void getCount(int[] evenNums, int[] oddNums, boolean[] evenMatched, boolean[] oddMatched, int count, int[] primeCount) {
        for(int x = 0; x < evenNums.length; x++){
            for(int y = 0; y < oddNums.length; y++){
                if(!evenMatched[x] && !oddMatched[y] && isPrime(evenNums[x] + oddNums[y])){
                    evenMatched[x] = true;
                    oddMatched[y] = true;
                    count++;
                    if(count > primeCount[0]){
                        primeCount[0] = count;
                    }
                    getCount(evenNums,oddNums,evenMatched,oddMatched,count,primeCount);
                    break;
                }
            }
        }
    }


    /**
     * 素数伴侣
     */
    public static void numCm(int[] arr,int[] count,List<Integer> temp,int startIndex){
        if(temp.size() == 2){
            int sum = temp.get(0) + temp.get(1);
            if (isPrime(sum))
                return;
        }

        for(int i =startIndex; i < arr.length; i++){
            temp.add(arr[i]);
            numCm(arr,count,temp,i+1);
            temp.remove(temp.size() -1);
        }

    }

    public static void combineMinPrimeWith(int n){
        int a =0 , b =n;
        int increase = 2;
        if(n <= 2){
            return;
        }
        while (increase < n-1){
            if(isPrime(increase) && isPrime(n-increase)){
                if (Math.abs(increase - (n-increase)) < Math.abs(a -b)){
                    a = increase;
                    b= n-increase;
                }
            }
            increase++;
        }
        System.out.println(a);
        System.out.println(b);

    }

    /**
     * 判断一个数是不是素数
     * @param sum
     * @return
     */
    private static boolean isPrime(int sum) {
        if(sum == 2){
            return true;
        }
        if(sum % 2 != 0){
            boolean isSuShu = true;
           for(int i = 3; i < sum/2; i+=2){
               if(sum % i == 0){
                   isSuShu =false;
                   break;
               }
           }
           if(isSuShu){
//                   System.out.println("素数：" + sum);
               return true;
           }
        }
        return false;
    }

    /**
     * 最小公倍数
     */
    public static int minCommonNum(int a,int b) {

        if(a >= b && a %b == 0){
            return a;
        }else if(b%a == 0){
            return b;
        }else {
           int  max =  a *b;
           int min = a*b;
            int max1 = Math.max(a, b);
            while (max > max1){
                max--;
//                System.out.println(max);
               if(max % a == 0 && max % b == 0){
                   min = max;
               }
           }
            return min;
        }


    }

    /**
     * 按之字形遍历二叉树
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(Objects.isNull(root)){
            return result;
        }
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        addValues(rootList,result,true);

        return result;
    }

    private static void addValues(List<TreeNode> nodeList, List<List<Integer>> result, boolean fromLeft) {
        List<Integer> tempValuesList = new ArrayList<>();
        List<TreeNode> newNodeList = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if(Objects.nonNull(treeNode)){
                tempValuesList.add(treeNode.val);
            }
            if(Objects.nonNull(treeNode.left)){
                newNodeList.add(treeNode.left);
            }
            if(Objects.nonNull(treeNode.right)){
                newNodeList.add(treeNode.right);
            }
        }

        if(!fromLeft){
            Collections.reverse(tempValuesList);
        }
        result.add(tempValuesList);
        fromLeft = !fromLeft;
        if(newNodeList.size() > 0){
            addValues(newNodeList,result,fromLeft);
        }
    }

    private static void setTreeValue(TreeNode treeNode, Integer treeValue, int i) {
        int power =1;
        while (true){
            if(i > Math.pow(2,(power-1)) && i <= Math.pow(2,power)){
                break;
            }
            power++;
        }
        int x = 0;
        List<TreeNode> currentNodes = new ArrayList<>();
        currentNodes.add(treeNode);
        while ( x <= power){
            if(x > 0 && x < power){
                for (TreeNode currentNode : currentNodes) {
                    if(Objects.isNull(currentNode.left)){
                        currentNode.left = new TreeNode(0);
                    }
                    if(Objects.isNull(currentNode.right)){
                        currentNode.right = new TreeNode(0);
                    }
                }
            }
            i -= Math.pow(2,x++);
        }
        System.out.println("treeValue,index " + treeValue + " | " + i);

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();
        if(Objects.isNull(root)){
            return resultList;
        }
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        setValues(nodes,resultList);
        return resultList;
    }

    private static void setValues(List<TreeNode> nodes,  List<List<Integer>> resultList) {
        List<TreeNode> nodeList = new ArrayList<>();
        List<Integer> tempValueList = new ArrayList<>();
        for (TreeNode node : nodes) {
            if(Objects.nonNull(node)){
                tempValueList.add(node.val);
                if(Objects.nonNull(node.left)){
                    nodeList.add(node.left);
                }
                if(Objects.nonNull(node.right)){
                    nodeList.add(node.right);
                }
            }
        }
        resultList.add(tempValueList);
        if(nodeList.size() > 0){
            setValues(nodeList,resultList);
        }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    private static void combineWeigh(int num, int[] weightArr, Set<Integer> distinctWeight, int weight,int startIndex,int plusCount) {
        System.out.println("set size:" + distinctWeight.size());

        if(plusCount == num){
            distinctWeight.add(weight);
            return;
        }

        for(int i =startIndex; i < weightArr.length;i++){
                weight += weightArr[i];
                plusCount++;
                combineWeigh(num,weightArr,distinctWeight,weight,i+1,plusCount);
                weight -= weightArr[i];
                plusCount--;
        }
    }

    /**
     * 给出两个字符串 s 和 t，要求在 s 中找出最短的包含 t 中所有字符的连续子串。
     * ，保证s和t字符串中仅包含大小写英文字母
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public static String minWindow(String S, String T) {
        // write code here

        String result = "";
        int left=0; int right =1;
        int[] needMatch = new int['z' +1];
        for (char c : T.toCharArray()) {
            needMatch[c]++;
        }
        while (true) {
            String temp = S.substring(left, right);
            if (allContains(needMatch,temp,T.length())) {
                if(result ==""){
                    result =temp;
                }else {
                    if(temp.length() < result.length()){
                        result = temp;
                    }
                }
                left++;
                if (left > S.length() - 1) {
                    break;
                }
            } else {
                right++;
                if (right > S.length()) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * temp 中是否包含t中全部字符
     * @return 是：true，否：false
     */
    private static boolean allContains(int[] matchArr, String temp, int length) {

        int[] newArr = matchArr.clone();
        int count = 0;
        for (char c : temp.toCharArray()) {
            if(newArr[c] > 0){
                newArr[c]--;
                count++;
            }
        }


        return count >= length;
    }


    /**
     * 最长的回文数
     * @param A
     * @return
     */
    public static int getLongestPalindrome (String A) {
        // write code here
        char[] arr = A.toCharArray();
        int max = 1;
        for(int i = 1; i < arr.length; i++){
            int step = 1;
            while ((i - step) >= 0 && (i + step) < arr.length){
                if(i+step < arr.length && arr[i-step] == arr[i+step]){
                    if(step * 2 + 1 > max){
                        max =step * 2 + 1;
                    };
                    step++;
                }
                else {
                    break;
                }
            }

        }

        for(int i = 1; i < arr.length; i++){
            int step = 1;
            while ((i - step) >= 0 && (i + step -1) < arr.length){
                if(arr[i-step] == arr[i+step-1]){
                    if(step * 2 > max){
                        max =step * 2;
                    };
                    step++;
                }
                else {
                    break;
                }
            }

        }
        return max;
    }


    /**
     * 最长连续递增序列
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int endIndex =0;
        int temp = 1;
        for(int i =0;i<nums.length-1; i++){
            if(nums[i] < nums[i+1]){
                if(++temp > max){
                    max =temp;
                    endIndex = i +1;
                };

            }else {
                temp =1;
            }
        }
        System.out.println("max:" + max + " endIndex:" + endIndex);

        StringBuilder sb = new StringBuilder("[");
        for (int i = endIndex - max + 1; i <= endIndex; i++){
            sb.append(nums[i]).append(",");
        }
        sb.append("]");
        System.out.println("子序列是：" + sb);
        return max;
    }

    public static List<List<Integer>> combine(int n, int k) {
//        Set<List<Integer>> result =new HashSet<>();

        List<List<Integer>> result =new ArrayList<>();
        boolean[] book = new boolean[k];
        int[] array = new int[n];
        for(int i = 1; i <= n; i++){
            array[i-1] = i;
        }

        dfs3(n,k,1,new ArrayList<Integer>(),result);
        for (List<Integer> integers : result) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Integer integer : integers) {
                sb.append(integer).append(",");
            }
            sb.deleteCharAt(sb.length() -1);
            sb.append("]");
            System.out.println(sb);
        }

        return result;
    }

    /**
     * 回溯算法
     * @param n
     * @param k
     * @param startIndex
     * @param tempList
     * @param result
     */
    private static void dfs3(int n, int k, int startIndex, List<Integer> tempList, List<List<Integer>> result) {
        if(tempList.size() == k){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i =startIndex; i <= n; i++){
            tempList.add(i);
            dfs3(n,k,i+1,tempList,result);
            tempList.remove(tempList.size() -1);

        }
    }

    public static String[] permutation(String S) {
        Set<String> allStr = new HashSet<>();
        char[] array = S.toCharArray();
        Arrays.sort(array);
        boolean[] book = new boolean[S.length()];
        dfs2(allStr,new StringBuilder(),book,array);

        String[] strings = new String[allStr.size()];
        int i =0;
        for (String s : allStr) {
            strings[i++] = s;
            System.out.print(s + " ");
        }
        System.out.println(i);
        return strings;
    }

    private static void dfs2(Set<String> allStr, StringBuilder sb, boolean[] book, char[] array) {
        if(array.length == sb.length()){
            allStr.add(sb.toString());
            return;
        }
        for(int i =0; i < array.length; i++){
            if(!book[i]){
                book[i] = true;
                sb.append(array[i]);
                dfs2(allStr,sb,book,array);
                book[i] =false;
                sb.deleteCharAt(sb.length() -1);
            }
        }
    }

    /**
     * 有重复字符的字符串排列组合
     */
    public static String[] permutation2(String S) {

        List<String> list = new ArrayList<>();
        char[] arr = S.toCharArray();
        Arrays.sort(arr);
        boolean[] book = new boolean[arr.length];
        dfs(list, new StringBuilder(), book, arr);

        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);

        return res;
    }

    public static void dfs(List<String> res, StringBuilder sb, boolean[] book, char[] arr) {

        if (sb.length() == arr.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!book[i]) {
                if (i > 0 && arr[i] == arr[i - 1] && !book[i - 1])
                    continue;
                else {
                    sb.append(arr[i]);
                    book[i] = true;
                    dfs(res, sb, book, arr);
                    book[i] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    /**
     * 计算括号的最大深度
     * @param s
     * @return
     */
    public static int maxDepth(String s) {
        for (char ch : s.toCharArray()) {
            if(ch != '(' && ch != ')'){
                s=s.replace(String.valueOf(ch),"");
            }
        }
        boolean flag = true;
        int i=0;
        while (flag){
            if("".equals(s.trim())){
                break;
            }
            int len = s.length();
            s=s.replaceAll("\\(\\)","");
            i++;
            if(len == s.length()){
                flag =false;
            }
        }
        System.out.println(i);
        return i;
    }

    public static  boolean checkBracket2(String s){
        boolean flag = true;
        while(flag){
            int len = s.length();
            s=s.replace("()","");
            s=s.replace("[]","");
            s=s.replace("{}","");
            if(len == s.length()){
                flag=false;
            }
        }
        return s.length() == 0;
    }

    public static  void checkBracket(String s){
        Stack<Character> stack = new Stack<>();
        boolean valid =true;
        for(int i=0; i<s.length();i++){
            char ch = s.charAt(i);
            switch (ch){
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    if(stack.isEmpty() || ch != stack.lastElement()){
                        valid =false;
                    }
                    stack.pop();

            }
        }

        if(!stack.isEmpty()){
            valid =false;
        }


        System.out.println(valid);
    }

    // {(
    /**
     * mapSort
     */
    private static void sortMap(LinkedHashMap<String, Integer> map,int escSort) {
        Collection<Integer> values = map.values();
        List<Integer> valueList = new ArrayList<>(values);
        Collections.sort(valueList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2){
                    return 0;
                }
                if(escSort == 0){
                    return o1 > o2 ? -1 :1;
                }else{
                    return o1 > o2 ? 1: -1;
                }


            }
        });

        LinkedHashMap<String,Integer> newSet = new LinkedHashMap<>();
        for (Integer integer : valueList) {
            for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                if(integer.equals(stringIntegerEntry.getValue())){
                    newSet.put(stringIntegerEntry.getKey(),integer);
                    map.remove(stringIntegerEntry.getKey());
                    break;
                }
            }
        }

        for (Map.Entry<String, Integer> en : newSet.entrySet()) {
            System.out.println(en.getKey() + " " + en.getValue());
        }
    }

    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public static   ArrayList<Interval> merge (ArrayList<Interval> intervals){
        ArrayList<Interval> resultList = new ArrayList<>();

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start < o2.start ? -1:1;
            }
        });

        int index =1;
        for (Interval interval : intervals) {
            int origin = interval.end;
            int start = interval.start;
            if(start >= origin){
                resultList.add(new Interval(interval.start,intervals.get(index).end));
            }
        }

        Collections.sort(resultList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start < o2.start ? -1:1;
            }
        });
        return resultList;
    }
// [[1,4],[2,3]]
public static class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

      
    }

    private static boolean arrayEqual(char[] array, char[] targetCharArray) {
        if(array.length != targetCharArray.length){
            return false;
        }

        for(int i = 0; i < array.length; i++){
            if(array[i] != targetCharArray[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 升序快排
     * @return
     */
    private static List<String> escList(List<String> list) {
        String[] array = new String[list.size()];
        for (int i=0; i<array.length;i++) {
            array[i] = list.get(i);
        }
        for(int i = 0; i < array.length; i++){
            String temp = array[i];
            int index = 0;
            for(int j = i+1; j < array.length;j++){
                if(array[j].compareTo(temp) < 0){
                    temp = array[j];
                    index = j;
                }
            }
            if(index > 0){
                array[index] = array[i];
                array[i] = temp;
            }
        }
        return Arrays.asList(array);
    }


    /**
     * 升序快排
     * @param keys
     * @return
     */
    private static int[] escSorted(Collection<Integer> keys) {
        int[] array = new int[keys.size()];
        int x = 0;
        for (Integer key : keys) {
            array[x++] = key;
        }

        for(int i =0; i< array.length; i++){
            int temp = array[i];
            int index = 0;
            for(int j =0; j < array.length; j++){
                if(j < i){
                    continue;
                }
                if (temp > array[j]){
                    temp = array[j];
                    index = j;
                }
            }
            if(index > 0){
                array[index] = array[i];
                array[i] = temp;
            }
        }

        return array;
    }

    private static void reverseStr(String input) {
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = charArray.length -1; i >= 0; i--){
            sb.append(String.valueOf(charArray[i]));
        }
        System.out.println(sb);
    }

    /**
     * 快排
     * @param arrayStr
     * @param descSortFlag
     */
    private static void sortInputNumStr(String arrayStr, int descSortFlag) {
        String[] numStrs = arrayStr.split(" ");
        int[] numArray = new int[numStrs.length];
        for (int i =0; i < numStrs.length;i++) {
            numArray[i] = Integer.parseInt(numStrs[i]);
        }

        for(int i =0; i < numArray.length; i++){
            int temp = numArray[i];
            int index = -1;
            for(int j=0; j < numArray.length; j++){
                if(j <= i){
                    continue;
                }
                if(numArray[j] > temp && descSortFlag == 1){
                    temp = numArray[j];
                    index = j;
                }
                if(numArray[j] < temp && descSortFlag == 0){
                    temp = numArray[j];
                    index = j;
                }
            }
            if(index > 0){
                numArray[index] = numArray[i];
                numArray[i] = temp;
            }
        }

        for (int i : numArray) {
            System.out.print(i + " ");
        }
    }

    static int N = 4;
    public static String convert(String str) {
        System.out.println("当前输入：" + str);
        // ipv4 -> int
        if (str.contains(".")) {
            String[] fields = str.split("\\.");
            long result = 0;
            for (int i = 0; i < N; i++) {
                System.out.println("ip段：" + fields[i] + " 计算前：" + result);
                result = result * 256 + Integer.parseInt(fields[i]);
                System.out.println("计算后：" + result);
            }
            return "" + result;
        }
        // int -> ipv4
        else {
            long ipv4 = Long.parseLong(str);
            String result = "";
            for (int i = 0; i < N; i++) {
                result = ipv4 % 256 + "." + result;
                ipv4 /= 256;
                System.out.println("result:" + result + " ipv4:" +ipv4);
            }
            return result.substring(0, result.length() - 1);
        }
    }

    /**
     * ip 与 十进制转换
     * @param ip
     * @param ipInt
     */
    public static void transferIp2Decimalism(String ip,Integer ipInt){
        String[] ipArray = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        int i =0;
        for(String str: ipArray){
            String bitStr = Integer.toString(Integer.valueOf(str), 2);

            if(bitStr.length() < 8){
                StringBuilder temp = new StringBuilder();
                for(int j  =0; j < 8-bitStr.length(); j++){
                    temp.append("0");
                }
                temp.append(bitStr);
                sb.append(temp);
            }else {
                sb.append(bitStr);
            }
        }

        long result = Long.parseLong(sb.toString(), 2);
        System.out.println(result);


        String ipStr = Long.toString(ipInt, 2);
        if(ipStr.length() % 8 != 0){

            StringBuilder str0 = new StringBuilder();
            int rightLength = ((ipStr.length() / 8) + 1 ) * 8;
            for(int x = 0; x < rightLength -ipStr.length(); x++){
                str0.append("0");
            }
            ipStr = str0.append(ipStr).toString();
        }
        StringBuilder newIp = new StringBuilder();
        for (int j =0; j < 4; j++){
            String ip2 = ipStr.substring(j*8,j*8+8);
            Integer integer = Integer.valueOf(ip2, 2);
            newIp.append(integer);
            if(j < 3){
                newIp.append(".");
            }
        }
        System.out.println(newIp);


    }

    /**\
     * 删除字符串中出现最少得数字
     */
    public static void deleteLatestCountChar(String str){
        char[] charArray = str.toCharArray();
        HashMap<String,Integer> char2CountMap = new HashMap<>();
        for(int i =0; i < charArray.length; i++){
            String ch = String.valueOf(charArray[i]);
            if(char2CountMap.containsKey(ch)){
                char2CountMap.put(ch,char2CountMap.get(ch) + 1);
            }else {
                char2CountMap.put(ch,1);
            }
        }

        Collection<Integer> values = char2CountMap.values();
        int minValue = Integer.MAX_VALUE;
        for (Integer value : values) {
            if (value < minValue){
                minValue = value;
            }
        }

        List<String> replaceChars = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : char2CountMap.entrySet()) {
            if(entry.getValue().equals(minValue)){
                replaceChars.add(entry.getKey());
            }
        }

        for (String replaceChar : replaceChars) {
            str =str.replaceAll(replaceChar,"");
        }

        System.out.println(str);

    }

    /**
     * 坐标计算
     * @param str
     */
    public static void calculateCoordinate(String str){
        String regex = "^[AWSD | awsd][1-9][0-9]{0,8}$";
        int x=0,y=0;
        String[] coordinateSteps = str.split(";");
        for(int i=0;i<coordinateSteps.length;i++){
            if(!coordinateSteps[i].matches(regex)){
                continue;
            }
            String currentStep = coordinateSteps[i];
            String direction = currentStep.substring(0, 1).toUpperCase();
            int step = Integer.parseInt(currentStep.substring(1));
            System.out.println("direction:" + direction + " step:" + step);
            if("A".equals(direction)){
                x -= step;
            }else if("S".equals(direction)){
                y -= step;
            }else if("D".equals(direction)){
                x += step;
            }else if("W".equals(direction)){
                y += step;
            }
        }

        System.out.println("(" + x + ", " + y + ")");
    }

    /**
     * 计算字符串
     */
    public static void calculateChar(String str){
        char[] charArray = str.toCharArray();
        Set<Character> charSet = new HashSet<>();
        for (char c : charArray) {
            charSet.add(c);
        }

        int count = 0;
        for (Character character : charSet) {
            char temp = character;
            if(temp <= 127){
                count++;
            }
        }

        System.out.println("不同的字符串数为：" + count);
    }


    /**
     * 动态规划
     * @param number
     * @return
     */
    public static int jumpFloor3(int number) {

        int res1 =1,res2 = 1;

        while(number-- > 0){

            res2+=res1;

            res1 =res2 - res1;

        }

        return res1;

    }


    public  static  int jumpFloor2(int target) {
        if(target <= 0)
            return 0;
        if(target == 1)
            return 1;
        //最多可以跳half个两级台阶
        long half = target/2;
        long result = 0;
        for(long i = 0;i <= half;i++){
            //求每一次跳i个两级有几种方法
            long m = target - i;
            long son = 1;
            long mom = 1;
            for(long x = m - i + 1;x <= m;x++){
                son *= x;
            }
            for(long y = 1;y <= i;y++){
                mom *= y;
            }
            long sum = son/mom;
            result += sum;

        }
        return (int)result;


    }

    /**
     * 跳台阶
     */
    public static void  jumpFloor(int number){
        int minStep = number%2 == 0 ? number/2 : number/2 +1;
        int totalStep = 0;
//        走两步的次数
        int count2Step = 0;
        for(int currentStepCount = number; currentStepCount >= minStep;currentStepCount--){

            System.out.println("当前总步数："  +  currentStepCount + " 走两步的步数：" + count2Step );
            //计算总的有多少个2步
            if(count2Step > 0){
                long father = mul(currentStepCount,count2Step);
                long son = mul(count2Step,count2Step);
                System.out.println("当前步数共有" + father/son + "种走法" + " father:" + father + " 除以 " + son );
                totalStep += father/son;
            }else {
                totalStep += 1;
            }
            count2Step++;
        }
        System.out.println("总的方法数:" + totalStep);

    }

    private static long mul(int number, int count2Step) {
        long result = 1;
        StringBuilder sb = new StringBuilder(number);
        for(int i = number; i > number - count2Step; i--){
            result = result * i;
            sb.append("*").append(i);
        }
        System.out.println("C " + number + " " + count2Step + " 数序表达式：" + sb.toString());
        return result;
    }

    /**
     * 两数之和
     */
    public static void sumOfTwoNums(int[] numbers, int target){
        int index1 = 0, index2= 0;
        for(int i = 0; i < numbers.length; i++){
            for (int j= 0; j < numbers.length; j++){
                if(j > i && target - numbers[i] == numbers[j]){
                    index1 = i;
                    index2 = j;
                    break;
                }
            }
        }
        System.out.println("[" + index1 + "," + index2 + "]" );
    }

    /**
     * N个随机数去重后排序  冒泡排序
     */
    public static void resortInputString(List<Integer> numList){
        int length = numList.size();
        int[] result = new int[numList.size()];

        for (int i = 0; i < length; i++) {
            int temp = 0;
            for (int j = 0 ; j < length; j++) {

                if (numList.get(j) <= temp ) {
                    if(i == 0){
                        temp = numList.get(j);
                    }else{
                        if(numList.get(j) > result[i-1]){
                            temp = numList.get(j);
                        }
                    }

                }
            }
            result[i] = temp;
        }

        for (int i = 0; i < length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 转换16进制为10进制
     * @param hexStr
     * @return
     */
    public static int transferHex(String hexStr){
        int resultTotal = 0;
        String regex = "^[0-9 | a-e]+$";
        String regexNum = "^[0-9]+$";
        hexStr = hexStr.toLowerCase();
//        if(!hexStr.matches(regex)){
//            System.out.print("输入的数字不是16进制");
//            System.exit(1);
//        }

        char[] charArray =  hexStr.toCharArray();
        int power = charArray.length -1;
        for(int i = 0; i < charArray.length; i++){
            System.out.println("当前total："  +  resultTotal + " 当前字符：" + charArray[i] + " power:" + power );
            if(!String.valueOf(charArray[i]).matches(regex)){
                power--;
                continue;
            }
            int num = 0;
            if(String.valueOf(charArray[i]).matches(regexNum)){
                num = Integer.parseInt(String.valueOf(charArray[i]));
            }else{
                num = charArray[i] - 87;
            }

            resultTotal += num * Math.pow(16,power--);
        }
        return resultTotal;
    }
}
