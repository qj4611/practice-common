package com.practice.common.service;

import java.util.Objects;
import java.util.Stack;


/**
 * @description: desc
 * @author: Francis
 * @date: 2023/9/7 18:55
 */
public class Interview {

    public static void main(String[] args) {

        System.out.println(isValid( "{[]}[]"));

    }


    private static boolean isValid(String str) {
        if(Objects.isNull(str) || str.length() == 0){
            return false;
        }
        String regex = "^[(|{|)|}|\\[|\\]]+$";
        if(!str.matches(regex)){
            return false;
        }
        Stack<Character> charStack = new Stack<>();
        for (Character ch : str.toCharArray()) {
            switch (ch){
                case '(':
                    charStack.push(')');
                    break;
                case '[':
                    charStack.push(']');
                    break;
                case '{':
                    charStack.push('}');
                    break;
                case '}':
                    if(!charStack.isEmpty() && charStack.peek() == '}'){
                        charStack.pop();
                    }else {
                        return false;
                    }
                    break;
                case ')':
                    if(!charStack.isEmpty() && charStack.peek() == ')'){
                        charStack.pop();
                    }else {
                        return false;
                    }
                    break;
                case ']':
                    if(!charStack.isEmpty() && charStack.peek() == ']'){
                        charStack.pop();
                    }else {
                        return false;
                    }
                    break;
            }
        }
        return charStack.isEmpty();
    }


}
