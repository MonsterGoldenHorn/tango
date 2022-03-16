package com.priva.tango.letcode;

/**
 * 排序算法
 */
public class SortFunc {
    public static void main(String[] args) {
        int a[] = {6,1,2,7,9,3,4,5,10,8};
        quickSort(a,0,a.length-1);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
        }
    }
    //1.冒泡，将最小的数往上比较，需要比较多次，时间复杂O(n^2)
    //2.选择排序，遍历最大或者最小的进行交换
    //3.插入排序，后来的数据寻找大于自己的就把数据往后移动，直到遇到小于自己的
    //4.希尔排序，和增量位置的数据比较，增量最后变成1
    //5.归并排序，多路归并，小范围有序后合并成大范围有序
    //6.快速排序，

    private static void quickSort(int[] arr, int start, int end){
        if(end<start){
            return;
        }
        int k = arr[start];
        int left = start;
        int right = end;
        int temp = 0;
        while(left<right){
            while(left<right && arr[right]>=k){
                right--;
            }
            while(left<right && arr[left]<=k){
                left++;
            }
            //这里双循环完成后left>k && right<k，直接交换
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        //双循环先动的右边,右边都是大于的，
        arr[start] = arr[right];
        arr[right] = k;

        quickSort(arr, start, left-1);
        quickSort(arr, left+1, end);
    }
}
