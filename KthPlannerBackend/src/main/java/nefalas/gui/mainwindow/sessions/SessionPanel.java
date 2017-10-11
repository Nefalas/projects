package nefalas.gui.mainwindow.sessions;

import nefalas.gui.components.button.ControlButton;
import nefalas.gui.components.scrolling.ModernScrollPane;
import nefalas.webreader.sessionmanager.SessionManager.Session;
import nefalas.webreader.sessionmanager.SessionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.Callable;

public class SessionPanel extends JPanel {

    private static final Color PANEL_BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color HEADER_BACKGROUND_COLOR = new Color(94, 155, 141);
    private static final Color CARD_BACKGROUND_COLOR = new Color(193, 222, 215);

    private JPanel panel;
    private JProgressBar progressBar;

    public SessionPanel() {
        setLayout(new BorderLayout());
        setBackground(PANEL_BACKGROUND_COLOR);

        panel = new JPanel();
        panel.setBackground(PANEL_BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(new Header(), BorderLayout.PAGE_START);

        ModernScrollPane scrollPane = new ModernScrollPane(panel, ModernScrollPane.SCROLLBAR_COLOR.DARK, 18);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateSessions() {
        panel.removeAll();
        if (!SessionManager.isRefreshing) {
            for (Session session : SessionManager.getSessions()) {
                String username = session.username;
                long lastUsed = (new Date().getTime()) - session.lastUse.getTime();
                panel.add(new SessionCard(username, lastUsed));
            }
            panel.revalidate();
            panel.repaint();
        }
    }

    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }

    class Header extends JPanel {

        Header() {
            setLayout(new BorderLayout());
            setBackground(HEADER_BACKGROUND_COLOR);

            JLabel titleLabel = new JLabel("Sessions", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 40));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(new EmptyBorder(15,0,25,0));
            add(titleLabel, BorderLayout.CENTER);

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            add(progressBar, BorderLayout.PAGE_END);
        }
    }

    class SessionCard extends JPanel {

        GridBagConstraints gbc = new GridBagConstraints();

        SessionCard(String username, long lastUsed) {
            setMaximumSize(new Dimension(2000, 100));
            setMinimumSize(new Dimension(0, 100));
            setLayout(new BorderLayout());

            setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, PANEL_BACKGROUND_COLOR));

            JPanel innerPanel = new JPanel();
            innerPanel.setLayout(new BorderLayout());
            innerPanel.setBackground(CARD_BACKGROUND_COLOR);
            innerPanel.setBorder(new EmptyBorder(5,5,5,5));

            InfoPanel infoPanel = new InfoPanel(username, lastUsed);
            ControlPanel controlPanel = new ControlPanel(username);

            innerPanel.add(infoPanel, BorderLayout.CENTER);
            innerPanel.add(controlPanel, BorderLayout.LINE_END);

            add(innerPanel, BorderLayout.CENTER);
        }

        class InfoPanel extends JPanel {

            InfoPanel(String username, long lastUsed) {
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                setBackground(CARD_BACKGROUND_COLOR);

                Label usernameLabel = new Label(username);
                usernameLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
                add(usernameLabel);

                int secondsLU = (int) (lastUsed/1000)%60;
                int minutesLU = (int) (lastUsed/(1000*60))%60;
                String textLU = "Last used: " + ((minutesLU > 0)? minutesLU + " min " : "")
                        + ((secondsLU > 0)? secondsLU + " sec ago" : "");
                Label lastUsedLabel = new Label(textLU);
                add(lastUsedLabel);

                int secondsEI = 60 - secondsLU;
                int minutesEI = 9 - minutesLU;
                if (secondsEI == 60 && minutesEI == 9) {
                    secondsEI = 0;
                    minutesEI = 10;
                }
                String textEI = (minutesEI < 0)? "Expires at next refresh" :
                        "Expires in: " + ((minutesEI > 0)? minutesEI + " min ": "")
                                + ((secondsEI > 0)? secondsEI + "sec" : "");
                Label expiresInLabel = new Label(textEI);
                add(expiresInLabel);
            }

        }

        class ControlPanel extends JPanel {

            ControlPanel(String username) {
                setLayout(new GridLayout(0,1, 0,5));
                setBackground(CARD_BACKGROUND_COLOR);

                ControlButton killButton = new ControlButton("Kill session", ControlButton.COLOR.RED);
                killButton.setClickAction(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        SessionManager.killSessionByUsername(username);
                        return null;
                    }
                });
                add(killButton);

                ControlButton refreshButton = new ControlButton("Refresh session", ControlButton.COLOR.GREEN);
                refreshButton.setClickAction(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        SessionManager.refreshSessionByUsername(username);
                        return null;
                    }
                });
                add(refreshButton);
            }

        }
    }

}
