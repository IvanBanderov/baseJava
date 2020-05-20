/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size();
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (checkStorage(i, uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++)
        {
            if (storage[i].uuid.equals(uuid))
            {
                Resume[] copyStorage = new Resume[10000];
                System.arraycopy(storage, 0, copyStorage, 0, i);
                System.arraycopy(storage, i+1, copyStorage, i, size-i-1);
                storage = copyStorage;
                break;
            }
        }
        size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        if (size >= 0) {
            System.arraycopy(storage, 0, resumes, 0, size);
        }
        return resumes;
    }

    int size() {
        size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) size++;
            if (storage[i+1] == null) break;
        }
        return size;
    }

    private boolean checkStorage(int index, String uuid) {
        return storage[index].uuid.equals(uuid);
    }
}
