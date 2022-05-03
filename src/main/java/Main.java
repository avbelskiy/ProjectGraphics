import app.Application;
import io.github.humbleui.jwm.App;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Здесь начинается графика
        App.start(Application::new);

        /*
        Algorithm mas = new Algorithm();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер алгоритма: ");
        mas.number_alg = sc.nextInt();
        if (mas.number_alg < 1 || mas.number_alg > 9) { System.out.println("Алгоритм с таким номером не существует.");}
        else {
            System.out.println("Введите длину массива: ");
            int n = sc.nextInt();
            mas.array = new int[n];
            int[] arr = new int[n];
            System.out.println("Вы хотите ввести массив самостоятельно? да/нет : 1/0 (если нет, то он сгенерируется рандомом от -100 до 100): ");
            int v = sc.nextInt();
            if (v==1) {
                System.out.println("Введите сам массив (числа через пробел): ");
                for (int i = 0; i < n; i++) {
                    mas.array[i] = sc.nextInt();
                }
            }
            else if (v==0) {
                for (int i = 0; i < n; i++) {
                    Random r = new Random();
                    int rr=r.nextInt(200)-100;
                    mas.array[i] = rr;
                    arr[i] = mas.array[i];
                }
                System.out.println("Вот сгенерированный массив: " + Arrays.toString(arr));
            }
            else System.out.println("Перечитайте предыдущую строку.");
            if (v==1 || v==0) {
                long lStartTime = System.nanoTime();
                arr=mas.sort_array();
                long lEndTime = System.nanoTime();
                System.out.println("Отсортированный массив: " + Arrays.toString(arr));
                System.out.println("Наносекунд на сортировку затрачено: " + (lEndTime - lStartTime));
            }
        }
        */
    }
}
