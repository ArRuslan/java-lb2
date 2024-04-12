package ua.nure.jfm.task2;

public class StreamImpl<T> implements Stream<T> {
    private Container<? extends T> container;

    public StreamImpl(Container<? extends T> container) {
        this.container = container;
    }

    @Override
    public Stream<T> filter(Function<? super T, Boolean> predicate) {
        Array<T> filtered = new ArrayImpl<>();
        for (T element : container) {
            if (predicate.apply(element)) {
                filtered.add(element);
            }
        }

        return new StreamImpl<>(filtered);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> function) {
        Array<R> mapped = new ArrayImpl<>();
        for (T element : container) {
            mapped.add(function.apply(element));
        }

        return new StreamImpl<>(mapped);
    }

    @Override
    public int count() {
        return container.size();
    }

    @Override
    public void forEach(Action<? super T> action) {
        for (T element : container) {
            action.perform(element);
        }
    }

}
