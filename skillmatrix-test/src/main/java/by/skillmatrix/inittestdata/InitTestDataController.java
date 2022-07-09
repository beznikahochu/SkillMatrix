package by.skillmatrix.inittestdata;

import java.util.List;

//TODO ПЕРЕДЕЛАТЬ НА адстрактный класс
public interface InitTestDataController<T> {
    void initTestData(int count);
    List<T> getInitTestData();
    void clear();
}
