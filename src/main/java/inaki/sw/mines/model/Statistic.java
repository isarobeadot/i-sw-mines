package inaki.sw.mines.model;

import inaki.sw.mines.view.ViewMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class Statistic {

    private List<Integer> discoveredHistory;

    private Integer horizontalSize;

    private Integer mineNumber;

    private String name;

    private Integer primaryClikNumber;

    private Integer totalMinutes;

    private Integer totalSeconds;

    private GameType type;

    private Integer verticalSize;

    private ViewMode viewMode;

    private Date winDate;

    /**
     *
     * @return
     */
    public List<Integer> getDiscoveredHistory() {
        return discoveredHistory;
    }

    /**
     *
     * @param discoveredHistory
     */
    public void setDiscoveredHistory(List<Integer> discoveredHistory) {
        this.discoveredHistory = discoveredHistory;
    }

    /**
     *
     * @return
     */
    public Integer getHorizontalSize() {
        return horizontalSize;
    }

    /**
     *
     * @param horizontalSize
     */
    public void setHorizontalSize(Integer horizontalSize) {
        this.horizontalSize = horizontalSize;
    }

    /**
     *
     * @return
     */
    public Integer getMineNumber() {
        return mineNumber;
    }

    /**
     *
     * @param mineNumber
     */
    public void setMineNumber(Integer mineNumber) {
        this.mineNumber = mineNumber;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Integer getPrimaryClikNumber() {
        return primaryClikNumber;
    }

    /**
     *
     * @param primaryClikNumber
     */
    public void setPrimaryClikNumber(Integer primaryClikNumber) {
        this.primaryClikNumber = primaryClikNumber;
    }

    /**
     *
     * @return
     */
    public Integer getTotalMinutes() {
        return totalMinutes;
    }

    /**
     *
     * @param totalMinutes
     */
    public void setTotalMinutes(Integer totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    /**
     *
     * @return
     */
    public Integer getTotalSeconds() {
        return totalSeconds;
    }

    /**
     *
     * @param totalSeconds
     */
    public void setTotalSeconds(Integer totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    /**
     *
     * @return
     */
    public GameType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(GameType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public Integer getVerticalSize() {
        return verticalSize;
    }

    /**
     *
     * @param verticalSize
     */
    public void setVerticalSize(Integer verticalSize) {
        this.verticalSize = verticalSize;
    }

    /**
     *
     * @return
     */
    public ViewMode getViewMode() {
        return viewMode;
    }

    /**
     *
     * @param viewMode
     */
    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    /**
     *
     * @return
     */
    public Date getWinDate() {
        return winDate;
    }

    /**
     *
     * @param winDate
     */
    public void setWinDate(Date winDate) {
        this.winDate = winDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.winDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Statistic other = (Statistic) obj;
        return Objects.equals(this.winDate, other.winDate);
    }

    @Override
    public String toString() {
        return "Statistic{" + "horizontalSize=" + horizontalSize + ", mineNumber=" + mineNumber + ", name=" + name + ", primaryClikNumber=" + primaryClikNumber + ", totalMinutes=" + totalMinutes + ", totalSeconds=" + totalSeconds + ", type=" + type + ", verticalSize=" + verticalSize + ", viewMode=" + viewMode + ", winDate=" + winDate + '}';
    }

}
