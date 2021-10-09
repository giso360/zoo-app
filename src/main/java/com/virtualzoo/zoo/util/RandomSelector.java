package com.virtualzoo.zoo.util;

import java.util.List;
import java.util.Random;

public class RandomSelector {

    public static String getRandomStringFromList(List<String> stringList) {
        int max = stringList.size();
        Random random = new Random();
        return stringList.get(random.ints(0, max).findFirst().getAsInt());
    }

}
