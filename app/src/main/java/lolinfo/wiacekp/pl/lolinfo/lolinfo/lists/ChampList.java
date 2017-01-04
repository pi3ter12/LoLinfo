package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

/**
 * Created by debian-piotrek on 19.05.15.
 */
public class ChampList {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampID() {
        return champID;
    }

    public void setChampID(int champID) {
        this.champID = champID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public int getInfoAttack() {
        return infoAttack;
    }

    public void setInfoAttack(int infoAttack) {
        this.infoAttack = infoAttack;
    }

    public int getInfoDefense() {
        return infoDefense;
    }

    public void setInfoDefense(int infoDefense) {
        this.infoDefense = infoDefense;
    }

    public int getInfoMagic() {
        return infoMagic;
    }

    public void setInfoMagic(int infoMagic) {
        this.infoMagic = infoMagic;
    }

    public int getInfoDifficulty() {
        return infoDifficulty;
    }

    public void setInfoDifficulty(int infoDifficulty) {
        this.infoDifficulty = infoDifficulty;
    }

    public String[] getAllyTips() {
        return allyTips;
    }

    public void setAllyTips(String[] allyTips) {
        this.allyTips = allyTips;
    }

    public String[] getEnemyTips() {
        return enemyTips;
    }

    public void setEnemyTips(String[] enemyTips) {
        this.enemyTips = enemyTips;
    }

    public String getPassiveTitle() {
        return passiveTitle;
    }

    public void setPassiveTitle(String passiveTitle) {
        this.passiveTitle = passiveTitle;
    }

    public String getPassiveDescription() {
        return passiveDescription;
    }

    public void setPassiveDescription(String passiveDescription) {
        this.passiveDescription = passiveDescription;
    }

    public String getPassiveNameImage() {
        return passiveNameImage;
    }

    public void setPassiveNameImage(String passiveNameImage) {
        this.passiveNameImage = passiveNameImage;
    }

    public String getPassiveLinkToImage() {
        return passiveLinkToImage;
    }

    public void setPassiveLinkToImage(String passiveLinkToImage) {
        this.passiveLinkToImage = passiveLinkToImage;
    }

    public String[] getSpellsTitle() {
        return spellsTitle;
    }

    public void setSpellsTitle(String[] spellsTitle) {
        this.spellsTitle = spellsTitle;
    }

    public String[] getSpellsDescription() {
        return spellsDescription;
    }

    public void setSpellsDescription(String[] spellsDescription) {
        this.spellsDescription = spellsDescription;
    }

    public String[] getSpellsNameImage() {
        return spellsNameImage;
    }

    public void setSpellsNameImage(String[] spellsNameImage) {
        this.spellsNameImage = spellsNameImage;
    }

    public String[] getSpellsLinkToImage() {
        return spellsLinkToImage;
    }

    public void setSpellsLinkToImage(String[] spellsLinkToImage) {
        this.spellsLinkToImage = spellsLinkToImage;
    }

    public ChampList(int id, int champID, String name, String title, String nameImage, String linkToImage, String lore, int infoAttack, int infoDefense, int infoMagic, int infoDifficulty, String[] allyTips, String[] enemyTips, String passiveTitle, String passiveDescription, String passiveNameImage, String passiveLinkToImage, String[] spellsTitle, String[] spellsDescription, String[] spellsNameImage, String[] spellsLinkToImage) {
        this.id = id;
        this.champID = champID;
        this.name = name;
        this.title = title;
        this.nameImage = nameImage;
        this.linkToImage = linkToImage;
        this.lore = lore;
        this.infoAttack = infoAttack;
        this.infoDefense = infoDefense;
        this.infoMagic = infoMagic;
        this.infoDifficulty = infoDifficulty;
        this.allyTips = allyTips;
        this.enemyTips = enemyTips;
        this.passiveTitle = passiveTitle;
        this.passiveDescription = passiveDescription;
        this.passiveNameImage = passiveNameImage;
        this.passiveLinkToImage = passiveLinkToImage;
        this.spellsTitle = spellsTitle;
        this.spellsDescription = spellsDescription;
        this.spellsNameImage = spellsNameImage;
        this.spellsLinkToImage = spellsLinkToImage;
    }

    int id;
    int champID;
    String name;
    String title;
    String nameImage;
    String linkToImage;
    String lore;
    int infoAttack;
    int infoDefense;
    int infoMagic;
    int infoDifficulty;
    String[] allyTips;
    String[] enemyTips;

    private String passiveTitle;
    private String passiveDescription;
    private String passiveNameImage;
    private String passiveLinkToImage;

    private String[] spellsTitle;
    private String[] spellsDescription;
    private String[] spellsNameImage;
    private String[] spellsLinkToImage;

}
