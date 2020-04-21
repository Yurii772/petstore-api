
public class Pet {
    private String id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tags[] tags;
    private String status;

    public Pet(String id, String name, String status) {
        this.id = id;
        this.category = new Category("0", "dogs");
        this.name = name;
        this.status = status;
        this.photoUrls = new String[]{"https://i.insider.com/5ba126df3cccd120008b4568?width=2500&format=jpeg&auto=webp",
                "https://myfeed.ru/wp-content/uploads/2016/04/priroda-zhivotnoe-enot-lapy-2-768x432.jpg"};
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

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Tags[] getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }
}