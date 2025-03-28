public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invertedIndex; // Using your custom BST and LinkedList

    // Constructor
    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
        LinkedList<String> tags = p.getTags();
        tags.findFirst();
        for (int i = 0; !tags.empty(); i++) {
            String tag = tags.current.getData();
            if (!invertedIndex.findkey(tag.hashCode())) {
                invertedIndex.insert(tag.hashCode(), new LinkedList<>());
            }
            invertedIndex.retrieve().insert(p);

            if (!tags.last()) {
                tags.findNext();
            } else {
                break;
            }
        }
    }

    // Delete a photo
    public void deletePhoto(String path) {
        invertedIndex.find(Relative.Root);
        while (true) {
            LinkedList<Photo> photos = invertedIndex.retrieve();
            photos.findFirst();
            for (int i = 0; !photos.empty(); i++) {
                if (photos.current.getData().getPath().equals(path)) {
                    photos.remove();
                    break;
                }
                if (!photos.last()) {
                    photos.findNext();
                } else {
                    break;
                }
            }
            if (photos.empty()) {
                invertedIndex.deleteSubtree();
            }
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