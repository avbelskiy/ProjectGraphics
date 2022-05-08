package app;

import controls.InputFactory;
import controls.Label;
import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Surface;
import misc.CoordinateSystem2i;
import panels.PanelInfo;
import panels.PanelInput;
import panels.PanelLog;
import panels.PanelOutput;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

import static app.Colors.*;

/**
 * Класс окна приложения
 */
public class Application implements Consumer<Event> {

    public enum Mode {
        WORK,
        INFO
    }
    /**
     * окно приложения
     */
    private final Window window;
    /**
     * отступ приложения
     */
    public static final int PANEL_PADDING = 10;

    /**
     * радиус скругления элементов
     */
    public static final int C_RAD_IN_PX = 10;
    /**
     * панель курсора мыши
     */
    private final PanelInput panelInput;
    private final PanelOutput panelOutput;
    private final PanelLog panelLog;
    private final Label label;
    private final PanelInfo panelInfo;

    public static Mode currentMode = Mode.WORK;

    /**
     * время последнего нажатия клавиши мыши
     */
    Date prevEventMouseButtonTime;

    /**
     * Конструктор окна приложения
     */
    public Application() {
        // создаём окно
        window = App.makeWindow();

        label = new Label(window, true, LABEL_BACKGROUND_COLOR, PANEL_PADDING,
                1, 7, 0, 0, 1, 1, "Ввод массива и получение данных", true, true);

        // создаём панель управления
        panelInput = new PanelInput(
                window, true, PANEL_BACKGROUND_COLOR, PANEL_PADDING, 2, 7, 0, 1,
                1, 5
        );
        panelOutput = new PanelOutput(
                window, true, PANEL_BACKGROUND_COLOR, PANEL_PADDING, 2, 7, 1, 1,
                1, 6
        );

        panelLog = new PanelLog(
                window, true, PANEL_BACKGROUND_COLOR, PANEL_PADDING, 2, 7, 0, 6,
                1, 1
        );
        panelInfo = new PanelInfo(window, true, APP_BACKGROUND_COLOR, PANEL_PADDING);

        // задаём обработчиком событий текущий объект
        window.setEventListener(this);
        // задаём заголовок
        window.setTitle("Методы сортировки");
        // задаём размер окна
        window.maximize();
        // задаём иконку
        switch (Platform.CURRENT) {
            case WINDOWS -> window.setIcon(new File("src/main/resources/windows.ico"));
            case MACOS -> window.setIcon(new File("src/main/resources/macos.icns"));
        }


        // названия слоёв, которые будем перебирать
        String[] layerNames = new String[]{
                "LayerGLSkija", "LayerRasterSkija"
        };

        // перебираем слои
        for (String layerName : layerNames) {
            String className = "io.github.humbleui.jwm.skija." + layerName;
            try {
                Layer layer = (Layer) Class.forName(className).getDeclaredConstructor().newInstance();
                window.setLayer(layer);
                break;
            } catch (Exception e) {
                System.out.println("Ошибка создания слоя " + className);
            }
        }

        // если окну не присвоен ни один из слоёв
        if (window._layer == null)
            throw new RuntimeException("Нет доступных слоёв для создания");

        // делаем окно видимым
        window.setVisible(true);
    }

    /**
     * Обработчик событий
     *
     * @param e событие
     */
    @Override
    public void accept(Event e) {
        // если событие кнопка мыши
        if (e instanceof EventMouseButton) {
            // получаем текущие дату и время
            Date now = Calendar.getInstance().getTime();
            // если уже было нажатие
            if (prevEventMouseButtonTime != null) {
                // если между ними прошло больше 200 мс
                long delta = now.getTime() - prevEventMouseButtonTime.getTime();
                if (delta < 200)
                    return;
            }
            // сохраняем время последнего события
            prevEventMouseButtonTime = now;
        }
        // кнопки клавиатуры
        else if (e instanceof EventKey eventKey) {
            // кнопка нажата с Ctrl
            if (eventKey.isPressed()) {
                switch (eventKey.getKey()) {
                    case ESCAPE -> {
                        if (currentMode.equals(Mode.WORK)) {
                            window.close();
                            // завершаем обработку, иначе уже разрушенный контекст
                            // будет передан панелям
                            return;
                        }
                    }
                    case TAB -> InputFactory.nextTab();
                }
            }
        }
        // если событие - это закрытие окна
        else if (e instanceof EventWindowClose) {
            // завершаем работу приложения
            App.terminate();
            // закрытие окна
        } else if (e instanceof EventWindowCloseRequest) {
            window.close();
        } else if (e instanceof EventFrameSkija ee) {
            Surface s = ee.getSurface();
            paint(s.getCanvas(), new CoordinateSystem2i(0, 0, s.getWidth(), s.getHeight())
            );
        } else if (e instanceof EventFrame) {
            // запускаем рисование кадра
            window.requestFrame();
        }

        switch (currentMode) {
            case INFO -> panelInfo.accept(e);
            case WORK -> {
                // передаём события на обработку панелям
                panelOutput.accept(e);
                panelInput.accept(e);
            }
        }
    }

    /**
     * Рисование
     *
     * @param canvas   низкоуровневый инструмент рисования примитивов от Skija
     * @param windowCS СК окна
     */
    public void paint(Canvas canvas, CoordinateSystem2i windowCS) {
        // запоминаем изменения (пока что там просто заливка цветом)
        canvas.save();
        // очищаем канвас
        canvas.clear(APP_BACKGROUND_COLOR);
        // рисуем панели
        panelInput.paint(canvas, windowCS);
        panelOutput.paint(canvas, windowCS);
        panelLog.paint(canvas, windowCS);
        label.paint(canvas, windowCS);
        canvas.restore();

        if (currentMode == Mode.INFO) {
            panelInfo.paint(canvas, windowCS);
        }
    }
}