package org.mmontilla.hollyworkdays.repository.helper;

public interface DateGenerator<T> {
    T getDate(int yearOf);
}