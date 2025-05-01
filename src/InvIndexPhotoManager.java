public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invertedIndex; // Using your custom BST and LinkedList

    // Constructor
    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
    	LinkedList<String> tags = p.getTags();
    	String tagStr;
    	int c;
    	boolean flag = true;
    	Photo p1;
    	LinkedList<Photo> p2;
    	LinkedList<String> tag;
    	if(tags.empty())
    		return;
    	tags.findFirst(); 
    	while(!tags.empty()) {
    		tagStr = tags.retrieve();
        	if(index.empty()) {
        		p2 = new LinkedList<Photo>();
        		tag = new LinkedList<String>();
        		tag.insert(tagStr);
        		c = tagStr.compareTo("");
        		tags.remove();
        		p1 = new Photo(p.getPath(), tag);
        		p2.insert(p1);
        		index.insert(c, p2);
        	}
        	if(flag)
    		index.find(Relative.Root);
    		c = tagStr.compareTo(index.retrieve().retrieve().getTags().retrieve());
    		if(tagStr.equalsIgnoreCase(index.retrieve().retrieve().getTags().retrieve()) & !index.findkey(c) ) {
    			p2 = new LinkedList<Photo>();
    			tag = new LinkedList<String>();
        		tag.insert(tagStr);
        		tags.remove();
        		p1 = new Photo(p.getPath(), tag);
        		p2.insert(p1);
        		index.insert(c, p2);
        		index.retrieve().retrieve().getTags().findNext();
        		flag = true;
    		}
    		else {
    			if(!tagStr.equalsIgnoreCase(index.retrieve().retrieve().getTags().retrieve()))
    				flag = false;

    			else {
    			tag = new LinkedList<String>();
        		tag.insert(tagStr);
    			p1 = new Photo(p.getPath(), tag);
    			index.retrieve().insert(p1);
           		tags.remove();
        		index.retrieve().retrieve().getTags().findNext();
        		flag = true;
    			}
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
