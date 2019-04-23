Написать свою реализацию ArrayList на основе массива.
class DIYarrayList<T> implements List<T>{...}

Проверить, что на ней работают методы из java.util.Collections:
Collections.addAll(Collection<? super T> c, T... elements)
Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
Collections.static <T> void sort(List<T> list, Comparator<? super T> c)