package com.sugno.arithmetic.lru;

import java.util.HashMap;

/**
 * Lru算法
 */
public class LRUCache {
    private Node head;
    private Node end;

    private HashMap<String,Node> hashMap;
    private int limit;  //缓存上限

    public LRUCache(int limit){
        this.limit = limit;
        this.hashMap = new HashMap<String,Node>();
    }

    //向链表中添加节点
    public void addNode (Node node){
        if (head == null){  //空链
            head = node;
        }

        if(end != null) {  //节点添加到尾部
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
    }

    //从链表中移除节点
    public String  removeNode(Node node){
        if(head == end && node == head){  //链表只有一个节点
            head = null;
            end = null;
        }else if (node == end){ //node为最后一个节点
            end.pre.next = null;
            end = end.pre;
        }else if (node == head){  //node为第一个节点
            head.next.pre = null;
            head = head.next;
        }else {  //node为中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    //刷新链表，将最近使用的节点放置到链表末尾
    public void refreshNode(Node node){
        if (node == end){
            return;
        }
        removeNode(node);  //删除节点
        addNode(node); //添加节点
    }

    //根据节点的key值，获取链表中该节点的value值
    public String get(String key){
        Node node = hashMap.get(key);
        if (node == null){
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    //向链表上添加key-value
    public void put(String key, String value){
        Node node = hashMap.get(key);
        if (node == null){
            if (hashMap.size() >= limit){
                String oldKey = removeNode(head);  //删除节点
                hashMap.remove(oldKey);  //同时需要把节点的key也从hashmap中删除
            }
            node = new Node(key,value);
            addNode(node);
            hashMap.put(key,node);
        }else {
            node.value = value;
            refreshNode(node);
        }
    }

    //从链表上删除指定key的数据
    public void remove(String key){
        Node node = hashMap.get(key);
        if (node == null){
            return ;
        }
        removeNode(node);
        hashMap.remove(key);
    }

    class Node {
        public Node next;
        public Node pre;
        public String key;
        public String value;
        Node(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String getNextKey() {
            return next.getKey();
        }

        public String getPreKey() {
            return pre.getKey();
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(10);
        lruCache.put("001","用户1信息");
        lruCache.put("002","用户2信息");
        lruCache.put("003","用户3信息");
        lruCache.put("004","用户4信息");
        lruCache.get("003");
        System.out.println("Now End Node Key is: " + lruCache.end.getKey()+ ",Value is: " +lruCache.end.getValue());
        lruCache.put("002","用户2信息更新");
        System.out.println("Now End Node Key is: " + lruCache.end.getKey() + ",Value is: " +lruCache.end.getValue() );
        lruCache.put("006","用户6信息");
        System.out.println("Now End Node Key is: " + lruCache.end.getKey()+ ",Value is: " +lruCache.end.getValue());
    }
}
