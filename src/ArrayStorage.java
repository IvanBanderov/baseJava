/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        storage = new Resume[10000];
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                if (storage[i] != null && storage[i].uuid.equals(uuid)) return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (size() > 0) {
            for (int i = 0; size() > 0; i++) {
                if (storage[i] != null && storage[i].uuid.equals(uuid)) storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] temp = new Resume[size()];
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                if (storage[i] != null) temp[i] = storage[i];
            }
            return temp;
        }
        return new Resume[0];
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }
}
