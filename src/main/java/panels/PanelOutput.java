package panels;

import app.Algorithm;
import controls.Button;
import controls.Input;
import controls.Label;
import io.github.humbleui.jwm.*;
import io.github.humbleui.skija.Canvas;
import misc.CoordinateSystem2i;
import misc.TimSort;
import misc.Vector2i;
import controls.MultiLineLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.Application.PANEL_PADDING;
import static app.Colors.BUTTON_COLOR;
import static panels.PanelInput.array;

/**
 * Панель вывода
 */
public class PanelOutput extends GridPanel {
    /**
     * Заголовки
     */
    public List<Label> labels;
    public List<MultiLineLabel> multiLineLabels;
    /**
     * Поля ввода
     */
    public List<Input> inputs;
    /**
     * Кнопки
     */
    public List<Button> buttons;

    /**
     * Панель управления
     *
     * @param window     окно
     * @param drawBG     флаг, нужно ли рисовать подложку
     * @param color      цвет подложки
     * @param padding    отступы
     * @param gridWidth  кол-во ячеек сетки по ширине
     * @param gridHeight кол-во ячеек сетки по высоте
     * @param gridX      координата в сетке x
     * @param gridY      координата в сетке y
     * @param colspan    кол-во колонок, занимаемых панелью
     * @param rowspan    кол-во строк, занимаемых панелью
     */
    public PanelOutput(
            Window window, boolean drawBG, int color, int padding, int gridWidth, int gridHeight,
            int gridX, int gridY, int colspan, int rowspan
    ) {
        super(window, drawBG, color, padding, gridWidth, gridHeight, gridX, gridY, colspan, rowspan);

        // создаём списки
        inputs = new ArrayList<>();
        labels = new ArrayList<>();
        multiLineLabels = new ArrayList<>();
        buttons = new ArrayList<>();

        MultiLineLabel rLabel = new MultiLineLabel(window, true, backgroundColor, PANEL_PADDING,
                5, 12, 0, 0, 4, 1, "Правильность сортировки сравнивается с методом пузырька", true, true);
        multiLineLabels.add(rLabel);

        Button i1 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 0, 10, 1, 1, "(i) Метод\nпузырька", true, true);
        i1.setOnClick(() -> PanelInfo.show("""
                Алгоритм состоит из повторяющихся проходов по сортируемому массиву. За каждый проход элементы последовательно
                 сравниваются попарно и, если порядок в паре неверный, выполняется обмен элементов.
                Проходы по массиву повторяются N-1 раз или до тех пор, пока на очередном проходе не окажется,
                 что обмены больше не нужны, что означает — массив отсортирован. При каждом проходе алгоритма по внутреннему циклу,
                 очередной наибольший элемент массива ставится на своё место в конце массива рядом с предыдущим «наибольшим элементом»,
                 а наименьший элемент перемещается на одну позицию к началу массива
                («всплывает» до нужной позиции, как пузырёк в воде, отсюда и название алгоритма)."""));
        buttons.add(i1);

