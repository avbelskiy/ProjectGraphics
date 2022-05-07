package panels;

import app.Algorithm;
import controls.Button;
import controls.Input;
import controls.Label;
import controls.MultiLineLabel;
import io.github.humbleui.jwm.*;
import io.github.humbleui.skija.Canvas;
import misc.CoordinateSystem2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.Application.PANEL_PADDING;
import static app.Colors.PANEL_BACKGROUND_COLOR;
import static panels.PanelInput.array;
import app.Algorithm.*;

/**
 * Панель управления
 */
public class PanelOutput extends GridPanel {
    int[] array_bubble = array;
    int[] array_choose = array;
    int[] array_paste = array;
    int[] array_shuttle = array;
    int[] array_shell = array;
    int[] array_quick = array;
    int[] array_swap = array;
    //int[] array_bucket = array;
    int[] array_count = array;

    /**
     * Заголовок
     */
    public List<Label> labels;
    public List<MultiLineLabel> multiLineLabels;
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
        labels = new ArrayList<>();
        multiLineLabels = new ArrayList<>();
        buttons = new ArrayList<>();

        Label rLabel = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 0, 1, 1, "Правильность сортировки сравнивается с методом пузырька", true, true);
        labels.add(rLabel);

        Label bubble_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 1, 1, 1, Arrays.toString(array_bubble), true, true);
        labels.add(bubble_Label);

        Label choose_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 2, 1, 1, Arrays.toString(array_choose), true, true);
        labels.add(choose_Label);

        Label paste_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 3, 1, 1, Arrays.toString(array_paste), true, true);
        labels.add(paste_Label);

        Label shuttle_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 4, 1, 1, Arrays.toString(array_shuttle), true, true);
        labels.add(shuttle_Label);

        Label shell_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 5, 1, 1, Arrays.toString(array_shell), true, true);
        labels.add(shell_Label);

        Label quick_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 6, 1, 1, Arrays.toString(array_quick), true, true);
        labels.add(quick_Label);

        Label swap_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 7, 1, 1, Arrays.toString(array_swap), true, true);
        labels.add(swap_Label);

        //Label bucket_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
        //        1, 10, 0, 8, 1, 1, Arrays.toString(array_bucket), true, true);
        //labels.add(bucket_Label);

        Label count_Label = new Label(window, true, backgroundColor, PANEL_PADDING,
                1, 10, 0, 9, 1, 1, Arrays.toString(array_count), true, true);
        labels.add(count_Label);


    }

    /**
     * Обработчик событий
     *
     * @param e событие
     */
    @Override
    public void accept(Event e) {

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
        // выводим поля вывода
        for (Label label : labels) {
            label.paint(canvas, windowCS);
        }

        for (MultiLineLabel multiLineLabel : multiLineLabels) {
            multiLineLabel.paint(canvas, windowCS);
        }
    }
}