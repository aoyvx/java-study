package util.collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author xay
 * 2022/3/26
 */
public class ListExample {

    public static void main(String[] args) {
        SimpleList<Integer> simpleList = new SimpleList<>();
        simpleList.add(1);
        simpleList.add(2);
        simpleList.add(3);
        simpleList.add(4);
        System.out.println(Arrays.toString(simpleList.toArray()));
        simpleList.set(2, 2);
        System.out.println(Arrays.toString(simpleList.toArray()));
        simpleList.remove(2);
        System.out.println(Arrays.toString(simpleList.toArray()));
        System.out.println(simpleList.size);
    }

    public static class SimpleList<E> extends AbstractList<E> implements List<E> {

        private Node<E> head;

        private int size;

        @Override
        public E get(int index) {
            checkIndex(index);
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public E set(int index, E element) {
            checkIndex(index);
            Node<E> pre = null;
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                pre = node;
                node = node.next;
            }
            Node<E> cur = new Node<>(element);
            if (pre != null) {
                pre.next = cur;
            } else {
                head = cur;
            }
            cur.next = node.next;
            return node.data;
        }

        @Override
        public void add(int index, E element) {
            if (index > size || index < 0)
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            Node<E> pre = index > 0 ? head : null;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            Node<E> cur = new Node<>(element);
            if (pre != null) {
                cur.next = pre.next;
                pre.next = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        @Override
        public E remove(int index) {
            Node<E> pre = null;
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                pre = node;
                node = node.next;
            }

            if (pre != null) {
                pre.next = node.next;
            } else {
                head = node.next;
            }
            size--;
            return node.data;
        }

        private void checkIndex(int index) {
            Objects.checkIndex(index, size);
        }
    }

    public static class Node<E> {
        final E data;

        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

    }
}
