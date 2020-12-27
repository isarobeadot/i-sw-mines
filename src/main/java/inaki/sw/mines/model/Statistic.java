package inaki.sw.mines.model;

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

    private Date winDate;

    public List<Integer> getDiscoveredHistory() {
        return discoveredHistory;
    }

    public void setDiscoveredHistory(List<Integer> discoveredHistory) {
        this.discoveredHistory = discoveredHistory;
    }

    public Integer getHorizontalSize() {
        return horizontalSize;
    }

    public void setHorizontalSize(Integer horizontalSize) {
        this.horizontalSize = horizontalSize;
    }

    public Integer getMineNumber() {
        return mineNumber;
    }

    public void setMineNumber(Integer mineNumber) {
        this.mineNumber = mineNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrimaryClikNumber() {
        return primaryClikNumber;
    }

    public void setPrimaryClikNumber(Integer primaryClikNumber) {
        this.primaryClikNumber = primaryClikNumber;
    }

    public Integer getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Integer totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public Integer getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Integer totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Integer getVerticalSize() {
        return verticalSize;
    }

    public void setVerticalSize(Integer verticalSize) {
        this.verticalSize = verticalSize;
    }

    public Date getWinDate() {
        return winDate;
    }

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
        return "Statistic{" + "horizontalSize=" + horizontalSize + ", mineNumber=" + mineNumber + ", name=" + name + ", primaryClikNumber=" + primaryClikNumber + ", totalMinutes=" + totalMinutes + ", totalSeconds=" + totalSeconds + ", type=" + type + ", verticalSize=" + verticalSize + ", winDate=" + winDate + '}';
    }

}
