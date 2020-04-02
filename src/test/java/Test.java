import java.util.Arrays;

/**
 * @author SiletFlower
 * @date 2020/4/3 03:48:53
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String str = "i am a good student";
        char[] arr = str.toCharArray();
        //每个字母进行逆序
        for(int i=0,j=arr.length-1;i<=j;i++,j--){
            char temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        int begin=0;//逆序过的单词的初始位置
        int end=-2;//逆序过的单词的结束位置，为保证不越界，初值为-2（测试时再改）
        for(int i=0;i<arr.length;i++){
            if(arr[i]==' '){
                begin=end+2;
                end=i-1;
                for(int m=begin,n=end;m<=n;m++,n--){
                    char temp=arr[m];
                    arr[m]=arr[n];
                    arr[n]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
