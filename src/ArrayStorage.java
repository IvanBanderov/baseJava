/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size();

    }

    Resume get(String uuid) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (checkStorage(i, uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (checkStorage(i, uuid)) {
                    storage[i] = null;
                }
            }
            size();
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i] != null) {
                    resumes[i] = storage[i];
                }
            }
            return resumes;
        }
        return new Resume[0];
    }

    int size() {
        size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }

    private boolean checkStorage(int index, String uuid) {
        return storage[index] != null && storage[index].uuid.equals(uuid);
    }
}
