package com.tourism.controller.base;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CFUtils {
    public static void main(String[] args) {
        double[] arr = {3.4,4.6};
        double[] arr1 = {6.8,7.9};
        double[] arr2 = {4.2,3.4};
       /* System.out.println("AB : " + cosineSimilarity(arr,arr1));
        System.out.println("AC : " + cosineSimilarity(arr1,arr2));
        System.out.println("BC : " + cosineSimilarity(arr1,arr2));*/
        Map<Integer, Double> mapX = new HashMap<Integer, Double>();
        Map<Integer, Double> mapY = new HashMap<Integer, Double>();
        mapX.put(1,3.4);
        mapX.put(2,4.6);
        mapY.put(1,4.2);
        mapY.put(2,3.4);
        System.out.println(caculatePearson(mapX,mapY));
    }
    private static  double caculatePearson(Map<Integer, Double> mapX, Map<Integer, Double> mapY) {
        // (sum(X*Y) - (sum(X)*sum(Y)/N))/sqr((sum(pow(X))-(pow(sum(X))/N)) * ((sum(pow(Y))-(pow(sum(Y))/N))))
        double sumXY = 0d;
        double sumX = 0d;
        double sumY = 0d;
        double sumPowX = 0d;
        double sumPowY = 0d;
        Set<Integer> setItem = new HashSet<Integer>();
        for (Map.Entry<Integer, Double> entry : mapX.entrySet()) {
            setItem.add(entry.getKey());
        }
        for (Map.Entry<Integer, Double> entry : mapY.entrySet()) {
            setItem.add(entry.getKey());
        }
        for (int bookId : setItem) {
            Double x = mapX.get(bookId);
            if (x == null) {
                x = 0d;
            }
            Double y = mapY.get(bookId);
            if (y == null) {
                y = 0d;
            }
            sumXY += x * y;
            sumX += x;
            sumY += y;
            sumPowX += Math.pow(x, 2);
            sumPowY += Math.pow(y, 2);
        }
        int n = setItem.size();
        double pearson = (sumXY - sumX * sumY / n) / Math.sqrt((sumPowX - Math.pow(sumX, 2) / n) * (sumPowY - Math.pow(sumY, 2) / n));
        return pearson;
    }
    public static double cosineSimilarity(double[] A, double[] B){
        if(A.length!=B.length){
            return 0.0000;
        }
        if(A==null||B==null){
            return 0.0000;
        }
        long fenzi=0;
        for(int i=0;i<A.length;i++){
            fenzi+=A[i]*B[i];
        }
        long left=0;
        long right=0;
        for(int i=0;i<A.length;i++){
            left+=A[i]*A[i];
            right+=B[i]*B[i];
        }
        if(left*right==0){
            return 0.0000;
        }
        double result=fenzi/Math.sqrt(left*right);
        DecimalFormat df=new DecimalFormat("#.####");
        return Double.parseDouble(df.format(result));
    }
}
