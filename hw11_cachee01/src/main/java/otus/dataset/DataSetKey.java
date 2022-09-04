package otus.dataset;

public class DataSetKey {
    private final Class<?> type;
    private final Long id;

    public <T> DataSetKey(Class<T> type, Long id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataSetKey)) {
            return false;
        }
        DataSetKey other = (DataSetKey) obj;
        return type.equals(other.type) && id.equals(other.id);
    }
}
