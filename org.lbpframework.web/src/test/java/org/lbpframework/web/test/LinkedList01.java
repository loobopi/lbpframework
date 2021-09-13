package org.lbpframework.web.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList01 implements List {


    private Node head;
    private Node tail;
    int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object object) {
        final Node t = tail;
        Node newNode = new Node(null,t,object);
        tail = newNode;
        if(t == null){
            head = newNode;
        }else{
            t.next = newNode;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node Node = head;
        while(Node != null){
            Object target = Node.value;
            if(!(target.getClass() == o.getClass())){
                Node = Node.next;
                continue;
            }
            if(target.equals(o)){
                if(Node == head){
                    head = head.next;
                    head.prev = null;
                }else{
                    Node prev = Node.prev;
                    prev.next = Node.next;
                }
                return true;
            }
            Node = Node.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuffer buffer = new StringBuffer("[");
        while(node != null){
            buffer.append(node.value);
            if(node.next!=null){
                buffer.append(",");
            }
            node = node.next;
        }
        return String.valueOf(buffer.append("]"));
    }

    class Node{
        Node next;
        Node prev;
        Object value;

        public Node() {
        }

        public Node(Node next, Node prev, Object value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
}
