package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private static int notExistIndex = -1;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (!isResumeExist(r.getUuid()) && size < storage.length) {
            System.out.println("Resume " + r.toString() + " was saved" );
            storage[size] = r;
            size++;
        } else {
            System.out.println("Error while save resume " + r.getUuid());
        }

    }

    public Resume get(String uuid) {
        int index = getResumeIndexByUuid(uuid);
        if (index != notExistIndex) {
            return storage[index];
        } else {
            System.out.println("Error while get resume. Not found resume with uuid " + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        if (isResumeExist(uuid)) {
            int i;
            for (i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) break;
            }
            if (size - 1 - i >= 0) {
                System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
            }
            System.out.println("Resume " + uuid + " was deleted");
        } else {
            System.out.println("Error while delete resume. Not found resume with uuid " + uuid);
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

    public void update(Resume r) {
        int index = getResumeIndexByUuid(r.getUuid());
        if (index != notExistIndex) {
            storage[index] = r;
            System.out.println("Resume " + r.toString() + " was updated " );
        } else {
            System.out.println("Error while update resume. Not found resume with uuid " + r.getUuid());
        }
    }

    public int size() {
        return size;
    }

    private int getResumeIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return notExistIndex;
    }

    /**
     * @return true, if resume exist in storage
     */
    private boolean isResumeExist(String uuid) {
        int index = getResumeIndexByUuid(uuid);
        return index != notExistIndex;
    }
}
