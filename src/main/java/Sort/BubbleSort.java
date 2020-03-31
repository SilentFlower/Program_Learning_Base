package Sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author SiletFlower
 * @date 2020/3/31 18:35:47
 * @description 冒泡排序以及改进版
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[]= new int[]{20,16,9,1,2,4,10,8,19,6};
        int[] sort = BubbleSort_01(arr);
        System.out.println(Arrays.toString(sort));
        int[] sort2 = BubbleSort_02(arr);
        System.out.println(Arrays.toString(sort2));

    }

    /**
        默认的冒泡排序原理。从第一个元素开始比较后一个元素与当前元素的大小，如果前者大则交换位置。
        这样可以将最大的数放在数组末尾，接下来循环最大减1继续进行上述操作可获得排序后的结果。
        这样的时间复杂度O(n²),不过可以继续改进。
     **/
    public static int[] BubbleSort_01(int[] arr){
        for (int i = 0;i<arr.length;i++){
            for (int j = 0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 改进冒泡一:当循环未结束前数组已经排序好则没有必要继续进行循环,添加标志判断数组是否还在交换，不在交换则表明排序完毕
     * 改进冒泡二:改变循环的最大值，如出现情况后面n位已经有序则没有必要交换到arr.length-1，
     * 判断的条件是最后一次交换数组的位置(边界值)
     *当数组过大时优化不太明显
     *
     */
    public static int[] BubbleSort_02(int[] arr){
        int lastChange=0;
        int sortBored=arr.length-1;
        for (int i = 0;i<arr.length;i++){
            boolean isSort=true;
            for (int j = 0;j<sortBored;j++){
                if(arr[j]>arr[j+1]){
                    isSort=false;
                    lastChange=j;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            sortBored=lastChange;
        }
        return arr;
    }
}
