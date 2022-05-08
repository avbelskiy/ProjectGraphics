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
import static app.Colors.APP_BACKGROUND_COLOR;
import static app.Colors.PANEL_BACKGROUND_COLOR;
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
                4, 10, 0, 0, 3, 1, "Правильность сортировки сравнивается с методом пузырька", true, true);
        multiLineLabels.add(rLabel);

        Button start = new Button(window, true, backgroundColor, PANEL_PADDING,
                4, 10, 3, 0, 1, 1, "Начать\nсортировку", true, true);

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
                        3, 10, 0, 1, 1, 1, "Правильно по умолчанию", true, true);
                labels.add(bubble_Label);
                Label bubbleTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 1, 2, 1, "Пузырьком наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(bubbleTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.choose_sort(array_choose);
                lEndTime = System.nanoTime();
                String text;
                if (Arrays.equals(array_choose, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label choose_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 2, 1, 1, text, true, true);
                labels.add(choose_Label);
                Label chooseTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 2, 2, 1, "Выбором наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(chooseTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.paste_sort(array_paste);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_paste, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label paste_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 3, 1, 1, text, true, true);
                labels.add(paste_Label);
                Label pasteTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 3, 2, 1, "Вставками наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(pasteTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.shuttle_sort(array_shuttle);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_shuttle, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label shuttle_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 4, 1, 1, text, true, true);
                labels.add(shuttle_Label);
                Label shuttleTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 4, 2, 1, "Шаттлом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(shuttleTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.shell_sort(array_shell);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_shell, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label shell_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 5, 1, 1, text, true, true);
                labels.add(shell_Label);
                Label shellTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 5, 2, 1, "Шеллом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(shellTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.quickSort(array_quick, 0, array_quick.length - 1);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_quick, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label quick_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 6, 1, 1, text, true, true);
                labels.add(quick_Label);
                Label quickTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 6, 2, 1, "Быстрым наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(quickTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.swap_sort(array_swap);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_swap, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label swap_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 7, 1, 1, text, true, true);
                labels.add(swap_Label);
                Label swapTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 7, 2, 1, "Перемешиванием наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(swapTime_Label);


                lStartTime = System.nanoTime();
                TimSort.timSort(array_timsort, array_timsort.length);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_timsort, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label bucket_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 8, 1, 1, text, true, true);
                labels.add(bucket_Label);
                Label TimSortTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 8, 2, 1, "TimSort, наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
                labels.add(TimSortTime_Label);


                lStartTime = System.nanoTime();
                Algorithm.count_sort(array_count);
                lEndTime = System.nanoTime();
                if (Arrays.equals(array_count, array_bubble)) {
                    text = "Правильно";
                } else text = "Неправильно";
                Label count_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 0, 9, 1, 1, text, true, true);
                labels.add(count_Label);
                Label countTime_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                        3, 10, 1, 9, 2, 1, "Подсчетом наносекунд затрачено: " + (lEndTime - lStartTime), true, true);
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