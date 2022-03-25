package util.collection;

import java.util.Iterator;

/**
 * @author v_vyqyxiong
 */
public class IterableExample {

    public static void main(String[] args) {
        for (Integer i : new IntRange(1, 10)) {
            System.out.println(i);
        }
    }

    public record IntRange(int start, int end) implements Iterable<Integer> {

        @Override
        public Iterator<Integer> iterator() {
            return new Itr();
        }

        public class Itr implements Iterator<Integer> {

            private int current;

            public Itr() {
                // 这里会发生越界 Integer.MIN_VALUE - 1
                this.current = start - 1;
            }

            @Override
            public boolean hasNext() {
                return current < end;
            }

            @Override
            public Integer next() {
                return ++current;
            }
        }
    }

}
