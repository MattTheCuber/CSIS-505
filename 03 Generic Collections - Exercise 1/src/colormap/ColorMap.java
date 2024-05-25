package colormap;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ColorMap {
    private HashMap<String, Color> colorMap;

    public ColorMap() {
        colorMap = new HashMap<>();
        initializeColors();
    }

    private void initializeColors() {
        colorMap.put("White", Color.WHITE);
        colorMap.put("Light Gray", Color.LIGHT_GRAY);
        colorMap.put("Gray", Color.GRAY);
        colorMap.put("Dark Gray", Color.DARK_GRAY);
        colorMap.put("Black", Color.BLACK);
        colorMap.put("Red", Color.RED);
        colorMap.put("Pink", Color.PINK);
        colorMap.put("Orange", Color.ORANGE);
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Magenta", Color.MAGENTA);
        colorMap.put("Cyan", Color.CYAN);
        colorMap.put("Blue", Color.BLUE);
    }

    public Color getColor(String name) {
        return colorMap.get(name);
    }

    public Set<String> getColorNames() {
        return colorMap.keySet();
    }

    public String[] getColorNamesArray() {
        Set<String> colorNames = colorMap.keySet();
        return colorNames.toArray(new String[colorNames.size()]);
    }

    public Set<Color> getColors() {
        return new HashSet<>(colorMap.values());
    }

    public int size() {
        return colorMap.size();
    }
}
