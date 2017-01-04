package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

/**
 * Created by debian-piotrek on 26.05.15.
 */
public class OtherList {
    public OtherList(int id, String version, boolean isFinish) {
        this.id = id;
        this.version = version;
        this.isFinish = isFinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    private int id;
    private String version;
    private boolean isFinish;
}
