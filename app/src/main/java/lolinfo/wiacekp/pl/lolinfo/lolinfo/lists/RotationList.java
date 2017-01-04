package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

/**
 * Created by debian-piotrek on 25.05.15.
 */
public class RotationList {
    public RotationList(int id, int champId, String nameImage, String name) {
        this.id = id;
        this.champId = champId;
        this.nameImage = nameImage;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampId() {
        return champId;
    }

    public void setChampId(int champId) {
        this.champId = champId;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    private int id;
    private int champId;
    private String nameImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
