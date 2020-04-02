package List;

import lombok.Data;

import javax.sound.midi.Soundbank;

/**
 * @author SiletFlower
 * @date 2020/3/31 21:03:34
 * @description
 */

/**
* 单链表的创建
 * 1.定义Node类，设置data成员变量放数据，next节点存放下一个节点
 * 2.创建单链表的类，创建头和尾节点用来存放头和尾。
 * 3.1.通过头插法创建链表，方式为当head为null将newNode的对象地址给head,如果head已经有了对象地址，那么将新的节点指向
 * 头节点(此时头节点是第一个节点的对象地址，在把新的节点对象给head)，这样就形成了先进后出的栈形式的链表
 * 3.2.通过尾插法创建链表，方式为使用tailer来记录最新节点的位置。方法为设置最后一个节点对象地址的tailer的下一个地址为
 * 新的节点的对象，然后将tailer传入新的节点的对象。
 * 4.字符串反转包括n个字符前反转和n个后字符反转具体看StringTest练习中的think01(使用头插法来反转)
 *
 * 遍历的方法为设置一个node对象，将head对象地址给它，使用while判断在node不为空的情况下，将node的数据打印并，设置node为
 * next node。
* */
public class SingleList {
    @Data
    class Node{
        private int data;
        private Node next;
        public Node(int data){this.data=data;}
    }

    private Node head;
    private Node tailer;
    private int length;
    public SingleList(){this.head=null;}
    //尾插法，先进先出实现队列
    public void createList_queue(int data){
        Node newNode = new Node(data);
        if (head==null){
            head=tailer=newNode;
        }else{
            tailer.setNext(newNode);
            tailer=newNode;
        }
        length++;

    }


    //头插法，先进后出实现栈结构
    public void createList_stack(int data){
        Node newNode = new Node(data);
        if (head==null){
            head=newNode;
        }else{
            newNode.setNext(head);
            head=newNode;
        }
        length++;
    }


    //链表打印
    public void print(SingleList list){
        Node p = list.head;
        while(p!=null){
            System.out.print(p.getData()+" ");
            p=p.getNext();
        }
    }

    //链表反转头插法(后面的字符也需要反转一次)
    public void reverse(int i){
        int n =0;
        Node q,r,p1,p2;
        p1=head;
        q=head.next;
        head.next=null;
        while (q!=null & n++ < i-1){
            r=q.next;
            q.next=p1;
            p1=q;
            q=r;
        }

        p2=q;
        q=q.next;
        p2.next=null;
        while (q!=null){
            r=q.next;
            q.next=p2;
            p2=q;
            q=r;
        }
        head.next=p2;
        head=p1;



    }



    public static void main(String[] args) {
        int m = 7;
        SingleList singleList = new SingleList();
        while(m-- > 1){
            singleList.createList_stack(m);
        }
        System.out.println("头插法");
        singleList.print(singleList);

        m = 7;
        SingleList singleList2 = new SingleList();
        while(m-- > 1){
            singleList2.createList_queue(m);
        }
        System.out.println();
        System.out.println("尾插法");
        singleList.print(singleList2);

        singleList.reverse(3);
        System.out.println();
        System.out.println("反转方法1");
        singleList.print(singleList);

    }
}

