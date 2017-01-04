package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

/**
 * Created by debian-piotrek on 26.05.15.
 */

public class SalesList {         //tutaj uzupelnij

    public SalesList(int id, int champId, String name, String oldPrice, String newPrice, String nameImage1, String linkToImage1, String nameImage2, String linkToimage2) {
        this.id = id;
        this.champId = champId;
        this.name = name;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.nameImage1 = nameImage1;
        this.linkToImage1 = linkToImage1;
        this.nameImage2 = nameImage2;
        this.linkToimage2 = linkToimage2;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getNameImage1() {
        return nameImage1;
    }

    public void setNameImage1(String nameImage1) {
        this.nameImage1 = nameImage1;
    }

    public String getLinkToImage1() {
        return linkToImage1;
    }

    public void setLinkToImage1(String linkToImage1) {
        this.linkToImage1 = linkToImage1;
    }

    public String getNameImage2() {
        return nameImage2;
    }

    public void setNameImage2(String nameImage2) {
        this.nameImage2 = nameImage2;
    }

    public String getLinkToimage2() {
        return linkToimage2;
    }

    public void setLinkToimage2(String linkToimage2) {
        this.linkToimage2 = linkToimage2;
    }

    private int id;
    private int champId;
    private String name;
    private String oldPrice;
    private String newPrice;
    private String nameImage1;
    private String linkToImage1;
    private String nameImage2;
    private String linkToimage2;
}
