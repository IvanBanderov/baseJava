package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                break;
        }
        if (size - 1 - i >= 0) {
            System.arraycopy(storage, i + 1, storage, i, size - i);
            size--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        if (size >= 0) {
            System.arraycopy(storage, 0, resumes, 0, size);
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
