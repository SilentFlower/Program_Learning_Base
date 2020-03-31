package String;

/**
 * @author SiletFlower
 * @date 2020/3/31 19:58:45
 * @description 字符串的反转
 */
public class Reverse {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("abcdefg");
        StringBuilder res1 = reverse_01(s, 3);
        System.out.println(res1);
        StringBuilder s2 = new StringBuilder("abcdefg");
        StringBuilder res2 = reverse_02(s2, 4);
        System.out.println(res2);
    }


    /**
    * 第一种将后一位提前到前一位，复杂度为O(n*m)
    * */
    public static StringBuilder reverse_01(StringBuilder s,int g){
        while(g-- > 0) {
            char temp = s.charAt(0);
            for (int i = 0; i < s.length()-1; i++) {
                s.setCharAt(i,s.charAt(i+1));
            }
            s.setCharAt(s.length()-1,temp);
        }
        return s;
    }

    /**
    第二种优化,将需要反转的n个字符先进行反转(通过i和j实现完全反转)，不需要反转的字符也进行完全反转
     最后将整个字符完全反转。打个比方abcdfe,我需要旋转前三位。则为cba与efd拼接为cbaefd，最后在反转为dfeabc即为旋转后的结果
     这个思想是将需要旋转的部分设为X，不需要旋转的设为Y，原来为XY,则(X^T Y^T)^T=YX
     空间复杂度
    * */
    public static StringBuilder reverse_02(StringBuilder s,int g){
        s = reverse_part(s,0,g - 1 );
        s = reverse_part(s, g, s.length() - 1);
        s = reverse_part(s,0,s.length()-1);
        return s;
    }

    public static StringBuilder reverse_part(StringBuilder s,int i,int j){
        while(i<j){
            char temp = s.charAt(i);
            s.setCharAt(i++,s.charAt(j));
            s.setCharAt(j--,temp);
        }
        return s;
    }
}
