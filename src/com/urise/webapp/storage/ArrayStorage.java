package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int foundIndex = getResumeIndexByUuid(resume.getUuid());
        if (foundIndex == -1) {
            System.out.println("Error while save resume. Not found resume with uuid " + resume.getUuid());
        } else if (size == storage.length) {
            System.out.println("Error while save resume. Too many resumes " + resume.getUuid());
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int foundIndex = getResumeIndexByUuid(uuid);
        if (foundIndex > -1) {
            return storage[foundIndex];
        }
        System.out.println("Error while get resume. Not found resume with uuid " + uuid);
        return null;

    }

    public void delete(String uuid) {
        int foundIndex = getResumeIndexByUuid(uuid);
        if (foundIndex > -1) {
            System.arraycopy(storage, foundIndex + 1, storage, foundIndex, size - foundIndex);
            size--;
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

    public void update(Resume resume) {
        int foundIndex = getResumeIndexByUuid(resume.getUuid());
        if (foundIndex > -1) {
            storage[foundIndex] = resume;
            System.out.println("Resume " + resume.toString() + " was updated " );
        } else {
            System.out.println("Error while update resume. Not found resume with uuid " + resume.getUuid());
        }
    }

    public int size() {
        return size;
    }

    /**
     * @return founded index, if resume not exist in storage return -1
     */
    private int getResumeIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}
