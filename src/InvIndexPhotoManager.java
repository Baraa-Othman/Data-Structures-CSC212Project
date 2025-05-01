public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invertedIndex; // Using your custom BST and LinkedList
    private LinkedList<Photo> phoIn = new LinkedList<Photo>();
    // Constructor
    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    // Add a photo
 public void addPhoto(Photo p) {
    	LinkedList<String> tags = p.getTags();
    	if(p == null)
    		return;
    	if(tags.empty())
    		return;
    	if(inPhotos(p))
    		return;
    	String tag;
    	LinkedList<Photo> ph ;
    	LinkedList<String> t;
    	Photo p1;
    	tags.findFirst();
    	while(!(tags.last())) {
    		tag = tags.retrieve();
    		if(index.empty()) {
    			ph = new LinkedList<Photo>();
    			t = new LinkedList<String>();
    			t.insert(tag);
    			p1 = new Photo(p.getPath(), t);
    			ph.insert(p1);
    			tags.findNext();
    			index.insert(tag, ph);
    		}
    		else {
    			if(!index.findkey(tag)) {
    				ph = new LinkedList<Photo>();
        			t = new LinkedList<String>();
        			t.insert(tag);
        			p1 = new Photo(p.getPath(), t);
        			ph.insert(p1);
        			tags.findNext();
        			index.insert(tag, ph);
    			}
    			else {
    				t = new LinkedList<String>();
        			t.insert(tag);
    				p1 = new Photo(p.getPath(), t);
    				tags.findNext();
    				index.retrieve().insert(p1);
    			}
    		}
    	}
    	tag = tags.retrieve();
		if(index.empty()) {
			ph = new LinkedList<Photo>();
			t = new LinkedList<String>();
			t.insert(tag);
			p1 = new Photo(p.getPath(), t);
			ph.insert(p1);
			tags.findNext();
			index.insert(tag, ph);
		}
		else {
			if(!index.findkey(tag)) {
				ph = new LinkedList<Photo>();
    			t = new LinkedList<String>();
    			t.insert(tag);
    			p1 = new Photo(p.getPath(), t);
    			ph.insert(p1);
    			tags.findNext();
    			index.insert(tag, ph);
			}
			else {
				t = new LinkedList<String>();
    			t.insert(tag);
				p1 = new Photo(p.getPath(), t);
				tags.findNext();
				index.retrieve().insert(p1);
			}
		}
    phoIn.insert(p);	
    	
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
    
        private boolean inPhotos(Photo ph) {
    	if(phoIn.empty())
    		return false;
    	phoIn.findFirst();
    	while(true) {
    		if(ph.getPath().equalsIgnoreCase(phoIn.retrieve().getPath()))
    			return true;
    		if(phoIn.last())
    			break;
    		phoIn.findNext();
    	}
    	return false;
    	}
}