        Button i2 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 1, 10, 1, 1, "(i) Метод\nвыбора", true, true);
        i2.setOnClick(() -> PanelInfo.show("""
                Проходим по массиву в поисках максимального элемента. Найденный максимум меняем местами с последним элементом.
                 Неотсортированная часть массива уменьшилась на один элемент (не включает последний элемент,
                 куда мы переставили найденный максимум). К этой неотсортированной части применяем те же действия—
                 — находим максимум и ставим его на последнее место в неотсортированной части массива. И так продолжаем
                 до тех пор, пока неотсортированная часть массива не уменьшится до одного элемента."""));
        buttons.add(i2);

        Button i3 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 2, 10, 1, 1, "(i) Метод\nвставок", true, true);
        i3.setOnClick(() -> PanelInfo.show("""
                Проходим по массиву слева направо и обрабатываем по очереди каждый элемент.Слева от очередного элемента наращиваем
                 отсортированную часть массива, справа по мере процесса потихоньку испаряется неотсортированная. В отсортированной
                 части массива ищется точка вставки для очередного элемента. Сам элемент отправляется в буфер, в результате чего в
                  массиве появляется свободная ячейка — это позволяет сдвинуть элементы и освободить точку вставки."""));
        buttons.add(i3);

        Button i4 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 3, 10, 1, 1, "(i) Метод\nшаттла", true, true);
        i4.setOnClick(() -> PanelInfo.show("""
                Точная документация отсутствует.
                """));
        buttons.add(i4);

        Button i5 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 4, 10, 1, 1, "(i) Метод\nШелла", true, true);
        i5.setOnClick(() -> PanelInfo.show("""
                Идея метода заключается в сравнение разделенных на группы элементов последовательности, находящихся друг от друга
                 на некотором расстоянии. Изначально это расстояние равно d или N/2, где N — общее число элементов. На первом шаге
                  каждая группа включает в себя два элемента расположенных друг от друга на расстоянии N/2; они сравниваются между собой,
                   и, в случае необходимости, меняются местами. На последующих шагах также происходят проверка и обмен, но расстояние d
                    сокращается на d/2, и количество групп, соответственно, уменьшается. Постепенно расстояние между элементами уменьшается,
                     и на d=1 проход по массиву происходит в последний раз.
                """));
        buttons.add(i5);

        Button i6 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 0, 11, 1, 1, "(i) Метод\nбыстрой с.", true, true);
        i6.setOnClick(() -> PanelInfo.show("""
                Алгоритм быстрой сортировки является рекурсивным, поэтому для простоты процедура на вход будет принимать границы участка массива
                 от l включительно и до r не включительно. Понятно, что для того, чтобы отсортировать весь массив, в качестве параметра l надо
                  передать 0, а в качестве r — n, где по традиции n обозначает длину массива.
                В основе алгоритма быстрой сортировке лежит процедура partition. Partition выбирает некоторый элемент массива и переставляет
                 элементы участка массива таким образом, чтобы массив разбился на 2 части: левая часть содержит элементы, которые меньше этого
                  элемента, а правая часть содержит элементы, которые больше или равны этого элемента. Такой разделяющий элемент называется пивотом.
                """));
        buttons.add(i6);

        Button i7 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 1, 11, 1, 1, "(i) Метод\nперемеш.", true, true);
        i7.setOnClick(() -> PanelInfo.show("""
                Второй вариант названия — двунаправленная пузырьковая сортировка — наиболее точно описывает процесс работы алгоритма. Здесь, в его название,
                 довольно-таки удачно включен термин «пузырьковая». Это действительно альтернативная версия известного метода, модификации в котором заключаются,
                  по большей части, в реализации, упомянутой в названии, двунаправленности: алгоритм перемещается, ни как в обменной (пузырьковой)
                   сортировке – строго снизу вверх (слева направо), а сначала снизу вверх, потом сверху вниз.
                """));
        buttons.add(i7);

        Button i8 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 2, 11, 1, 1, "(i) Метод\nTimSort", true, true);
        i8.setOnClick(() -> PanelInfo.show("""
                Timsort  — гибридный алгоритм сортировки, сочетающий сортировку вставками и сортировку слиянием, опубликованный в 2002 году  Тимом Петерсоном.
                 Основная идея алгоритма в том, что в реальном мире сортируемые массивы данных часто содержат в себе упорядоченные подмассивы. На таких данных Timsort
                  существенно быстрее многих алгоритмов сортировки.  По специальному алгоритму входной массив разделяется на подмассивы. Каждый подмассив
                   сортируется сортировкой вставками. Отсортированные подмассивы собираются в единый массив с помощью модифицированной сортировки слиянием.
                """));
        buttons.add(i8);

        Button i9 = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 3, 11, 1, 1, "(i) Метод\nподсчёта", true, true);
        i9.setOnClick(() -> PanelInfo.show("""
                Сортировка подсчетом — простейший способ упорядочить массив за линейное время. Применять его можно только для целых чисел, небольшого диапазона,
                 т.к. он требует O(M) дополнительной памяти, где M — ширина диапазона сортируемых чисел. Алгоритм особо эффективен когда мы сортируем большое количество
                  чисел, значения которых имеют небольшой разброс — например: массив из 1000000 целых чисел, которые принимают значения от 0 до 1000.
                  
                """));
        buttons.add(i9);

        Button dev = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 4, 11, 1, 1, "Справка", true, true);
        dev.setOnClick(() -> PanelInfo.show("Разработчик - Бельский Антон, 10-3\n2022 год\nv 1.0"));
        buttons.add(dev);

        Button start = new Button(window, true, BUTTON_COLOR, PANEL_PADDING,
                5, 12, 4, 0, 1, 1, "Начать\nсортировку", true, true);

        start.setOnClick(() -> {
            if (array == null) {
                PanelLog.warning("Массив ещё не создан");
            } else {
                PanelLog.success("Массив отсортирован");

                int[] array_bubble = array.clone();
                int[] array_choose = array.clone();
                int[] array_paste = array.clone();
                int[] array_shuttle = array.clone();
                int[] array_shell = array.clone();
                int[] array_quick = array.clone();
                int[] array_swap = array.clone();
                int[] array_timsort = array.clone();
                int[] array_count = array.clone();
                labels.clear();


                long lStartTime = System.nanoTime();
                Algorithm.bubble_sort(array_bubble);
                long lEndTime = System.nanoTime();
                Label bubble_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 1, 1, 1, "Правильно по умолчанию", true, true);
                labels.add(bubble_Label);
                Label bubbleTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 1, 2, 1, "Пузырьком наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(bubbleTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.choose_sort(array_choose);
                lEndTime = System.nanoTime();
                String text;
                if (Arrays.equals(array_choose, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label choose_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 2, 1, 1, text, true, true);
                labels.add(choose_Label);
                Label chooseTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 2, 2, 1, "Выбором наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(chooseTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.paste_sort(array_paste);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_paste, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label paste_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 3, 1, 1, text, true, true);
                labels.add(paste_Label);
                Label pasteTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 3, 2, 1, "Вставками наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(pasteTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.shuttle_sort(array_shuttle);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_shuttle, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label shuttle_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 4, 1, 1, text, true, true);
                labels.add(shuttle_Label);
                Label shuttleTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 4, 2, 1, "Шаттлом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(shuttleTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.shell_sort(array_shell);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_shell, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label shell_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 5, 1, 1, text, true, true);
                labels.add(shell_Label);
                Label shellTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 5, 2, 1, "Шеллом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(shellTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.quickSort(array_quick, 0, array_quick.length - 1);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_quick, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label quick_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 6, 1, 1, text, true, true);
                labels.add(quick_Label);
                Label quickTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 6, 2, 1, "Быстрой с. наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(quickTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.swap_sort(array_swap);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_swap, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label swap_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 7, 1, 1, text, true, true);
                labels.add(swap_Label);
                Label swapTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 7, 2, 1, "Перемешиванием наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(swapTime_Label);


                lStartTime = System.nanoTime();
                TimSort.timSort(array_timsort, array_timsort.length);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_timsort, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label bucket_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 8, 1, 1, text, true, true);
                labels.add(bucket_Label);
                Label TimSortTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 8, 2, 1, "TimSort, наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(TimSortTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.count_sort(array_count);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_count, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label count_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 0, 9, 1, 1, text, true, true);
                labels.add(count_Label);
                Label countTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 12, 1, 9, 2, 1, "Подсчетом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(countTime_Label);
            }
        });
        buttons.add(start);
    }

    /**
     * Обработчик событий
     *
     * @param e событие
     */
    @Override
    public void accept(Event e) {
        // вызываем обработчик предка
        super.accept(e);
        // событие движения мыши
        if (e instanceof EventMouseMove ee) {
            for (Input input : inputs)
                input.accept(ee);

            for (Button button : buttons) {
                if (lastWindowCS != null)
                    button.checkOver(lastWindowCS.getRelativePos(new Vector2i(ee)));
            }
            // событие нажатия мыши
        } else if (e instanceof EventMouseButton) {
            if (!lastInside)
                return;

            Vector2i relPos = lastWindowCS.getRelativePos(lastMove);

            // пробуем кликнуть по всем кнопкам
            for (Button button : buttons) {
                button.click(relPos);
            }

            // перебираем поля ввода
            for (Input input : inputs) {
                // если клик внутри этого поля
                if (input.contains(relPos)) {
                    // переводим фокус на это поле ввода
                    input.setFocus();
                }
            }
            // перерисовываем окно
            window.requestFrame();
            // обработчик ввода текста
        } else if (e instanceof EventTextInput ee) {
            for (Input input : inputs) {
                if (input.isFocused()) {
                    input.accept(ee);
                }
            }
            // перерисовываем окно
            window.requestFrame();
            // обработчик ввода клавиш
        } else if (e instanceof EventKey ee) {
            for (Input input : inputs) {
                if (input.isFocused()) {
                    input.accept(ee);
                }
            }
            // перерисовываем окно
            window.requestFrame();
        }
    }

    /**
     * Метод под рисование в конкретной реализации
     *
     * @param canvas   область рисования
     * @param windowCS СК окна
     */
    @Override
    public void paintImpl(Canvas canvas, CoordinateSystem2i windowCS) {
        // выводим кнопки
        for (Button button : buttons) {
            button.paint(canvas, windowCS);
        }
        // выводим поля ввода
        for (Input input : inputs) {
            input.paint(canvas, windowCS);
        }
        // выводим поля вывода
        for (Label label : labels) {
            label.paint(canvas, windowCS);
        }

        for (MultiLineLabel multiLineLabel : multiLineLabels) {
            multiLineLabel.paint(canvas, windowCS);
        }
    }
}