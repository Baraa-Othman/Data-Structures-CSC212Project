public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invertedIndex; // Using your custom BST and LinkedList

    // Constructor
    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
        LinkedList<String> tags = p.getTags(); // Get tags of the photo
        tags.findFirst();
        while (!tags.empty()) {
            String tag = tags.current.getData(); // Get the current tag

             // hashCode() is used to create a unique key for each tag
            // Because if we used charAt(0) ASCII value,
            // we would have a lot of collisions, forcing us to loop
            // through the whole String, not just the first character
            if (!invertedIndex.findkey(tag.hashCode())) {
                invertedIndex.insert(tag.hashCode(), new LinkedList<>());
            }

            // Add the photo to the LinkedList associated with the tag
            LinkedList<Photo> photos = invertedIndex.retrieve();
            photos.insert(p);

            // Move to the next tag
            if (!tags.last()) {
                tags.findNext();
            } else {
                break;
            }
        }
    }

    // Delete a photo
    public void deletePhoto(String path) {
        invertedIndex.find(Relative.Root); // Start at the root of the BST
        while (true) {
            LinkedList<Photo> photos = invertedIndex.retrieve(); // Get the LinkedList of photos for the current tag
            photos.findFirst();
            while (!photos.empty()) {
                // Check if the current photo matches the path
                if (photos.current.getData().getPath().equals(path)) {
                    photos.remove(); // Remove the photo
                    break;
                }
                if (!photos.last()) {
                    photos.findNext();
                } else {
                    break;
                }
            }

            // If the LinkedList becomes empty, remove the tag from the BST
            if (photos.empty()) {
                invertedIndex.deleteSubtree();
            }
              // Move to the next tag in the BST
              if (!invertedIndex.find(Relative.RightChild)) {
                break;
            }
        }
    }

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos() {
        return invertedIndex;
    }
}
