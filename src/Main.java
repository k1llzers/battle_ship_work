import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class Main {
    public static int botMode = 1;
    public static int hardMode = 2;
    public static int coins = 1000;
    public static int countOfPidkazka = 0;
    public static int countOfPechatka = 0;
    public static int countOfTsarBimba = 0;
    public static JFrame mainFrame = new JFrame();
    private static Clip clip;
    private static Settings settings = new Settings();
    private static Menu menu = new Menu();
    private static Store store = new Store();
}

    public static void main(String[] args) {
//        Music
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("music\\sound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(20f * (float) Math.log10(0.05));
        getFromFile();
        clip.loop(200);
//        Music
        mainFrame.setTitle("BattleShit");
        mainFrame.setSize(1500,1000);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                saveToFile();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        openMenu();
        mainFrame.setVisible(true);
    }

    public static void setVolume(double volume){
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(20f * (float) Math.log10(0.01 * volume));
    }

    public static void openMenu(){
        menu.updateCoins();
        mainFrame.add(menu);
        reload();
    }

    public static void openSettings(){
        mainFrame.add(settings);
        reload();
    }

    public static void openStore(){
        store.updateCounters();
        mainFrame.add(store);
        reload();
    }

    public static void openPlay(){
        if (botMode == 1)
            mainFrame.add(new Play());
        else
            mainFrame.add(new PlayWithFriend());
        reload();
    }

    public static void reload(){
        mainFrame.getGlassPane().setVisible(false);
        mainFrame.getGlassPane().setVisible(true);
    }