public class PhotoManager {
    private LinkedList<Photo> photos; // Using your custom LinkedList

    // Constructor
    public PhotoManager() {
        photos = new LinkedList<>();
    }

    // Return all managed photos
    public LinkedList<Photo> getPhotos() {	
        return photos;
    }

    // Add a photo
    public void addPhoto(Photo p) {
        photos.insert(p);
    }

    // Delete a photo
    public void deletePhoto(String path) {
        photos.findFirst();
        while(true) {
            if (photos.retrieve().getPath().equals(path)) {
                photos.remove();
                System.out.println("This photo has been deleted");
                break;
            }
            if (!photos.last()) {
                photos.findNext();
            } else {
            	System.out.println("this photo isn't here");
                break;
            }
        }
    }
}
