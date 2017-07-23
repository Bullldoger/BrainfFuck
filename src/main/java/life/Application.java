package life;

/**
 * Created by roman on 19.07.17.
 */

import life.ui.BrainFuckPanel;
import org.apache.log4j.Logger;

import javax.swing.*;

public class Application {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            BrainFuckPanel brainFuckPanel = new BrainFuckPanel();
            brainFuckPanel.setVisible(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
