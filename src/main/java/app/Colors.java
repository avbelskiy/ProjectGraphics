package app;

import misc.Misc;

/**
 * Класс цветов
 */
public class Colors {

   public static final int APP_BACKGROUND_COLOR = Misc.getColor(255, 173, 216, 230);

    public static final int LABEL_BACKGROUND_COLOR = Misc.getColor(32, 0, 0, 0);

    public static final int LABEL_TEXT_COLOR = Misc.getColor(100, 0, 0, 0);

    public static final int PANEL_BACKGROUND_COLOR = Misc.getColor(50, 0, 0, 0);

    public static final int FIELD_BACKGROUND_COLOR = Misc.getColor(255, 255, 255, 255);

    public static final int FIELD_TEXT_COLOR = Misc.getColor(255, 0, 0, 0);

    public static final int BUTTON_COLOR = Misc.getColor(80, 0, 0, 0);


    private Colors() {
        throw new AssertionError("Вызов этого конструктора запрещён");
    }
}