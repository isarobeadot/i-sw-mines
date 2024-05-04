package inaki.sw.mines.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class Chronometer implements Runnable {

    private static final Logger LOGGER = getLogger(Chronometer.class.getName());
    public static final String C_UPDATE_CHRONO = "C_UPDATE_CHRONO";
    private boolean instancePaused;
    private boolean instanceRunning;
    private static final int STEP = 100;
    private Integer minutes;
    private Integer seconds;
    private Integer miliseconds;
    private ActionListener actionlistener;
    private int eventNo = 0;

    /**
     *
     * @return
     */
    public boolean isInstancePaused() {
        return instancePaused;
    }

    /**
     *
     * @return
     */
    public boolean isInstanceRunning() {
        return instanceRunning;
    }

    /**
     *
     * @return
     */
    public Integer getMinutes() {
        return minutes;
    }

    /**
     *
     * @return
     */
    public Integer getSeconds() {
        return seconds;
    }

    /**
     *
     * @return
     */
    public Integer getMiliseconds() {
        return miliseconds;
    }

    /**
     *
     * @param miliseconds
     */
    public void setMiliseconds(Integer miliseconds) {
        this.miliseconds = miliseconds;
    }

    /**
     *
     */
    public void pauseChronometer() {
        instancePaused = true;
    }

    /**
     *
     */
    public void resumeChronometer() {
        instancePaused = false;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        if (!instanceRunning) {
            instanceRunning = true;
            minutes = 0;
            seconds = 0;
            miliseconds = 0;
            LOGGER.fine("Chronometer started");
            actionlistener.actionPerformed(new ActionEvent(this, eventNo, C_UPDATE_CHRONO));
            eventNo++;
            while (instanceRunning) {
                try {
                    // Wait for an step
                    Thread.sleep(STEP);
                    if (!instancePaused) {
                        miliseconds += STEP;

                        // One more second
                        if (miliseconds == 1000) {
                            miliseconds = 0;
                            seconds += 1;
                            // One more minute
                            if (seconds == 60) {
                                seconds = 0;
                                minutes++;
                            }
                            actionlistener.actionPerformed(new ActionEvent(this, eventNo, C_UPDATE_CHRONO));
                            eventNo++;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, "Chronometer interrupted: {0}", ex);
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
            LOGGER.fine("Chronometer stopped");
        } else {
            LOGGER.fine("There is an instance running");
        }
    }

    /**
     *
     * @param actionlistener
     */
    public void setActionlistener(ActionListener actionlistener) {
        this.actionlistener = actionlistener;
    }

    /**
     *
     */
    public void stopChronometer() {
        instanceRunning = false;
    }
}
