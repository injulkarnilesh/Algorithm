package in.algorithm.course.part.one.week.four.priorityqueue;

import in.algorithm.course.part.one.week.four.heap.application.BinaryHeapMaxPriorityQueue;

public class PriorityQueueFactory {

    public static <T extends Comparable> PriorityQueue<T> newUnOrderedMinPriorityQueue() {
        return new UnOrderedPriorityQueue<>(FIRST_SMALLER());
    }

    public static <T extends Comparable> PriorityQueue<T> newUnOrderedMaxPriorityQueue() {
        return new UnOrderedPriorityQueue<>(FIRST_LARGER());
    }

    public static <T extends Comparable> PriorityQueue<T> newOrderedMinPriorityQueue() {
        return new OrderedPriorityQueue<>(FIRST_LARGER());
    }

    public static <T extends Comparable> PriorityQueue<T> newOrderedMaxPriorityQueue() {
        return new OrderedPriorityQueue<>(FIRST_SMALLER());
    }

    public static <T extends Comparable> PriorityQueue<T> newBinaryHeapPriorityQueue(final int capacity) {
        return new BinaryHeapMaxPriorityQueue<>(capacity);
    }

    public static <T extends Comparable> OrderDecider<T> FIRST_SMALLER() {
      return new OrderDecider<T>() {
          @Override
          public boolean isValid(final Node<T> first, final Node<T> second) {
              return first.getValue().compareTo(second.getValue()) < 0;
          }
      };
    }

    public static <T extends Comparable> OrderDecider<T> FIRST_LARGER() {
        return new OrderDecider<T>() {
            @Override
            public boolean isValid(final Node<T> first, final Node<T> second) {
                return first.getValue().compareTo(second.getValue()) > 0;
            }
        };
    }

}
