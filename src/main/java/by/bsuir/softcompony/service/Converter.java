package by.bsuir.softcompony.service;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static <T> List<T> iterableToList(Iterable<T> sendedList) {
        List<T> list = new ArrayList<>();
        sendedList.forEach(list::add);
        return list;
    }
}
