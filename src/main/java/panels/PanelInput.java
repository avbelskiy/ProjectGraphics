package panels;

import controls.Button;
import controls.Input;
import controls.InputFactory;
import controls.Label;
import io.github.humbleui.jwm.*;
import io.github.humbleui.skija.Canvas;
import misc.CoordinateSystem2i;
import misc.Vector2i;
import controls.MultiLineLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static app.Application.PANEL_PADDING;
import static app.Colors.*;

/**
 * Панель управления
 */
public class PanelInput extends GridPanel {
    //public static int[] array = {5, 9, -19, 23, 54, 450, 19, 19, 20, 0, -0, 100};
    public static int[] array;

    public static boolean isArr = false;
    /**
     * Заголовки
     */
    public List<Label> labels;
    public List<MultiLineLabel> multiLineLabels;
    int i = 0;
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
    public PanelInput(
            Window window, boolean drawBG, int color, int padding, int gridWidth, int gridHeight,
            int gridX, int gridY, int colspan, int rowspan
    ) {
        super(window, drawBG, color, padding, gridWidth, gridHeight, gridX, gridY, colspan, rowspan);

        // создаём списки
        inputs = new ArrayList<>();
        labels = new ArrayList<>();
        multiLineLabels = new ArrayList<>();
        buttons = new ArrayList<>();

        // добавление вручную

        MultiLineLabel nLabel = new MultiLineLabel(window, false, backgroundColor, PANEL_PADDING,
                6, 7, 0, 0, 2, 1, "Длина массива:", true, true);
        multiLineLabels.add(nLabel);

        Input nField = InputFactory.getInput(window, false, FIELD_BACKGROUND_COLOR, PANEL_PADDING,
                6, 7, 2, 0, 2, 1, "1", true,
                FIELD_TEXT_COLOR, true);
        inputs.add(nField);

        Button addArr = new Button(
                window, true, BUTTON_COLOR, PANEL_PADDING,
                6, 7, 4, 0, 2, 1, "Создать массив",
                true, true);
        addArr.setOnClick(() -> {
            // если числа введены верно
            if (!nField.hasValidIntValue() || Integer.parseInt(nField.getText()) < 0) {
                PanelLog.warning("Длина введена неверно");
            } else {

                PanelLog.success("Пустой массив создан");
                array = new int[Integer.parseInt(nField.getText())];
                isArr = true;
            }
            MultiLineLabel arrLabel;
            if (!isArr) {
                arrLabel = new MultiLineLabel(window, true, backgroundColor, PANEL_PADDING,
                        1, 7, 0, 3, 1, 1, "Массив", true, true);
                multiLineLabels.add(arrLabel);

            } else {
                arrLabel = new MultiLineLabel(window, true, APP_BACKGROUND_COLOR, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, "", true, true);
                multiLineLabels.add(arrLabel);
                arrLabel = new MultiLineLabel(window, true, backgroundColor, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, Arrays.toString(array), false, false);
                multiLineLabels.add(arrLabel);
            }
        });
        buttons.add(addArr);
        Label inLabel = new Label(window, false, backgroundColor, PANEL_PADDING,
                6, 7, 0, 1, 1, 1, "Число:", true, true);
        labels.add(inLabel);

        Input inField = InputFactory.getInput(window, false, FIELD_BACKGROUND_COLOR, PANEL_PADDING,
                6, 7, 1, 1, 2, 1, Integer.toString(i), true,
                FIELD_TEXT_COLOR, true);
        inputs.add(inField);

        Label indLabel = new Label(window, false, backgroundColor, PANEL_PADDING,
                6, 7, 3, 1, 1, 1, "индекс:", true, true);
        labels.add(indLabel);

        Input indField = InputFactory.getInput(window, false, FIELD_BACKGROUND_COLOR, PANEL_PADDING,
                6, 7, 4, 1, 2, 1, Integer.toString(i), true,
                FIELD_TEXT_COLOR, true);
        inputs.add(indField);

        Button addNumber = new Button(
                window, true, BUTTON_COLOR, PANEL_PADDING,
                3, 7, 0, 2, 2, 1, "Добавить в массив",
                true, true);
        addNumber.setOnClick(() -> {
            // если числа введены верно
            if (array == null) {
                PanelLog.warning("Массив ещё не создан");

            } else if (!indField.hasValidIntValue() || Integer.parseInt(indField.getText()) < 0 || Integer.parseInt(indField.getText()) > array.length - 1) {
                PanelLog.warning("Индекс введен неверно");
            } else if (!inField.hasValidIntValue()) {
                PanelLog.warning("Число введено неверно");
            } else {
                PanelLog.success("Число добавлено к массиву");
                array[Integer.parseInt(indField.getText())] = Integer.parseInt(inField.getText());
                i += 1;
                MultiLineLabel arrLabel;
                arrLabel = new MultiLineLabel(window, true, APP_BACKGROUND_COLOR, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, "", true, true);
                multiLineLabels.add(arrLabel);
                arrLabel = new MultiLineLabel(window, true, backgroundColor, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, Arrays.toString(array), false, false);
                multiLineLabels.add(arrLabel);
            }
        });
        buttons.add(addNumber);

        Button randArr = new Button(
                window, true, BUTTON_COLOR, PANEL_PADDING,
                3, 7, 2, 2, 1, 1, "Случайный массив",
                true, true);

        randArr.setOnClick(() -> {
            if (array == null) {
                PanelLog.warning("Массив ещё не создан");
            } else {
                PanelLog.success("Случайный массив задан");
                for (int i = 0; i < array.length; i++) {
                    Random r = new Random();
                    int rr = r.nextInt(200) - 100;
                    array[i] = rr;
                }
                MultiLineLabel arrLabel;
                arrLabel = new MultiLineLabel(window, true, APP_BACKGROUND_COLOR, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, "", true, true);
                multiLineLabels.add(arrLabel);
                arrLabel = new MultiLineLabel(window, true, backgroundColor, PANEL_PADDING,
                        1, 7, 0, 3, 1, 4, Arrays.toString(array), false, false);
                multiLineLabels.add(arrLabel);
            }
        });
        buttons.add(randArr);
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