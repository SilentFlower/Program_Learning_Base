package String;

import lombok.Data;
import org.junit.Test;

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


    /*
    *
    * 1、链表翻转。给出一个链表和一个数k，比如，链表为1→2→3→4→5→6，k=2，则翻转后2→1→6→5→4→3，若k=3，翻转后3→2→1→6→5→4，若k=4，翻转后4→3→2→1→6→5，用程序实现。
      2、编写程序，在原字符串中把字符串尾部的m个字符移动到字符串的头部，要求：长度为n的字符串操作时间复杂度为O(n)，空间复杂度为O(1)。 例如，原字符串为”Ilovebaofeng”，m=7，输出结果为：”baofengIlove”。
      3、单词翻转。输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变，句子中单词以空格符隔开。为简单起见，标点符号和普通字母一样处理。例如，输入“I am a student.”，则输出“student. a am I”。
    * */

    @Data
    class Node{
        private int data;
        private Node next;
        public Node(int data){this.data=data;}

    }

    class NodeList{
        private Node head;
        private Node tailer;
        private int length = 0;
        public NodeList(){this.head=null;}

        public void addNode(int data){
            Node newNode = new Node(data);
            if(head == null){
                head = tailer= newNode;
            }else{
                tailer.setNext(newNode);
                tailer=newNode;
            }
            length++;
        }

        public void print(){
            Node p = head;
            while (p!=null){
                System.out.print(p.getData()+" ");
                p=p.next;
            }
            System.out.println();
        }

    }

    @Test
    public void think01(){
        NodeList list = new NodeList();
        int n = 0;
        while (n++ <6){
            list.addNode(n);
        }
        list.print();
    }
}
