package com.priva.tango.letcode.medium;

public class FindMinStr {

    public static void main(String[] args) {
        System.out.println(PrintMinNumber(new int[]{3,32,321}));
    }
    static String min = "99999999";
    public static String PrintMinNumber(int [] numbers) {
        boolean [] done = new boolean [numbers.length];
        for(int x = 0;x<numbers.length;x++){
            backtracing(numbers, done, x, 0, "");
        }
        return min;
    }

    public static void backtracing(int [] numbers, boolean [] done, int index, int times, String current){
        if(times > numbers.length - 1){
            return;
        }
        current += numbers[index];
        if(Integer.valueOf(min)> Integer.valueOf(current) && times == numbers.length - 1){
            min = current;
        }
        done[index] = true;
        ++times;
        for(int x = 0;x<numbers.length;x++){
            if(done[x] == true){
                continue;
            }
            backtracing(numbers, done, x, times, current);
        }
        done[index] = false;
    }
}
