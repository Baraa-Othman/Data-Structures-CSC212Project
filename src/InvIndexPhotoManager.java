public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> index;
    private LinkedList<Photo> phoIn = new LinkedList<Photo>();

    public InvIndexPhotoManager() {
        this.index = new BST<>();
    }
    
    // Add a photo to the index
	// Big-Oh is O(n) where n is the number of tags in the photo.
    public void addPhoto(Photo p) {
    	LinkedList<String> tags = p.getTags();
    	if(p == null)
    		return;
    	if(tags.empty())
    		return;
    	if(findPhoto(p))
    		return;
    	String tag;
    	LinkedList<Photo> ph ;
    	LinkedList<String> t;
    	Photo p1;
    	tags.findFirst();
    	while(!(tags.last())) {
    		tag = tags.retrieve();
    		if(index.empty() ||!index.findkey(tag)) {
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
    	tag = tags.retrieve();
		if(index.empty()||!index.findkey(tag)) {
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
		
    phoIn.insert(p);	
    	
    }

	// Delete a photo from the index
	// Big-Oh is O(n squared) 
    public void deletePhoto(String path) {
    	if(!findPhoto(path)) {
    		System.out.println("No photos with this path is here");
    		return;
    	}
    	delete(path);
    	phoIn.remove();

    	
    }

	// Delete a photo from the index
	// helper method for the previous one
	// Big-Oh is O(nlogn)
    private void delete(String path) {
	 
	   LinkedList<String> t = phoIn.retrieve().getTags();
	   t.findFirst();
	   String s;
	   while(true) {
		   s = t.retrieve();
		   if(index.findkey(s)) {
			   index.retrieve().findFirst();
			   while(true) {
			   if(index.retrieve().retrieve().getPath().equalsIgnoreCase(path)) {
				   index.retrieve().remove();
				   break;
			   }
			   index.retrieve().findNext();
			   }
			   if(index.retrieve().empty())
				   index.remove_key(s);
			   }
		   if(t.last())
			   break;
		   t.findNext();
	   }
	 
    }
    
    // Get all photos in the index
	// Big-Oh is O(n squared).
    public void getPhotos() {
    	if(phoIn.empty()) {
    	System.out.println("no photos in the album");
    	return;
    	}
    	getphotos(index.root);
    }

	// Big-Oh is O(n squared).
    public void getphotos(BSTNode<LinkedList<Photo>> b) {
    		if (b == null){
    	        return;
    		}
    	    System.out.print(b.key + " -> ");
    	    b.data.findFirst();
    	    while (true) {
    	        System.out.print(b.data.retrieve().getPath());
    	        if (b.data.last()) 
    	        	break;
    	        System.out.print(", ");
    	        b.data.findNext();
    	    }
    	    System.out.println(); 
    	    getphotos(b.left);
    	    getphotos(b.right);
    }

	// Get all photos in the index by another way
	// not used
    /*public BST<LinkedList<Photo>> getPhotos2(){
    	if(phoIn.empty()) {
        	System.out.println("no photos in the album");
        	return null;
        	}
        	getphotos(index.root);
    	return index;
    }*/

	// Check if a photo is in the index
	// Big-Oh is O(n) where n is the number of photos.
    private boolean findPhoto(Photo ph) {
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

	// Find if a photo exists in the index
	// same as the previous method but with a string as parameter
	// Big-Oh is O(n) where n is the number of photos.
    private boolean findPhoto(String path) {
    	if(phoIn.empty())
    		return false;
    	phoIn.findFirst();
    	while(true) {
    		if(path.equalsIgnoreCase(phoIn.retrieve().getPath())) {
    			return true;
    		}
    		if(phoIn.last())
    			break;
    		phoIn.findNext();
    	}
    	return false;
    }
    
    
    
}
