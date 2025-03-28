public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;

    // Constructor
    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    // Return the name of the album
    public String getName() {
        return name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return condition;
    }

    // Return the manager
    public PhotoManager getManager() {
        return manager;
    }

    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> result = new LinkedList<>();
        LinkedList<Photo> allPhotos = manager.getPhotos();
        String[] requiredTags = condition.split("\\s*AND\\s*");

        allPhotos.findFirst();
        for (int i = 0; !allPhotos.empty(); i++) {
            Photo photo = allPhotos.current.getData();
            LinkedList<String> tags = photo.getTags();
            boolean matches = true;

            tags.findFirst();
            for (int j = 0; j < requiredTags.length; j++) {
                boolean tagFound = false;
                for (int k = 0; !tags.empty(); k++) {
                    if (tags.current.getData().equals(requiredTags[j])) {
                        tagFound = true;
                        break;
                    }
                    if (!tags.last()) {
                        tags.findNext();
                    } else {
                        break;
                    }
                }
                if (!tagFound) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                result.insert(photo);
            }

            if (!allPhotos.last()) {
                allPhotos.findNext();
            } else {
                break;
            }
        }

        return result;
    }

    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        int comparisons = 0;
        LinkedList<Photo> allPhotos = manager.getPhotos();
        String[] requiredTags = condition.split("\\s*AND\\s*");

        allPhotos.findFirst();
        for (int i = 0; !allPhotos.empty(); i++) {
            Photo photo = allPhotos.current.getData();
            LinkedList<String> tags = photo.getTags();

            tags.findFirst();
            for (int j = 0; j < requiredTags.length; j++) {
                for (int k = 0; !tags.empty(); k++) {
                    comparisons++;
                    if (tags.current.getData().equals(requiredTags[j])) {
                        break;
                    }
                    if (!tags.last()) {
                        tags.findNext();
                    } else {
                        break;
                    }
                }
            }

            if (!allPhotos.last()) {
                allPhotos.findNext();
            } else {
                break;
            }
        }

        return comparisons;
    }
}