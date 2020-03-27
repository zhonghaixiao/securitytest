package com.example.securitytest.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main1 {

    public static void main(String[] args){
        System.out.println(splitInteger(496));
//        for (int i = 1; i < 500; i++){
//            if (sum(splitInteger(i)) == i){
//                System.out.println(i);
//            }
//        }
    }

    static int sum(List<Integer> list){
        int sum = 0;
        for (Integer i : list){
            sum += i;
        }
        return sum;
    }

    static List<Integer> splitInteger(int n){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        HashSet<Pair> tempSet = new HashSet<>();
        for (int i = 2 ; i < n/2; i++){
            int pair = n/i;
            if (pair * i == n){
                tempSet.add(new Pair(i,pair));
            }
        }
        Iterator<Pair> iterator = tempSet.iterator();
        while (iterator.hasNext()){
            Pair p = iterator.next();
            list.add(p.m);
            list.add(p.n);
        }
        return list;
    }

    static class Pair{
        int m;
        int n;

        Pair(int m,int n){
            this.m = m;
            this.n = n;
        }

        @Override
        public int hashCode() {
            return m+n + m*n;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair){
                Pair p = (Pair) obj;
                return (m*n == p.m * p.n) && (m+n ==p.m+p.n);
            }else{
                return false;
            }

        }
    }

}
