package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

/**
 * Created by debian-piotrek on 19.05.15.
 */
public class ItemsList {


    public ItemsList(int id, int itemID, int[] intoBuild, int[] fromBuild, String title, String description, int baseGold, int totalGold, int sellGold, String nameImage, String linkToImage) {
        this.id = id;
        this.itemID = itemID;
        this.intoBuild = intoBuild;
        this.fromBuild = fromBuild;
        this.title = title;
        this.description = description;
        this.baseGold = baseGold;
        this.totalGold = totalGold;
        this.sellGold = sellGold;
        this.nameImage = nameImage;
        this.linkToImage = linkToImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int[] getIntoBuild() {
        return intoBuild;
    }

    public void setIntoBuild(int[] intoBuild) {
        this.intoBuild = intoBuild;
    }

    public int[] getFromBuild() {
        return fromBuild;
    }

    public void setFromBuild(int[] fromBuild) {
        this.fromBuild = fromBuild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBaseGold() {
        return baseGold;
    }

    public void setBaseGold(int baseGold) {
        this.baseGold = baseGold;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getSellGold() {
        return sellGold;
    }

    public void setSellGold(int sellGold) {
        this.sellGold = sellGold;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    private int id;
    private int itemID;
    private int[] intoBuild;
    private int[] fromBuild;
    private String title;
    private String description;
    private int baseGold;
    private int totalGold;
    private int sellGold;
    private String nameImage;
    private String linkToImage;
}
