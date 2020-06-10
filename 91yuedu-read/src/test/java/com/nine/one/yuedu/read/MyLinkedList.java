package com.nine.one.yuedu.read;

/**
 * @author wangyuliang
 * 手写双向链表
 */
public class MyLinkedList<E> {

    /**
     * 结点
     *
     * @param <E>
     */
    private static class Node<E> {
        private Node<E> prev;//当前结点前一结点
        private Node<E> next;//当前结点后一结点
        private E item;//当前元素

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    private Node<E> first;//头结点
    private Node<E> last;//尾结点
    public int size;//元素个数

    public MyLinkedList() {
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return node(index).item;
    }

    /**
     * 在末尾添加
     *
     * @param e
     */
    public void add(E e) {
        linkLast(e);
    }

    /**
     * 在指定位置添加
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        //如果index不在size范围内则return掉
        if (index < 0 || index > size) {
            return;
        }
        if (index == size) {//index == size，则往末尾添加
            linkLast(e);
        } else {
            Node<E> target = node(index);//找出当前index处的结点
            Node<E> prev = target.prev;
            Node<E> newNode = new Node<>(prev, e, target);
            if (prev == null) {//添加到第一个，即index为0
                first = newNode;
                target.prev = newNode;
            } else {//添加到中间
                prev.next = newNode;
                target.prev = newNode;
            }
            size++;
        }
    }

    /**
     * 删除指定位置元素
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }
        Node<E> target = node(index);//定位找到index处的结点
        Node<E> prev = target.prev;
        Node<E> next = target.next;
        if (prev == null) {//如果index处结点的前一结点为null(即index = 0，第一个元素)
            first = next;//将头结点置为target的后一结点
        } else {//如果index处结点的前一结点不为null
            prev.next = next;//将target的前一结点的next结点置为target的后一结点
        }
        if (next == null) {//如果index处结点的后一结点为null(即index = size - 1，最后一个元素)
            last = prev;//将尾结点置为target的前一结点
        } else {//如果index处结点的后一结点不为null
            next.prev = prev;//将target的后一结点的prev结点置为target的前一结点
        }
        size--;
    }

    /**
     * 定位返回index处的结点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        if (index < size / 2) {//二分法查找，如果index在前半部分，则从前往后查找
            Node<E> node = first;//初始为first，往后遍历直到index处
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {//如果index在后半部分，则从后往前查找
            Node<E> node = last;//初始为last，往前遍历直到index处
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    /**
     * 添加元素到末尾
     *
     * @param e
     */
    private void linkLast(E e) {
        Node<E> newNode = new Node<>(last, e, null);//新建新结点，并指明新结点的前一结点和后一结点
        if (last == null) {
            //如果当前尾结点为null(即没有任何元素的情况),把新结点赋值给头结点，此时该新添加的元素为第一个元素，头尾结点重合)
            first = newNode;
        } else {
            //尾结点不为null,往后添加新元素，并将当前尾结点的next指向新结点
            last.next = newNode;
        }
        last = newNode;//最后新增的结点变为尾结点
        size++;//元素个数递增
    }
}

