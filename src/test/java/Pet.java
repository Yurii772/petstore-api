
public class Pet {
    private String id;
    private Category category;
    private String name;
    private PhotoUrls[] photoUrls;
    private Tags[] tags;
    private String status;

    public Pet(String id, String name, String status) {
        this.id = id;
        this.category = new Category("0", "dogs");
        this.name = name;
        this.status = status;
        this.photoUrls = new PhotoUrls[]{new PhotoUrls("https://media.mnn.com/assets/images/2017/02/scarlet-kingsnakes.jpg.838x0_q80.jpg")};
        this.tags = new Tags[]{new Tags("0", "Sharikas")};
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public PhotoUrls[] getPhotoUrls() {
        return photoUrls;
    }

    public Tags[] getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }
}