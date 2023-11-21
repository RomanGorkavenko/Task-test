package ru.t1.test_task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Тестовое задание.
 * Спроектировать(продумать формат и ограничения входящих/исходящих параметров) и реализовать REST API,
 * вычисляющее частоту встречи символов по заданной строке. Результат должен быть отсортирован по убыванию
 * количества вхождений символа в заданную строку.
 */
@RestController
public class ControllerRLE {

    /**
     * Метод принимает на вход строку, преобразует в массив символов.
     * Затем циклом перебирается полученный массив символов, добавляя в список в виде строки,
     * с помощью метода{@link ControllerRLE#pack(char, int)}, символ и количество его вхождений.
     * Затем список сортируется путем получения с помощью Stream API значений типа {@link Integer}
     * (количество вхождений символа в строку), и сравнивает их.
     * @param string строка
     * @return отформатированную строку согласно ТЗ
     */
    @PostMapping("/rle")
    public ResponseEntity<String> rle(@RequestBody String string) {
        char[] chars = string.toCharArray();
        char lastSym = chars[0];
        int lastPos = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != lastSym) {
                list.add(pack(lastSym, i - lastPos));
                lastPos = i;
                lastSym = chars[i];
            }
        }
        list.add(pack(chars[lastPos], chars.length - lastPos));
        list.sort((o1, o2) -> Arrays.stream(o2.split(" ")).skip(1L).mapToInt(Integer::parseInt).findFirst().getAsInt()
                - Arrays.stream(o1.split(" ")).skip(1L).mapToInt(Integer::parseInt).findFirst().getAsInt());
        return ResponseEntity.ok(String.join(", ", list));
    }

    /**
     * Метод собирает строку нужного формата.
     * @param c символ строки переданной в метод {@link ControllerRLE#rle(String)}
     * @param count количество вхождения символа в строку, переданную в метод {@link ControllerRLE#rle(String)}
     * @return отформатированную строку
     */
    private String pack(char c, int count) {
        String s = String.valueOf(c);
        s = "\"" + s + "\"" + ": " + count;
        return s;
    }

}
