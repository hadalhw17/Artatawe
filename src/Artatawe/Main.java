package Artatawe;

import Artatawe.Data.DataController;
import Artatawe.GUI.GUIController;

/**
 * Artatawe program entry point
 */
public class Main
{
    public static void main(String[] args)
    {
        DataController data = new DataController();
        data.save();

        GUIController gui = new GUIController();
    }
}
