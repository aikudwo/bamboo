package com.bamboo;


/**
 * @author wls
 * @date 2020-05-27 11:55
 */
public class sort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        bubbleSort(array);
        selectioSotrPreactice(array);
        quickSort(array,0,array.length -1);
        quickSort1(array,0,array.length-1);
        int i = binarySerach(array, 27);
        System.out.println(i);
        print(array);
    }
    private static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    public static void bubbleSort(int[] array){
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                if(array[i] > array[i+1]){
                    int temp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int index = i ;
            for (int j = i +1 ; j < array.length ; j++) {
                if(array[j] < array[index]){
                    index = j;
                }
            }
            //找出最小的，交换位置
            int tem = array[index];
            array[index] = array[i];
            array[i] = tem;
        }
    }


    private static void bubbleSortPricete(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    int tem = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tem;
                }
            }
        }
    }

    private static void selectioSotrPreactice (int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i ;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[index] > arr[j]){
                    index = j ;
                }
            }

            int tem = arr[index];
            arr[index] = arr[i];
            arr[i] = tem;
        }
    }


        public static void quickSort(int[] arr,int low,int high){
            int i,j,temp,t;
            if(low>high){
                return;
            }
            i=low;
            j=high;
            //temp就是基准位
            temp = arr[low];

            while (i<j) {
                //先看右边，依次往左递减
                while (temp<=arr[j]&&i<j) {
                    j--;
                }
                //再看左边，依次往右递增
                while (temp>=arr[i]&&i<j) {
                    i++;
                }
                //如果满足条件则交换
                if (i<j) {
                    t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                }

            }
            //最后将基准为与i和j相等位置的数字交换
            arr[low] = arr[i];
            arr[i] = temp;
            //递归调用左半数组
            quickSort(arr, low, j-1);
            //递归调用右半数组
            quickSort(arr, j+1, high);
        }


    public static void quickSort1(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i= low ;
        j= high;
        temp = arr[low];
        while (i<j){
            //比较右边
            while (temp <= arr[j] && i < j){
                j-- ;
            }
            //比较左边
            while ( temp >= arr[i] && i < j){
                i ++ ;
            }
            //交换位置
            if(i < j){
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t ;
            }

        }
        //当两者在统一位置时，和基准数交换位置
        arr[low] = arr[i];
        arr[i] = temp;

        //递归调用 左边
        quickSort1(arr,low,j -1);
        //递归调用右边
        quickSort1(arr,j+1,high);
    }

    private static int binarySerach(int[] arr,int key){
        int low =0;
        int high = arr.length-1;
        int mid = 0;
        while ( low <= high){
            mid = low +high >>>1 ;
            if(key > arr[mid]){
                low = mid + 1;
            }else if( key < arr[mid]){
                high = mid -1;
            }else {
                return arr[mid] ;
            }
        }
        return -1 ;
    }



}
