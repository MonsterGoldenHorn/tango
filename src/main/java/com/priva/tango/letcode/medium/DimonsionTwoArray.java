package com.priva.tango.letcode.medium;

/**
 *跳表查找
 */
public class DimonsionTwoArray {
    public static void main(String[] args) {
        int[][] ints = new int[4][4];
        ints[0] = new int[4];
    }
    public boolean Find(int target, int [][] array) {
        int length = array.length;
        int size = array[0].length;
        if(length == 0 || size ==0 ){
            return false;
        }

        int x = 0;
        int y = 0;
        while(x<length){
            boolean glt = false;
            boolean llt = false;
            while(y<size){
                if(target == array[x][y]){
                    return true;
                }
                else if(target < array[x][y]){
                    if(y==0){
                        break;
                    }
                    y--;
                    llt = true;
                }
                else if(target > array[x][y]){
                    if(y==size-1){
                        break;
                    }
                    y++;
                    glt = true;
                }
                if(llt && glt){
                    break;
                }
            }
            x++;
        }
        return false;
    }
}
