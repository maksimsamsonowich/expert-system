import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static List<String> SETTINGS = new ArrayList<>();

    private static List<String> OBJECTS = new ArrayList<>();

    private static HashMap<Integer, List<Integer>> MATCHES = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println(Basics.MENU);
            Integer choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1: {
                    startTraining();

                    break;
                }
                case 2: {
                    createObject();

                    break;
                }
                case 3: {
                    play();

                    break;
                }
                default: {
                    System.out.println("Попробуй еще раз");
                }
            }
        }
    }

    public static void createObject() {
        System.out.println(Basics.NUM_OF_SETTINGS);
        Integer num = new Scanner(System.in).nextInt();

        for (int i = 1; i <= num; i++) {
            System.out.printf((Basics.ENTER_SETTING) + "%n", i);
            SETTINGS.add(new Scanner(System.in).nextLine());
        }

        for (int i = 1; i <= num; i++) {
            System.out.printf((Basics.ENTER_OBJECT) + "%n", i);
            OBJECTS.add(new Scanner(System.in).nextLine());
            MATCHES.put(i - 1, new ArrayList<>());
        }
    }

    public static void startTraining() {
        for (int o = 0; o < OBJECTS.size(); o++) {
            for (int i = 0; i < SETTINGS.size(); i++) {
                System.out.printf((Basics.QUESTION) + "%n", OBJECTS.get(o), SETTINGS.get(i));
                System.out.println(Basics.CHOICE);
                Integer answer = new Scanner(System.in).nextInt();
                if (answer == 1) {
                    MATCHES.get(o).add(i);
                }
            }
        }
    }

    public static void play() {
        HashMap<Integer, List<Integer>> temObjectsMap = new HashMap<>(MATCHES);

        for (int i = 0; i < SETTINGS.size(); i++) {
            System.out.printf((Basics.OBJECT_QUESTION) + "%n", SETTINGS.get(i));
            System.out.println(Basics.CHOICE);
            Integer answer = new Scanner(System.in).nextInt();

            if (answer == 1) {
                List<Integer> toRemove = new ArrayList<>();

                int finalI = i;
                temObjectsMap.forEach((value, matches) -> {
                    if (!matches.contains(finalI)) {
                        toRemove.add(value);
                    }
                });
                toRemove.forEach(temObjectsMap::remove);
                toRemove.clear();
            } else {
                List<Integer> toRemove = new ArrayList<>();

                int finalI = i;
                temObjectsMap.forEach((value, matches) -> {
                    if (matches.contains(finalI)) {
                        toRemove.add(value);
                    }
                });
                toRemove.forEach(temObjectsMap::remove);
                toRemove.clear();
            }

            if (temObjectsMap.size() == 1) {
                System.out.println("Вы загадали " + OBJECTS.get(temObjectsMap.keySet().stream().findFirst().get()));
                return;
            }

            if (temObjectsMap.size() == 0) {
                System.out.println("Я такого еще не знаю :(");
                return;
            }
        }
    }

}