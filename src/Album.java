public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;

    // Constructor
    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        nbComps = 0;
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
        nbComps = 0;

        if (condition == null || condition.equals("") || condition.trim().isEmpty()) {
            return allPhotos; 
        }

        String[] requiredTags = condition.split("AND");
        for (int i = 0; i < requiredTags.length; i++) {
            requiredTags[i] = requiredTags[i].trim(); 
        }   

        allPhotos.findFirst();
        while (true) {
            Photo photo = allPhotos.current.getData();
            LinkedList<String> tags = photo.getTags();
    
            if (tags.empty()) {
                if (!allPhotos.last()) {
                    allPhotos.findNext();
                } else {
                    break;
                }
                continue; 
            }
    
            boolean matches = true;
    
            for (int j = 0; j < requiredTags.length; j++) {
                boolean tagFound = false;
                tags.findFirst();
                while (true) {
                    if (tags.current.getData().equals(requiredTags[j])) {
                        tagFound = true;
                        nbComps++;
                        break; 
                    }
                    if (!tags.last()) {
                        tags.findNext();
                        nbComps++;
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
                result.findFirst();
                boolean alreadyInResult = false;
                while (!result.empty()) {
                    if (photo.getPath().equalsIgnoreCase(result.current.getData().getPath())) {
                        alreadyInResult = true;
                        break;
                    }
                    if (!result.last()) {
                        result.findNext();
                    } else {
                        break;
                    }
                }
                if (!alreadyInResult) {
                    result.insert(photo);
                }
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
        return nbComps;
    }
}
