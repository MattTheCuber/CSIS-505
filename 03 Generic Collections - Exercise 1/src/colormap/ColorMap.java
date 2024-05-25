// Generic Collections Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 31, 2024

package colormap;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * ColorMap class to store a map of predefined list of 13 color names and Color
 * objects.
 */
public class ColorMap {
    private HashMap<String, Color> colorMap;

    /**
     * Constructor for the ColorMap class with 13 predefined colors.
     */
    public ColorMap() {
        colorMap = new HashMap<>();
        initializeColors();
    }

    /**
     * Initialize the color map with 13 predefined colors mapping each Color object
     * to a String name of that color.
     */
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

    /**
     * Get the Color object from the color map by the name of the color.
     * 
     * @param name The name of the color to get the Color object for.
     * @return The Color object for the given color name.
     */
    public Color getColor(String name) {
        return colorMap.get(name);
    }

    /**
     * Get the set of color names from the color map.
     * 
     * @return The set of color names from the color map.
     */
    public Set<String> getColorNames() {
        return colorMap.keySet();
    }

    /**
     * Get the array of color names from the color map.
     * 
     * @return The array of color names from the color map.
     */
    public String[] getColorNamesArray() {
        Set<String> colorNames = getColorNames();
        return colorNames.toArray(new String[colorNames.size()]);
    }

    /**
     * Get the set of Color objects from the color map.
     * 
     * @return The set of Color objects from the color map.
     */
    public Set<Color> getColors() {
        return new HashSet<>(colorMap.values());
    }

    /**
     * Get the array of Color objects from the color map.
     * 
     * @return The array of Color objects from the color map.
     */
    public Color[] getColorsArray() {
        Set<Color> colorNames = getColors();
        return colorNames.toArray(new Color[colorNames.size()]);
    }

    /**
     * Get the number of colors in the color map.
     * 
     * @return The number of colors in the color map.
     */
    public int size() {
        return colorMap.size();
    }
}
